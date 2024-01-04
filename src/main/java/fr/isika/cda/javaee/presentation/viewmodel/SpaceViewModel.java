package fr.isika.cda.javaee.presentation.viewmodel;

import fr.isika.cda.javaee.entity.spaces.Space;

public class SpaceViewModel {

	private Long spaceId;
	private Space space;

	public SpaceViewModel() {
		this.space = new Space(true);
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

}
