package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.users.Role;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.exceptions.UserExistsException;
import fr.isika.cda.javaee.presentation.viewmodel.UserViewModel;
import fr.isika.cda.javaee.services.UserServices;

@Named
@ViewScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 8496614779097793939L;

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
	 * Delete a user from the database using it's Id.
	 * 
	 * @param userToDeleteId
	 * @return url (:String)
	 */
	public String deleteUser(Long userToDeleteId) {
		userDao.deleteUser(userToDeleteId);
		return "index";
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
			User userToLog = this.userDao.getUserByEmail(userViewModel.getEmail());
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

	public boolean logIn(Long userId) {
		User userToLog = this.userDao.getUserById(userId);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().getSessionMap().put("role", userToLog.getAccount().getRole());
		fc.getExternalContext().getSessionMap().put("id", userToLog.getUserId());
		fc.getExternalContext().getSessionMap().put("name", userToLog.getProfile().getCivility().getName());
		return true;
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
