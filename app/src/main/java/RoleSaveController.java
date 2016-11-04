package crud.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import crud.core.service.RoleService;
import crud.core.service.RoleDto;

@SuppressWarnings("deprecation")
public class RoleSaveController extends SimpleFormController {
	private RoleService roleOps;
	private RoleDto roleDto;

	public RoleSaveController() {
		setCommandClass(RoleDto.class);
		setCommandName("roleDto");
	}

	public void setRoleService(RoleService roleOps) {
		this.roleOps = roleOps;
	}
    
    public void setRole(RoleDto roleDto) {
        this.roleDto = roleDto;
    }
    
    @Override
	protected ModelAndView onSubmit(Object command) throws Exception {
	    roleDto = (RoleDto) command;
	    System.out.println("onSubmit");
        /*
        if ("UPDATE".equals(action)) {
	            if(roleOps.isDuplicate(roleDto.getRoleName())){
                    System.out.println("Error duplicate role");	            
	            } else {
	                roleOps.addRole(roleDto.getRoleName());
	            }
	        } else if ("DELETE".equals(action)) {
	            roleOps.DtoToEntity(roleDto);
	            if(!roleOps.deleteRole()){
                    System.out.println("unable to delete role");
                }
	        }*/
		
        return new ModelAndView("RoleDetails", "roleDto", roleDto);        
	}
}
