package crud.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import crud.core.service.RoleOperations;
import crud.core.service.RoleDto;

@SuppressWarnings("deprecation")
public class RoleController extends SimpleFormController {
	private RoleOperations roleOps;

	public RoleController() {
		setCommandClass(RoleDto.class);
		setCommandName("role");
	}

	public void setRoleOpertaions(RoleOperations roleOps) {
		this.roleOps = roleOps;
	}
    
	protected ModelAndView onSubmit(HttpServletRequest request, 
	                HttpServletResponse response, Object command) throws Exception {
		RoleDto role = (RoleDto) command;
		String action = request.getParameter("action");
        
         if ("BACK".equals(action)) {
            response.sendRedirect("index.html");
        } else {
            if ("UPDATE".equals(action)) {
	            if(roleOps.isDuplicate(role.getRoleName())){
                    System.out.println("Error duplicate role");	            
	            } else {
	                roleOps.addRole(role.getRoleName());
	            }
	        } else if ("DELETE".equals(action)) {
	            roleOps.DtoToEntity(role);
	            if(!roleOps.deleteRole()){
                    System.out.println("unable to delete role");
                }
	        }
		}
        return new ModelAndView("RoleDetails", "role", role);        
	}
}
