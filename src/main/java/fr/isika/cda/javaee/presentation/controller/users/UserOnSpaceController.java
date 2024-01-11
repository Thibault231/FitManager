package fr.isika.cda.javaee.presentation.controller.users;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import fr.isika.cda.javaee.dao.spaces.IDaoSpace;
import fr.isika.cda.javaee.dao.user.IDaoUser;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.users.Account;
import fr.isika.cda.javaee.entity.users.Role;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.exceptions.UserExistsException;
import fr.isika.cda.javaee.presentation.util.FileUploadUtils;
import fr.isika.cda.javaee.presentation.util.SessionUtils;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;
import fr.isika.cda.javaee.services.UserServices;

/**
 * Manage User objects in space's views.
 * 
 * @author Alex Charef Nene Thibault
 *
 */
@Named("spaceUser")
@SessionScoped
public class UserOnSpaceController implements Serializable {

	private static final long serialVersionUID = 849661469097793938L;

	@Inject
	private IDaoSpace spaceDao;
	@Inject
	private IDaoUser userDao;
	@Inject
	private UserServices userSvc;

	private SpaceViewModel spaceViewModel = new SpaceViewModel();

	private UploadedFile uploadedFile;

	// **********************************************************
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

	// **********************************************************

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
			return "SpaceLoginForm?faces-redirect=true";
		} else if (userToLogAccount.getPassword().isEmpty()) {
			message = "vérifiez votre mot de passe ";
			fc.addMessage(null, new FacesMessage(message));
			return "SpaceLoginForm?faces-redirect=true";
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
				return "SpaceLoginForm?faces-redirect=true";
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
		// Injecte le role et le mot de passe par défaut dans le formulaire
		this.spaceViewModel.getNewUser().getAccount().setRole(Role.Coach);
		this.spaceViewModel.getNewUser().getAccount().setPassword("00000");
		try {
			// Attrappe la salle courante
			Long currentSpaceId = SessionUtils.getSpaceIdFromSession();
			Space currentSpace = spaceDao.getSpaceWithMembers(currentSpaceId);
			// Persist le nouveau coach
			Long createdUserId = userSvc.createUser(spaceViewModel.getNewUser());
			// Lie le coach et la salle courante
			User createdUser = userDao.getUserByIdWithLinkedSpaces(createdUserId);
			currentSpace.getUsers().add(createdUser);
			createdUser.getLinkedSpaces().add(currentSpace);
			spaceDao.updateSpace(currentSpace);
			userDao.updateUser(createdUser);
			// Réinitialise le formulaire du viewmodel
			this.spaceViewModel.setNewUser(new User(true));
			return "ManagerSpaceDashBoard?faces-redirect=true";
		} catch (UserExistsException e) {
			System.out.println("Exception : " + e.getMessage());
			this.spaceViewModel.setNewUser(new User(true));
			return "RegisterCoachForm?faces-redirect=true";
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
			return "RegisterMemberForm?faces-redirect=true";
		}
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
			return "AdherentDashboard?faces-redirect=true";
		} else if (userRole.equals(Role.Coach)) {
			return "CoachDashboard?faces-redirect=true";
		} else if (userRole.equals(Role.Gestionnaire)) {
			return "ManagerSpaceDashBoard?faces-redirect=true";
		} else {
			Long spaceId = SessionUtils.getSpaceIdFromSession();
			return "SpaceView.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;
		}
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	/**
	 * Update the change of a user profile.
	 * 
	 * @param dashboard url (:String)
	 */
	public String updateUser(Long userToUpdateid) {
		// mettre à jour le user
		userSvc.updateUserOnPlateform(spaceViewModel.getUser(), userToUpdateid);
		// mettre à jour la session si le nom est changé
		if (spaceViewModel.getUser().getProfile().getCivility().getName() != null) {
			String newName = spaceViewModel.getUser().getProfile().getCivility().getName();
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.getExternalContext().getSessionMap().put("name", newName);
		}
		return redirectToRightDashBoard(SessionUtils.getUserRoleFromSession());
	}

	public void uploadAdministrativeDocument(FileUploadEvent event) throws Exception {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));

		UploadedFile uploadedFile = event.getFile();
		String fileName = String.join("_", timestamp, uploadedFile.getFileName());

		spaceViewModel.getUser().getAccount().getAdministrativeDocument().setFilePath(fileName);

		FileUploadUtils.uploadFileToApp(uploadedFile, fileName);
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}
}