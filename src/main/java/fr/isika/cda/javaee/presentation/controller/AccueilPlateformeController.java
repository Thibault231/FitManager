package fr.isika.cda.javaee.presentation.controller;

import javax.inject.Named;

@Named
public class AccueilPlateformeController {
	
	
	public String AfficherPageAcceuil() {
		
		return "accueilPlateforme";
	}
	

}