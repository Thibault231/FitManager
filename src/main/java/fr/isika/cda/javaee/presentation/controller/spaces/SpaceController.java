package fr.isika.cda.javaee.presentation.controller.spaces;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import fr.isika.cda.javaee.dao.spaces.IDaoSpace;
import fr.isika.cda.javaee.dao.user.IDaoUser;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.users.Role;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.util.FileUploadUtils;
import fr.isika.cda.javaee.presentation.util.SessionUtils;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;
import fr.isika.cda.javaee.services.SpaceServices;
import fr.isika.cda.javaee.services.UserServices;

/**
 * Manage Space objects in views.
 * 
 * @author Alex Charef Nene Thibault
 *
 */
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
	@Inject
	private SpaceServices spaceSvc;

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

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

//**********************************************************
	/**
	 * Create a space and link it to it's manager creator.
	 * 
	 * @return url (String)
	 */
	public String createSpace() {
		System.out.println("***********************************************!!!!!!!!!!!!!!!!!!!!!");
		System.err.println(spaceViewModel);
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

		return "ManagerSpacesList.xhtml?faces-redirect=true";
	}

	/**
	 * Smooth delete a space from the database.
	 * 
	 * @param spaceToDeleteId (:Long)
	 * @return url of the manager dashboard (:String)
	 */
	public String deleteSpace(Long spaceToDeleteId) {
		spaceDao.deleteSpace(spaceToDeleteId);

		return "ManagerDashBoard.xtml?faces-redirect=true";
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

	/**
	 * Get the current simple space object, usig it's Id
	 * 
	 * @return current space (:Space)
	 */
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

	/**
	 * Get as param a space Id and return this space index view.
	 * 
	 * @return formated url (String)
	 */
	public String goToSpaceIndex() {
		// Récupérer la valeur du param "spaceId"
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long spaceId = Long.valueOf(params.get("spaceId"));

		String viewToReturn = "SpaceView.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;
		return spaceLogOut(spaceId, viewToReturn);
	}

	/**
	 * Reedirect to the update-space form with the current space in the viewmodel of
	 * the controller.
	 * 
	 * @return url (String)
	 */
	public String goToUpdateSpaceForm() {
		spaceViewModel.setSpace(spaceDao.getSpaceById(SessionUtils.getSpaceIdFromSession()));
		return "UpdateSpaceForm.xhtml?faces-redirect=true";
	}

	/**
	 * Check if a user is connected. Method used for conditional display in views.
	 * 
	 * @return (:boolean)
	 */
	public boolean isUserConnected() {
		return SessionUtils.getUserIdFromSession() != null;
	}

	/**
	 * Check if a user is connected as the manager of the space. Method used for
	 * conditional display in views.
	 * 
	 * @return (:boolean)
	 */
	public boolean isManagerOfTheSpaceConnected() {
		Space currentSpace = spaceDao.getSpaceWithMembers(SessionUtils.getSpaceIdFromSession());
		User currentUser = userDao.getUserById(SessionUtils.getUserIdFromSession());
		if (currentSpace != null && currentUser != null) {
			if (SessionUtils.getUserRoleFromSession().equals(Role.Gestionnaire)) {
				for (User user : currentSpace.getUsers()) {
					if (user.getUserId() == SessionUtils.getUserIdFromSession())
						return true;
				}
			}
		}
		return false;
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

	/**
	 * Logout a connected user on plateform and reload a session with only spaceId
	 * parameter. <b> Use this method only for logout from plateform</b>
	 * 
	 * @return url (:String)
	 */
	public String spaceLogOut(Long spaceId, String viewToReturn) {
		SessionUtils.invalidateSession();
		SessionUtils.putSpaceIdInSession(spaceId);
		this.spaceViewModel.setSpaceId(spaceId);
		this.spaceViewModel.setSpace(spaceDao.getSpaceById(spaceId));
		return viewToReturn;
	}

	/**
	 * Logout a connected user on the space and return the space index view.
	 * 
	 * @return url of the index space's page (:String)
	 */
	public String simpleSpacelogout() {
		Long spaceId = SessionUtils.getSpaceIdFromSession();
		SessionUtils.invalidateSession();
		SessionUtils.putSpaceIdInSession(spaceId);
		return "SpaceView.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;
	}

	/**
	 * Update all parameter of the current space, from an update-form in the manager
	 * dashboard and persist it.
	 * 
	 * @return url of the manager dashboard(:String)
	 */
	public String upDateSpaceOnDashBoard() {
		spaceSvc.updateUserOnPlateform(spaceViewModel.getSpace());
		return "ManagerSpaceDashBoard?faces-redirect=true";
	}

	/**
	 * Update the style only of the current space, from an update-form in the index
	 * space page and persist it.
	 * 
	 * @return url of the space index(:String)
	 */
	public String upDateSpaceOnIndex() {
		spaceSvc.updateUserOnIndex(spaceViewModel.getSpace());
		Long spaceId = SessionUtils.getSpaceIdFromSession();
		return "SpaceView.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;
	}

	/**
	 * Upload first picture of the carrousel for Space objects, then rename and
	 * stock it.
	 * 
	 * @param event (:FileUploadEvent)
	 * @throws Exception
	 */
	public void uploadSpaceFirstPicture(FileUploadEvent event) throws Exception {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
		UploadedFile uploadedFile = event.getFile();
		String fileName = String.join("_", timestamp, uploadedFile.getFileName());
		spaceViewModel.getSpace().getInfos().getConfiguration().setCarrouselOne(fileName);
		FileUploadUtils.uploadFileToApp(uploadedFile, fileName);
	}

	/**
	 * Upload second picture of the carrousel for Space objects, then rename and
	 * stock it.
	 * 
	 * @param event (:FileUploadEvent)
	 * @throws Exception
	 */
	public void uploadSpaceSecondPicture(FileUploadEvent event) throws Exception {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
		UploadedFile uploadedFile = event.getFile();
		String fileName = String.join("_", timestamp, uploadedFile.getFileName());
		spaceViewModel.getSpace().getInfos().getConfiguration().setCarrouselTwo(fileName);
		FileUploadUtils.uploadFileToApp(uploadedFile, fileName);
	}

	/**
	 * Upload third picture of the carrousel for Space objects, then rename and
	 * stock it.
	 * 
	 * @param event (:FileUploadEvent)
	 * @throws Exception
	 */
	public void uploadSpaceThirdPicture(FileUploadEvent event) throws Exception {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
		UploadedFile uploadedFile = event.getFile();
		String fileName = String.join("_", timestamp, uploadedFile.getFileName());
		spaceViewModel.getSpace().getInfos().getConfiguration().setCarrouselThree(fileName);
		FileUploadUtils.uploadFileToApp(uploadedFile, fileName);
	}

	/**
	 * Upload logo picture for Space objects, then rename and stock it.
	 * 
	 * @param event (:FileUploadEvent)
	 * @throws Exception
	 */
	public void uploadSpaceLogo(FileUploadEvent event) throws Exception {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
		UploadedFile uploadedFile = event.getFile();
		String fileName = String.join("_", timestamp, uploadedFile.getFileName());
		spaceViewModel.getSpace().getInfos().getConfiguration().setLogo(fileName);
		FileUploadUtils.uploadFileToApp(uploadedFile, fileName);
	}

	/**
	 * Upload main image picture for Space objects, then rename and stock it.
	 * 
	 * @param event (:FileUploadEvent)
	 * @throws Exception
	 */
	public void uploadSpaceMainPicture(FileUploadEvent event) throws Exception {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
		UploadedFile uploadedFile = event.getFile();
		String fileName = String.join("_", timestamp, uploadedFile.getFileName());
		spaceViewModel.getSpace().getInfos().getConfiguration().setMainPicture(fileName);
		FileUploadUtils.uploadFileToApp(uploadedFile, fileName);
	}

}
