package fr.isika.cda.javaee.presentation.viewmodel;

import fr.isika.cda.javaee.entity.relations.Booking;
import fr.isika.cda.javaee.entity.relations.Planning;
import fr.isika.cda.javaee.entity.relations.Schedulde;

public class RelationViewModel {
	private Booking booking;
	private Planning planning;
	private Schedulde schedulde;
	private Long spaceId;

	public RelationViewModel() {
		this.booking = new Booking();
		this.planning = new Planning();
		this.schedulde = new Schedulde();
	}

//**********************************************************
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Planning getPlanning() {
		return planning;
	}

	public void setPlanning(Planning planning) {
		this.planning = planning;
	}

	public Schedulde getSchedulde() {
		return schedulde;
	}

	public void setSchedulde(Schedulde schedulde) {
		this.schedulde = schedulde;
	}

	public Long getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(Long spaceId) {
		this.spaceId = spaceId;
	}

}
