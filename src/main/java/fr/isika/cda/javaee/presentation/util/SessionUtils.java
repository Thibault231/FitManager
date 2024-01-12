package fr.isika.cda.javaee.presentation.util;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import fr.isika.cda.javaee.dao.spaces.IDaoSpace;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.users.Role;

/**
 * Common methods for all controllers related to session.
 * 
 * @author Thibault
 *
 */
public final class SessionUtils {

	private SessionUtils() {
	}

	/**
	 * Get and return the Id of the current space register in the session.
	 * 
	 * @return spaceId (:Long)
	 */
	public static Long getSpaceIdFromSession() {
		Long spaceId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("spaceId");
		return spaceId;
	}

	/**
	 * Get and return the Id of the current user register in the session.
	 * 
	 * @return userId (:Long)
	 */
	public static Long getUserIdFromSession() {
		Long userId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
		return userId;
	}

	/**
	 * Get and return the Role of the current user register in the session.
	 * 
	 * @return userRole (:Role)
	 */
	public static Role getUserRoleFromSession() {
		Role userRole = (Role) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("role");
		return userRole;
	}

	/**
	 * Close the current session.
	 */
	public static void invalidateSession() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
	}

	/**
	 * Put the given parameter Id as the spaceId parameter of the session.
	 * 
	 * @param spaceId
	 */
	public static void putSpaceIdInSession(Long spaceId) {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().getSessionMap().put("spaceId", spaceId);
	}

	/**
	 * Redirect the current user to it's space dashboard according to it's
	 * role.</br>
	 * <b>Only use this method for user on a space</b>
	 * 
	 * @return
	 */
	public static String redirectToDashBoard() {
		Role userRole = getUserRoleFromSession();
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
}
