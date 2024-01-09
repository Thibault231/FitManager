package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.users.Role;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.exceptions.UserExistsException;
import fr.isika.cda.javaee.presentation.util.FileUploadUtils;
import fr.isika.cda.javaee.presentation.util.SessionUtils;
import fr.isika.cda.javaee.presentation.viewmodel.UserViewModel;
import fr.isika.cda.javaee.services.UserServices;

@Named
@ViewScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 8496614779097793439L;

	@Inject
	private IDaoUser userDao;

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
			userViewModel = new UserViewModel();
			return "ManagerDashBoard";
		} catch (UserExistsException e) {
			System.out.println("Exception : " + e.getMessage());
			return "RegisterManagerForm";
		}
	}

	/**
	 * Update the change of a user profile.
	 * 
	 * @param dashboard url (:String)
	 */
	public String updateUser(User updatedUser) {
		System.out.println("updateUser");
		userDao.updateUser(updatedUser);
		return "UpdateUserForm.xhtml?faces-redirect=true";
	}

	/**
	 * Delete a user from the database using it's Id.
	 * 
	 * @param userToDeleteId
	 * @return url (:String)
	 */

	public String deleteUser(User userToDelete) {
		System.err.println("Utilisateur à supprimer " + userToDelete);
		userDao.deleteUser(userToDelete.getUserId());
		return "UsersAccounts.xhtml?faces-redirect=true";
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
		if (userViewModel.getEmail().isEmpty()) {
			message = "Login inexistant !!";
			fc.addMessage(null, new FacesMessage(message));
			return "LoginForm";
		} else if (userViewModel.getPassword().isEmpty()) {
			message = "vérifiez votre mot de passe ";
			fc.addMessage(null, new FacesMessage(message));
			return "LoginForm";
		} else {
			User userToLog = this.userDao.getUserByLoginAndRole(userViewModel.getEmail(), Role.Gestionnaire);
			if (userToLog != null && userToLog.getAccount().getPassword().equals(userViewModel.getPassword())) {
				fc.getExternalContext().getSessionMap().put("role", userToLog.getAccount().getRole());
				fc.getExternalContext().getSessionMap().put("id", userToLog.getUserId());
				fc.getExternalContext().getSessionMap().put("name", userToLog.getProfile().getCivility().getName());
				return "ManagerDashBoard";
			} else {
				message = "Mot de passe erroné. ";
				fc.addMessage(null, new FacesMessage(message));
				return "LoginForm";
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
		return "index";
	}

}
