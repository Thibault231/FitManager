package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import fr.isika.cda.javaee.dao.IDaoSpace;
import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.users.Account;
import fr.isika.cda.javaee.entity.users.Role;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;

@Named
@ViewScoped
public class SpaceController implements Serializable {

	private static final long serialVersionUID = 8496614779097793938L;

	@Inject
	private IDaoSpace spaceDao;
	@Inject
	private IDaoUser userDao;

	private SpaceViewModel spaceViewModel = new SpaceViewModel();

//**********************************************************	
	@PostConstruct
	public void init() {
		this.spaceViewModel = new SpaceViewModel();
	}

	public SpaceViewModel getSpaceForm() {
		return spaceViewModel;
	}

	public void setSpaceForm(SpaceViewModel spaceForm) {
		this.spaceViewModel = spaceForm;
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
			User userToLog = this.userDao.getUserByEmail(userToLogAccount.getLogin());
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

	public String createSpace() {
		Long spaceId = spaceDao.createSpace(spaceViewModel.getSpace());

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
		String viewToReturn = "AccueilSalle.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;

		return spaceLogOut(spaceId, viewToReturn);
	}

	public String deleteSpace(Long spaceToDeleteId) {
		spaceDao.deleteSpace(spaceToDeleteId);

		return "index";
	}

	public List<Space> getAllActiveSpaces() {
		List<Space> spaceList = spaceDao.getAllSpace();
		return spaceList;
	}

	public Space getSpaceById(Long spaceId) {
		return spaceDao.getSpaceById(spaceId);
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
		if (userRole.equals(Role.Adherent)) {
			return "Test-AdherentDashboard";
		} else if (userRole.equals(Role.Coach)) {
			return "Test-CoachDashboard";
		} else if (userRole.equals(Role.Gestionnaire)) {
			return "ManagerSpaceDashBoard";
		} else {
			Long spaceId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("spaceId");
			return "AccueilSalle.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;
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
		return "AccueilSalle.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;
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

}
