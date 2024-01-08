package fr.isika.cda.javaee;

import javax.faces.context.FacesContext;

public final class SessionUtils {

	private SessionUtils() {
	}

	public static Long getSpaceIdFromSession() {
		Long spaceId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("spaceId");
		return spaceId;
	}

}
