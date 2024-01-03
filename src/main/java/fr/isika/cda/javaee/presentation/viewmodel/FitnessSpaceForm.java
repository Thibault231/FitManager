package fr.isika.cda.javaee.presentation.viewmodel;

import fr.isika.cda.javaee.entity.spaces.FitnessSpace;

public class FitnessSpaceForm {
	
	private FitnessSpace fitnessSpace;
	private String logo; 
	private String fitnessName; 
	private String planning;
	
	
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public String getFitnessName() {
		return fitnessName;
	}
	public void setFitnessName(String name) {
		this.fitnessName = name;
	}
	
	public String getPlanning() {
		return planning;
	}
	public void setPlanning(String planning) {
		this.planning = planning;
	}
	
	
	@Override
	public String toString() {
		StringBuilder  builder = new StringBuilder();
		builder.append("FitnessSpaceForm [logo=");
		builder.append(logo);
		builder.append(", fitnessName=");
		builder.append(fitnessName);
		builder.append(", planning=");
		builder.append(planning);
		builder.append("]");
		return builder.toString();
	}
		
	}
