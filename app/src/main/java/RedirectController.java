package crud.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Locale;

@Controller
public class RedirectController{

   @RequestMapping(value = "/redirect", method = RequestMethod.GET)
   public String redirect (@RequestParam(value="view", required = true) String view) {
        return view;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPage() {
        return "index";
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
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		} 
		
		model.setViewName("login");
        
		return model;

	}
	
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}
 
    
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "accessDenied";
    }
    
     private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
