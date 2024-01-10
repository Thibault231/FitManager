package fr.isika.cda.javaee.presentation.util;

import javax.faces.context.FacesContext;

import fr.isika.cda.javaee.entity.users.Role;

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
}
