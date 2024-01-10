//package fr.isika.cda.javaee.entity.spaces;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//
//@Entity
//public class Color {
//
//	@Id
//	@GeneratedValue
//	private long id;
//
//	private Map<String, String> colorsMap;
//
//	public Map<String, String> getColorsTemplate() {
//		colorsMap = new HashMap<>();
//		colorsMap.put("red", "##be2e14");
//		colorsMap.put("blue", "##be2e14");
//		colorsMap.put("orange", "#f0913c");
//		colorsMap.put("green", "#4ebe1e");
//		return colorsMap;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public Map<String, String> getColorsMap() {
//		return colorsMap;
//	}
//
//	public void setColorsMap(Map<String, String> colorsMap) {
//		this.colorsMap = colorsMap;
//	}
//
//}
