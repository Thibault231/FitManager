package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.viewmodel.UserViewModel;

@Named
public class UserController implements Serializable {
	@Inject
	private IDaoUser userDao;
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
	 * Create a new user in the database using the UserviewModel.
	 * 
	 * @return url (:String)
	 */
	public String createUser() {
		User userToCreate = new User();
		userToCreate.setActive(true);
		userDao.createUser(userToCreate);
		return "index";
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
	 * role and id.
	 * 
	 * @return url (:String)
	 */
	public String authenticate() {
		String message;
		System.out.println("**************");
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
			fc.getExternalContext().getSessionMap().put("login", userViewModel.getEmail());
		}
		return "ManagerDashBoard";
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
