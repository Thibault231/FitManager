package fr.isika.cda.javaee.presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.isika.cda.javaee.presentation.util.SalleUtils;

//@WebServlet("/espace/*")
public class AccueilSalleController {

	private String spaceId;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Faire une logique pour récupérer l'ID de l'espace des salles de sport à partir de l'URL
		//pathInfo est les chaîne de caractères qui composent l'url
		//pathParts est un tableau fictif 
		//le pathParts est divisé avec le split
		String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");
		
		// Le tableau pathParts a au moins deux éléments
		if (pathParts.length >= 2) {
			 spaceId = pathParts[1]; 
			
			// Logique pour récupérer les données de la salle avec l'ID donné
			String pageSalle = SalleUtils.afficherPageSalle();
			
			request.getRequestDispatcher("/WEB-INF/" + pageSalle + ".html").forward(request, response);
		} else {
			// Redirection vers une page d'erreur si l'ID n'est pas présent dans l'URL
			response.sendRedirect("/erreur.html");
		}
	}

}
