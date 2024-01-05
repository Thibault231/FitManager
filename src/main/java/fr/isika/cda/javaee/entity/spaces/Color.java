package fr.isika.cda.javaee.entity.spaces;

import java.util.HashMap;
import java.util.Map;

public class Color {

	private static Map<String, String> colorsMap;

	public static Map<String, String> getColorsTemplate() {
		colorsMap = new HashMap<>();
		colorsMap.put("red", "##be2e14");
		colorsMap.put("blue", "##be2e14");
		colorsMap.put("orange", "#f0913c");
		colorsMap.put("green", "#4ebe1e");
		return colorsMap;
	}
	// Call with Ex: String a = Color.getColorsTemplate().get("red");

}
