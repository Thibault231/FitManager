package fr.isika.cda.javaee.presentation.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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

	public static Long getSpaceIdFromSession() {
		Long spaceId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("spaceId");
		return spaceId;
	}

	public static Long getUserIdFromSession() {
		Long spaceId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
		return spaceId;
	}

	public static Role getUserRoleFromSession() {
		Role userRole = (Role) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("role");
		return userRole;
	}

	public static void invalidateSession() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
	}

	public static void putSpaceIdInSession(Long spaceId) {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().getSessionMap().put("spaceId", spaceId);
	}

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
