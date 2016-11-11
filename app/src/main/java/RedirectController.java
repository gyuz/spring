package crud.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

@SuppressWarnings("deprecation")
public class RedirectController extends SimpleFormController {

    @Override
   public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String view = request.getParameter("view");;
        return new ModelAndView(view);
    }
}
