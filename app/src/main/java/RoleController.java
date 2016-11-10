package crud.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.validation.BindException;
import crud.core.service.RoleService;
import crud.core.model.RoleDto;

@SuppressWarnings("deprecation")
public class RoleController extends SimpleFormController {
	private RoleService roleOps;
	private RoleDto roleDto;

	public RoleController() {
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
   public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        roleDto = roleOps.printRoleList();
        return new ModelAndView("RoleDetails", "roleDto", roleDto);
    }
}
