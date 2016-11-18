package crud.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;
import java.util.Locale;

@Controller
public class RedirectController{

   @RequestMapping(value = "/redirect", method = RequestMethod.GET)
   public String redirect (@RequestParam(value="view", required = true) String view) {
        return view;
    }
    
   @RequestMapping(value = "/locale", method = RequestMethod.GET)
   public String changeLocale(HttpServletRequest request, HttpServletResponse response) {
        String view = request.getParameter("page");
        String language = request.getParameter("lang");
        
        if(language != null){
            if(language.equals("en")){    
                RequestContextUtils.getLocaleResolver(request).setLocale(request, response, Locale.ENGLISH);
            } else {
                RequestContextUtils.getLocaleResolver(request).setLocale(request, response, Locale.CHINA);
            }
        } else {
            RequestContextUtils.getLocaleResolver(request).setLocale(request, response, Locale.ENGLISH);
        }
        
        if(view == null){
            view = "index";
        } else if (view.equals("person")) {
            return "forward:/person";
        } else if (view.equals("role")) {
            return "forward:/role";
        }
        return view;
    }
}
