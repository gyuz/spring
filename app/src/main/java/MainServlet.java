package crud.app;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {    
    public void doGet(HttpServletRequest request,
                  HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("PERSON".equals(action)) {
            response.sendRedirect("../PersonMain.jsp");
        } else if ("ROLE".equals(action)) {
            response.sendRedirect("../RoleDetails.jsp");
        }
    }
    
}
