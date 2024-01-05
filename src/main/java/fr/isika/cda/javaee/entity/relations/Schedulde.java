package fr.isika.cda.javaee.entity.relations;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Schedulde {

	@Id
	@GeneratedValue
	private Long scheduldeId;

	private List<Booking> bookingsList;
}
