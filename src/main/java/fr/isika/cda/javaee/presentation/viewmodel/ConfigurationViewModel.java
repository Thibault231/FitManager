package fr.isika.cda.javaee.presentation.viewmodel;

import fr.isika.cda.javaee.entity.spaces.Configuration;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.entity.spaces.Style;

/**
 * ConfigurationViewModel for Configuration controller
 * 
 * @author Thibault
 *
 */
public class ConfigurationViewModel {

	private Configuration configuration;
	private Style style;
	private Space space;

	public ConfigurationViewModel() {
		this.configuration = new Configuration();
		this.style = new Style();
		this.space = new Space(true);
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}

}
