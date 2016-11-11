package crud.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.support.RequestContextUtils;
import java.util.Locale;

@SuppressWarnings("deprecation")
public class LocaleController extends SimpleFormController {

    @Override
   public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String language = request.getParameter("lang");
        if(language != null){
            if(language.equals("en")){    
                RequestContextUtils.getLocaleResolver(request).setLocale(request, response, Locale.ENGLISH);
            } else {
                RequestContextUtils.getLocaleResolver(request).setLocale(request, response, Locale.CHINA);
            }
        } else {
            RequestContextUtils.getLocaleResolver(request).setLocale(request, response, Locale.CHINA);
        }
        return new ModelAndView("index");
    }
}
