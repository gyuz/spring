package crud.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.validation.BindException;
import java.util.List;
import crud.core.service.RoleService;
import crud.core.model.RoleDto;
import crud.core.service.DataParser;

@SuppressWarnings("deprecation")
public class RoleSaveController extends SimpleFormController {
	private RoleService roleOps;
	private RoleDto roleDto;
	private DataParser dataParser;

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
    
    public void setDataParser(DataParser dataParser) {
        this.dataParser = dataParser;
    }
    
    @Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException {
	    ModelAndView mav = new ModelAndView("RoleDetails");
	    String errMsgs = "";
	    String successMsgs = "";
	    String[] deletedRoles = request.getParameterValues("roleDetailsDeleted");
	    if(deletedRoles != null){
	        for(String r: deletedRoles) {
	            System.out.println(r + " " + dataParser.stringToInt(r));
                if(!roleOps.delete(dataParser.stringToInt(r))){
                    errMsgs = errMsgs + "Role# "+r+"cannot be deleted";
                }
            }
	    }
	    RoleDto roleDtoUpdated = (RoleDto) command;
	    roleDto = roleOps.printRoleList();
	    
	    List<String> updatedId = roleDtoUpdated.getRoleIdList();
	    List<String> updatedRoles = roleDtoUpdated.getRoleNameList();
	    List<String> dbRoles = roleDto.getRoleNameList();
	    
	    if(!updatedRoles.equals(dbRoles)) {
	        for(int i = 0; i<updatedId.size(); i++) {
	            System.out.println(updatedId.get(i) + " "+updatedRoles.get(i));
	            String roleName = updatedRoles.get(i).toUpperCase().trim();
	            int id = dataParser.stringToInt(updatedId.get(i));
	            if(!roleName.equals("")) {
	                if(id != 0 && !dbRoles.contains(roleName)) {
	                    roleOps.update(id, roleName);   
	                } else if(id == 0 && !dbRoles.contains(roleName)) {
	                    roleOps.add(roleName);   
	                }
	            }
	        }
	    }
	    
	    roleDto = roleOps.printRoleList();
	    System.out.println(roleDto.getRoleIdList() + " " + roleDto.getRoleNameList());
        mav.addObject("roleDto", roleDto);
       // mav.addObject("errMsgs", errMsgs);
        return mav;        
	}
}
