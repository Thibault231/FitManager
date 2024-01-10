package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import fr.isika.cda.javaee.dao.IDaoSpace;
import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.users.Account;
import fr.isika.cda.javaee.entity.users.Role;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.exceptions.UserExistsException;
import fr.isika.cda.javaee.presentation.util.FileUploadUtils;
import fr.isika.cda.javaee.presentation.util.SessionUtils;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;
import fr.isika.cda.javaee.services.UserServices;

@Named
@SessionScoped
public class SpaceController implements Serializable {

	private static final long serialVersionUID = 8496614779097793938L;

	@Inject
	private IDaoSpace spaceDao;
	@Inject
	private IDaoUser userDao;
	@Inject
	private UserServices userSvc;

	private SpaceViewModel spaceViewModel = new SpaceViewModel();

	private UploadedFile uploadedFile;

//**********************************************************	
	@PostConstruct
	public void init() {
		this.spaceViewModel = new SpaceViewModel();
	}

	public SpaceViewModel getSpaceViewModel() {
		return spaceViewModel;
	}

	public void setSpaceViewModel(SpaceViewModel spaceViewModel) {
		this.spaceViewModel = spaceViewModel;
	}

//**********************************************************
	/**
	 * Authenticate a visitor from a login form and create a session object with
	 * role and id. The authenticated visitor is next redirected to it's specific
	 * dashboard. <br/>
	 * <b>Use this method for space only</b>
	 * 
	 * @return url (:String)
	 */
	public String authenticateOnSpace() {
		String message;
		FacesContext fc = FacesContext.getCurrentInstance();
		Account userToLogAccount = spaceViewModel.getUser().getAccount();
		if (userToLogAccount.getLogin().isEmpty()) {
			message = "Login inexistant !!";
			fc.addMessage(null, new FacesMessage(message));
			return "SpaceLoginForm";
		} else if (userToLogAccount.getPassword().isEmpty()) {
			message = "vérifiez votre mot de passe ";
			fc.addMessage(null, new FacesMessage(message));
			return "SpaceLoginForm";
		} else {
			User userToLog = this.userDao.getUserBySpaceAndLogin(userToLogAccount.getLogin(),
					SessionUtils.getSpaceIdFromSession());
			if (userToLog != null && userToLog.getAccount().getPassword().equals(userToLogAccount.getPassword())) {
				fc.getExternalContext().getSessionMap().put("role", userToLog.getAccount().getRole());
				fc.getExternalContext().getSessionMap().put("id", userToLog.getUserId());
				fc.getExternalContext().getSessionMap().put("name", userToLog.getProfile().getCivility().getName());
				return redirectToRightDashBoard(userToLog.getAccount().getRole());
			} else {
				message = "Mot de passe erroné. ";
				fc.addMessage(null, new FacesMessage(message));
				return "SpaceLoginForm";
			}
		}
	}

	/**
	 * Get the Creating coach form using the UserviewModel, then call the
	 * UserService to create a new user.<br/>
	 * <b>Use this method for creating only coach</b>
	 * 
	 * @return url (:String)
	 */
	public String createCoachAccount() {

		this.spaceViewModel.getUser().getAccount().setRole(Role.Coach);
		this.spaceViewModel.getUser().getAccount().setPassword("00000");
		try {
			Long currentSpaceId = SessionUtils.getSpaceIdFromSession();
			Space currentSpace = spaceDao.getSpaceWithMembers(currentSpaceId);
			Long createdUserId = userSvc.createUser(spaceViewModel.getUser());
			User createdUser = userDao.getUserByIdWithLinkedSpaces(createdUserId);
			currentSpace.getUsers().add(createdUser);
			createdUser.getLinkedSpaces().add(currentSpace);

			spaceDao.updateSpace(currentSpace);
			userDao.updateUser(createdUser);

			this.spaceViewModel.setUser(new User(true));
			return "ManagerSpaceDashBoard";
		} catch (UserExistsException e) {
			System.out.println("Exception : " + e.getMessage());
			return "RegisterCoachForm";
		}
	}

	/**
	 * Get the Creating member form using the UserviewModel, then call the
	 * UserService to create a new user.<br/>
	 * <b>Use this method for creating only adherent</b>
	 * 
	 * @return url (:String)
	 */
	public String createMemberAccount() {
		Long currentSpaceId = SessionUtils.getSpaceIdFromSession();

		Space currentSpace = spaceDao.getSpaceWithMembers(currentSpaceId);
		this.spaceViewModel.getUser().getAccount().setRole(Role.Adherent);

		try {
			Long createdUserId = userSvc.createUser(spaceViewModel.getUser());
			User createdUser = userDao.getUserByIdWithLinkedSpaces(createdUserId);
			currentSpace.getUsers().add(createdUser);
			createdUser.getLinkedSpaces().add(currentSpace);

			spaceDao.updateSpace(currentSpace);
			userDao.updateUser(createdUser);

			return authenticateOnSpace();
		} catch (UserExistsException e) {
			System.out.println("Exception : " + e.getMessage());
			return "RegisterMemberForm";
		}
	}

	/**
	 * Create a space and link it to it's manager creator.
	 * 
	 * @return url (String)
	 */
	public String createSpace() {
		// 1- Get manager
		User createdUser = userDao.getUserByIdWithLinkedSpaces(SessionUtils.getUserIdFromSession());
		// 2- Create then get the new space
		Long createdSpaceId = spaceDao.createSpace(spaceViewModel.getSpace());
		Space createdSpace = spaceDao.getSpaceWithMembers(createdSpaceId);
		// 3-Link manager and new space as transient objects
		createdSpace.getUsers().add(createdUser);
		createdUser.getLinkedSpaces().add(createdSpace);
		// 4- Persist manager and space links
		spaceDao.updateSpace(createdSpace);
		userDao.updateUser(createdUser);

		return "ManagerSpacesList";
	}

	/**
	 * Get as param a space Id and return this space index view.
	 * 
	 * @return formated url (String)
	 */
	public String goToSpaceIndex() {
		// 1 - Récupérer la valeur du param "spaceId"
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long spaceId = Long.valueOf(params.get("spaceId"));

		// 2 - aller chercher l'objet Salle par cet id (en bdd)
		spaceViewModel.setSpace(spaceDao.getSpaceById(spaceId));
		// 3- Renseigne l'id de la salle dans la session.
		String viewToReturn = "TemplateFitness.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;

		return spaceLogOut(spaceId, viewToReturn);
	}

	public String deleteSpace(Long spaceToDeleteId) {
		spaceDao.deleteSpace(spaceToDeleteId);

		return "index";
	}

	public void uploadAdministrativeDocument(FileUploadEvent event) throws Exception {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));

		UploadedFile uploadedFile = event.getFile();
		String fileName = String.join("_", timestamp, uploadedFile.getFileName());

		spaceViewModel.getUser().getAccount().getAdministrativeDocument().setFilePath(fileName);

		FileUploadUtils.uploadFileToApp(uploadedFile, fileName);
	}

	/**
	 * Return the list of all the active spaces created on the plateform by all
	 * manager.
	 * 
	 * @return the list of spaces (: List<Space>)
	 */
	public List<Space> getAllActiveSpaces() {
		List<Space> spaceList = spaceDao.getAllSpace();
		return spaceList;
	}

	/**
	 * Return the list of all the active spaces created by a specific manager. <br/>
	 * This method use the session parameters and so need the manager to be
	 * connected.
	 * 
	 * @return the list of spaces (: List<Space>)
	 */
	public List<Space> getAllActiveSpacesOfManager() {
		List<Space> spaceList = userDao.getUserByIdWithLinkedSpaces(SessionUtils.getUserIdFromSession())
				.getLinkedSpaces();
		return spaceList;
	}

	public Space getSpaceById(Long spaceId) {
		return spaceDao.getSpaceById(spaceId);
	}

	/**
	 * Get the current space object with it's subscriptions
	 * 
	 * @return current space (:Space)
	 */
	public Space getCurrentSpaceWithSubscriptions() {
		Long spaceId = SessionUtils.getSpaceIdFromSession();
		return spaceDao.getSpaceWithMembers(spaceId);
	}

	/**
	 * Get the current space object with it's users
	 * 
	 * @return current space (:Space)
	 */
	public Space getCurrentSpaceWithUsers() {
		Long spaceId = SessionUtils.getSpaceIdFromSession();
		return spaceDao.getSpaceWithMembers(spaceId);
	}

	public Space getSpaceByName(String spaceName) {
		return spaceDao.getSpaceByName(spaceName);
	}

	/**
	 * Return the view of a user dashboard, using it's role.
	 * 
	 * @param userRole (:Role ENUM)
	 * @return url of the user dashboard (:String)
	 */
	public String redirectToRightDashBoard(Role userRole) {
		this.spaceViewModel.setUser(userDao.getUserById(SessionUtils.getUserIdFromSession()));
		if (userRole.equals(Role.Adherent)) {
			return "AdherentDashboard";
		} else if (userRole.equals(Role.Coach)) {
			return "CoachDashboard";
		} else if (userRole.equals(Role.Gestionnaire)) {
			return "ManagerSpaceDashBoard";
		} else {
			Long spaceId = SessionUtils.getSpaceIdFromSession();
			return "TemplateFitness.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;
		}
	}

	/**
	 * Logout a connected user on the space and return the space index view.
	 * 
	 * @return url of the index space's page (:String)
	 */
	public String simpleSpacelogout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Long spaceId = (Long) fc.getExternalContext().getSessionMap().get("spaceId");
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		fc.getExternalContext().getSessionMap().put("spaceId", spaceId);
		return "TemplateFitness.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;
	}

	/**
	 * Logout a connected user on plateform and reload a session with only spaceId
	 * parameter. <b> Use this method only for logout from plateform</b>
	 * 
	 * @return url (:String)
	 */
	public String spaceLogOut(Long spaceId, String viewToReturn) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().getSessionMap().put("spaceId", spaceId);
		return viewToReturn;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

}
