package fr.isika.cda.javaee.entity.plateform;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * All lessons, courses or other fitness activities.
 * 
 * @author Charef
 *
 */
@Entity
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	public Activity(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Activity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
