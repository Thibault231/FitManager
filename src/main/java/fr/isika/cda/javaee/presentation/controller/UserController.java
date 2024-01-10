package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import fr.isika.cda.javaee.dao.IDaoSpace;
import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.users.Role;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.exceptions.UserExistsException;
import fr.isika.cda.javaee.presentation.util.SessionUtils;
import fr.isika.cda.javaee.presentation.viewmodel.UserViewModel;
import fr.isika.cda.javaee.services.UserServices;

@Named
@SessionScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 8496614779097793439L;

	@Inject
	private IDaoUser userDao;

	@Inject
	private IDaoSpace spaceDao;

	@Inject
	private UserServices userSvc;

	private UserViewModel userViewModel;

	@PostConstruct
	public void init() {
		this.userViewModel = new UserViewModel();
	}

//***************************************
	public UserViewModel getUserViewModel() {
		return userViewModel;
	}

	public void setUserViewModel(UserViewModel userViewModel) {
		this.userViewModel = userViewModel;
	}

//***************************************
	/**
	 * Get the Creating manager form using the UserviewModel, then call the
	 * UserService to create a new user.<br/>
	 * <b>Use this method for creating only manager</b>
	 * 
	 * @return url (:String)
	 */
	public String createManagerAccount() {
		this.userViewModel.getUser().getAccount().setRole(Role.Gestionnaire);
		Long userToCreateId;
		try {
			userToCreateId = userSvc.createUser(userViewModel.getUser());
			logIn(userToCreateId);
			return "ManagerDashBoard?faces-redirect=true";
		} catch (UserExistsException e) {
			System.out.println("Exception : " + e.getMessage());
			this.setUserViewModel(new UserViewModel());
			return "RegisterManagerForm?faces-redirect=true";
		}
	}

	/**
	 * Update the change of a user profile.
	 * 
	 * @param dashboard url (:String)
	 */
	public String updateUser() {
		// mettre à jour le user
		userSvc.updateUserOnPlateform(userViewModel.getUser(), SessionUtils.getUserIdFromSession());
		// mettre à jour la session si le nom est changé
		if (userViewModel.getUser().getProfile().getCivility().getName() != null) {
			String newName = userViewModel.getUser().getProfile().getCivility().getName();
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.getExternalContext().getSessionMap().put("name", newName);
		}
		return "ManagerDashBoard.xhtml";
	}

	/**
	 * Delete a user from the database using it's Id.
	 * 
	 * @param userToDeleteId
	 * @return url (:String)
	 */
	public String deleteUser(Long userToDeleteId) {
		userDao.deleteUser(userToDeleteId);
		return redirectToRightDashBoard(SessionUtils.getUserRoleFromSession());
	}

	/**
	 * Return the view of a user dashboard, using it's role.
	 * 
	 * @param userRole (:Role ENUM)
	 * @return url of the user dashboard (:String)
	 */
	public String redirectToRightDashBoard(Role userRole) {
		if (userRole.equals(Role.Adherent)) {
			return "AdherentDashboard?faces-redirect=true";
		} else if (userRole.equals(Role.Coach)) {
			return "CoachDashboard";
		} else if (userRole.equals(Role.Gestionnaire)) {
			return "ManagerSpaceDashBoard?faces-redirect=true";
		} else {
			Long spaceId = SessionUtils.getSpaceIdFromSession();
			return "SpaceView.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;
		}
	}

	/**
	 * Return all the users marked as active in the database.
	 * 
	 * @return (:List<User>)
	 */
	public List<User> getAllActiveUser() {
		List<User> usersList = userDao.getAllUsers();
		return usersList;
	}

	/**
	 * Return all the users of the current space, marked as active in the database.
	 * <b>Use this method for connected user</b>
	 * 
	 * @return (:List<User>)
	 */
	public List<User> getAllMembersOfCurrentSpace() {
		Space currentSpace = spaceDao.getSpaceWithMembers(SessionUtils.getSpaceIdFromSession());
		List<User> usersList = new ArrayList<User>();
		for (User user : currentSpace.getUsers()) {
			if (user.getAccount().getRole().equals(Role.Adherent) && user.isActive())
				usersList.add(user);
		}
		return usersList;
	}

	/**
	 * Return all the users of the current space, marked as active in the database.
	 * <b>Use this method for connected user</b>
	 * 
	 * @return (:List<User>)
	 */
	public List<User> getAllCoachesOfCurrentSpace() {
		Space currentSpace = spaceDao.getSpaceWithMembers(SessionUtils.getSpaceIdFromSession());
		List<User> usersList = new ArrayList<User>();
		for (User user : currentSpace.getUsers()) {
			if (user.getAccount().getRole().equals(Role.Coach) && user.isActive())
				usersList.add(user);
		}
		return usersList;
	}

	/**
	 * Get a user from the database using it's Id.
	 * 
	 * @param userId (:Long)
	 * @return (:User)
	 */
	public User getUser(Long userId) {
		return userDao.getUserById(userId);
	}

	/**
	 * Get a user from the database using it's Email.
	 * 
	 * @param userEmail (:String)
	 * @return (:User)
	 */
	public User getUser(String userEmail) {
		return userDao.getUserByEmail(userEmail);
	}

	/**
	 * Get the current user object and return it.
	 * 
	 * @return current user (:User)
	 */
	public User getCurrentUserDetails() {
		return userDao.getUserById(SessionUtils.getUserIdFromSession());
	}

	/**
	 * Authenticate a visitor from a login form and create a session object with
	 * role and id. <br/>
	 * <b>Use this method for plateform only</b>
	 * 
	 * @return url (:String)
	 */
	public String authenticate() {
		String message;
		FacesContext fc = FacesContext.getCurrentInstance();
		// Email non rempli
		if (userViewModel.getEmail().isEmpty()) {
			message = "Login inexistant !!";
			fc.addMessage(null, new FacesMessage(message));
			return "LoginForm?faces-redirect=true";
			// Password non rempli
		} else if (userViewModel.getPassword().isEmpty()) {
			message = "vérifiez votre mot de passe ";
			fc.addMessage(null, new FacesMessage(message));
			return "LoginForm?faces-redirect=true";
		} else {
			// Check User présent sur la plateforme
			User userToLog = this.userDao.getUserByLoginAndRole(userViewModel.getEmail(), Role.Gestionnaire);
			// Vérification de la rectitude des données du formulaire
			if (userToLog != null && userToLog.getAccount().getPassword().equals(userViewModel.getPassword())) {
				fc.getExternalContext().getSessionMap().put("role", userToLog.getAccount().getRole());
				fc.getExternalContext().getSessionMap().put("id", userToLog.getUserId());
				fc.getExternalContext().getSessionMap().put("name", userToLog.getProfile().getCivility().getName());
				// chargement du viewmodel avec le user.
				userViewModel.setUser(userToLog);
				return "ManagerDashBoard.xhtml?faces-redirect=true";
			} else {
				message = "Mot de passe erroné. ";
				fc.addMessage(null, new FacesMessage(message));
				return "LoginForm?faces-redirect=true";
			}
		}
	}

	/**
	 * Connecte Manager of the plateform, creating a new session.
	 * 
	 * @param userId (:Long)
	 * @return success (:Boolean)
	 */
	public boolean logIn(Long userId) {
		User userToLog = this.userDao.getUserByIdWithLinkedSpaces(userId);
		if (userToLog != null) {
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.getExternalContext().getSessionMap().put("role", userToLog.getAccount().getRole());
			fc.getExternalContext().getSessionMap().put("id", userToLog.getUserId());
			fc.getExternalContext().getSessionMap().put("name", userToLog.getProfile().getCivility().getName());
			userViewModel.setUser(userToLog);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Logout a connected user.
	 * 
	 * @return url (:String)
	 */
	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		this.setUserViewModel(new UserViewModel());
		return "index?faces-redirect=true";
	}

}
