package crud.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

@SuppressWarnings("deprecation")
public class IndexController extends SimpleFormController {
    
	protected ModelAndView showForm(HttpServletRequest request, 
	        HttpServletResponse response) throws Exception {
	    String action = request.getParameter("action");
        
        if ("PERSON".equals(action)) {
            response.sendRedirect("../PersonMain.jsp");
        } else if ("ROLE".equals(action)) {
            return new ModelAndView("RoleController");
        }
        
        return new ModelAndView("PersonMain");
	}
}
