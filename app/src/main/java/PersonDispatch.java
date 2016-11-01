package crud.app;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PersonDispatch extends HttpServlet {
    public void doGet(HttpServletRequest request,
                  HttpServletResponse response)
    throws ServletException, IOException{
        String action = request.getParameter("action");
        RequestDispatcher dispatcher;
        
        if ("BACK".equals(action)) {
            response.sendRedirect("../index.html");
        } else if ("BACKP".equals(action)) {
            response.sendRedirect("PersonMain");
        } else if ("CREATE".equals(action)) {
            response.sendRedirect("NewPerson");
        } else if ("SEARCH".equals(action)) {
            dispatcher = request.getRequestDispatcher("PersonDetails");
            dispatcher.forward(request, response);
        } else if ("LIST".equals(action)) {
            dispatcher = request.getRequestDispatcher("PersonList");
            dispatcher.forward(request, response);
        }
    }
    
}
