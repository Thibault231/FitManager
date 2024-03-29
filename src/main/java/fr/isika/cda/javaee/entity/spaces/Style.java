package fr.isika.cda.javaee.entity.spaces;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import fr.isika.cda.javaee.presentation.util.DefaultConfig;

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

	private String mainColor = DefaultConfig.DEFAULT_MAIN_COLOR;

	private String secondColor = DefaultConfig.DEFAULT_SECOND_COLOR;

	private String thirdcolor = DefaultConfig.DEFAULT_THIRD_COLOR;

	private String font = DefaultConfig.DEFAULT_FONT;

//*******************************************************************	
	/**
	 * Empty constructor for JEE.
	 */
	public Style() {
	}

//*******************************************************************	
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
