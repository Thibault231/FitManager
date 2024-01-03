package fr.isika.cda.javaee.presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.isika.cda.javaee.presentation.util.SalleUtils;

@WebServlet("/espace/*")
public class AccueilSalleController {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Faire une logique pour récupérer l'ID de l'espace des salles de sport à partir de l'URL
		String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");
		String id = pathParts[1]; 
		
		//Logique pour récup les données de la salle avec l'ID donné et afficher la page d'accueil correspondante
		String pageSalle = SalleUtils.afficherPageSalle(id);
		
		request.getRequestDispatcher("/WEB-INF/" + pageSalle + ".html").forward(request, response);
	}
	
			
}
