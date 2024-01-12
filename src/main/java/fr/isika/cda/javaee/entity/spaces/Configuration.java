package fr.isika.cda.javaee.entity.spaces;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import fr.isika.cda.javaee.presentation.util.DefaultConfig;

/**
 * Customizable data of a Space, type of text and pictures.
 * 
 * @author Nene
 *
 */
@Entity
public class Configuration {

	@Id
	@GeneratedValue
	private Long id;

	private String fitnessName;

	private String logo;
	private String slogan;
	private String mainText;
	private String mainPicture = DefaultConfig.DEFAULT_MAIN_PICTURE;

	@OneToOne
	private Style style;

//*******************************************

	public Configuration() {

	}

	public Configuration(boolean isForViewModel) {
		this.style = new Style();

	}

//*******************************************
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getMainText() {
		return mainText;
	}

	public void setMainText(String mainText) {
		this.mainText = mainText;
	}

	public String getMainPicture() {
		return mainPicture;
	}

	public void setMainPicture(String mainPicture) {
		this.mainPicture = mainPicture;
	}

}
