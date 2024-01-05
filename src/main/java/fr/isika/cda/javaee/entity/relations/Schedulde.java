package fr.isika.cda.javaee.entity.relations;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Schedulde {

	@Id
	@GeneratedValue
	private Long scheduldeId;

	@OneToMany
	private List<Booking> bookingsList;

	public Long getScheduldeId() {
		return scheduldeId;
	}

	public void setScheduldeId(Long scheduldeId) {
		this.scheduldeId = scheduldeId;
	}

	public List<Booking> getBookingsList() {
		return bookingsList;
	}

	public void setBookingsList(List<Booking> bookingsList) {
		this.bookingsList = bookingsList;
	}

}
