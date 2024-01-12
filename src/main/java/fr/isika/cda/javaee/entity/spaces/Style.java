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

	private String mainColor = "#F5B37D";

	private String secondColor = "#D52056";

	private String thirdcolor = "gray";

	private String font;

//*******************************************

	public Style() {
	}

//*******************************************
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMainColor() {
		return mainColor;
	}

	public void setMainColor(String mainColor) {
		this.mainColor = mainColor;
	}

	public String getSecondColor() {
		return secondColor;
	}

	public void setSecondColor(String secondColor) {
		this.secondColor = secondColor;
	}

	public String getThirdcolor() {
		return thirdcolor;
	}

	public void setThirdcolor(String thirdcolor) {
		this.thirdcolor = thirdcolor;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

}
