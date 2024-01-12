package fr.isika.cda.javaee.entity.spaces;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Customizable data of a Space, type of font and colors.
 * 
 * @author Nene
 *
 */
@Entity
public class Style {

	@Id
	@GeneratedValue
	private long id;

	private String navbarColor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNavbarColor() {
		return navbarColor;
	}

	public void setNavbarColor(String navbarColor) {
		this.navbarColor = navbarColor;
	}

}
