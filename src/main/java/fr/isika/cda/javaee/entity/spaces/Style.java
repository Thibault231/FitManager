package fr.isika.cda.javaee.entity.spaces;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Style {

	@Id
	@GeneratedValue
	private Long id;
	private String backgroundColor;
	private String navBarColor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getNavBarColor() {
		return navBarColor;
	}

	public void setNavBarColor(String navBarColor) {
		this.navBarColor = navBarColor;
	}

}
