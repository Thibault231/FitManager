package fr.isika.cda.javaee.presentation.controller;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class AccueilPlateformeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<html><head><title>Accueil</title></head><body>");
        response.getWriter().println("<h1>Bienvenue sur la page d'accueil</h1>");
        // Ajoutez le contenu de votre page d'accueil ici
        response.getWriter().println("</body></html>");
    }
}