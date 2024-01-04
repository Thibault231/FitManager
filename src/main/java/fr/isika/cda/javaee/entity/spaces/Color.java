package fr.isika.cda.javaee.entity.spaces;

import java.util.ArrayList;
import java.util.List;

public class Color {

	private static String red = "##be2e14";
	private static String blue = "#33acff";
	private static String orange = "#f0913c";
	private static String vert = "#4ebe1e";

	public static List<String> createColorList() {
		List<String> colorList = new ArrayList<>();

		// Ajouter des couleurs au format hexadécimal à la liste
		colorList.add(red);
		colorList.add(blue);
		colorList.add(orange);
		colorList.add(vert);

		return colorList;
	}

	public static String getRed() {
		return red;
	}

	public static void setRed(String red) {
		Color.red = red;
	}

	public static String getBlue() {
		return blue;
	}

	public static void setBlue(String blue) {
		Color.blue = blue;
	}

	public static String getOrange() {
		return orange;
	}

	public static void setOrange(String orange) {
		Color.orange = orange;
	}

	public static String getVert() {
		return vert;
	}

	public static void setVert(String vert) {
		Color.vert = vert;
	}

}
