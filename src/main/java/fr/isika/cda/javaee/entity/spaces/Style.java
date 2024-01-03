package fr.isika.cda.javaee.entity.spaces;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Style {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String fitnessName;
	private String logo; 
	private String color; 
	private String police;
	
	
	public String getFitnessName() {
		return fitnessName;
	}
	public void setFitnessName(String fitnessName) {
		this.fitnessName = fitnessName;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPolice() {
		return police;
	}
	public void setPolice(String police) {
		this.police = police;
	}
	
	

}
