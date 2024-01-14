package fr.isika.cda.javaee.presentation.controller.spaces;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/espace/*")
public class RedirectToSpace {
	private String spaceId;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");

		if (pathParts.length >= 2) {
			spaceId = pathParts[1];

			String pageSalle = SalleUtils.afficherPageSalle();

			request.getRequestDispatcher("/WEB-INF/" + pageSalle + ".html").forward(request, response);
		} else {
			// Redirection vers une page d'erreur si l'ID n'est pas prÃ©sent dans l'URL
			response.sendRedirect("/erreur.html");
		}
	}
}
