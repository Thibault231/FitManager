package fr.isika.cda.javaee.entity.spaces;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Configuration {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String fitnessName;
	
	@OneToOne
	private Style style;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFitnessName() {
		return fitnessName;
	}
	public void setFitnessName(String fitnessName) {
		this.fitnessName = fitnessName;
	}
	
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}

	
}
