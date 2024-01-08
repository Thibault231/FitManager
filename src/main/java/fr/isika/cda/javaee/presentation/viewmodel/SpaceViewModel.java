package fr.isika.cda.javaee.presentation.viewmodel;

import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.users.User;

public class SpaceViewModel {

	private Long spaceId;
	private Space space;
	private User user;

	private String administrativeDocPath;

	public SpaceViewModel() {
		this.space = new Space(true);
		this.user = new User(true);
	}

//*******************************************
	public Long getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(Long spaceId) {
		this.spaceId = spaceId;
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAdministrativeDocPath() {
		return administrativeDocPath;
	}

	public void setAdministrativeDocPath(String administrativeDocPath) {
		this.administrativeDocPath = administrativeDocPath;
	}
}
