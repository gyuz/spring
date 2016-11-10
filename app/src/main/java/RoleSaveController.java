package crud.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.validation.BindException;
import java.util.List;
import java.util.ArrayList;
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
	    List errMsgs = new ArrayList();
	    List successMsgs = new ArrayList();
	    List success = new ArrayList();
	    RoleDto roleDtoUpdated = (RoleDto) command;
	    roleDto = roleOps.printRoleList();
	    List<String> updatedId = roleDtoUpdated.getRoleIdList();
	    List<String> updatedRoles = roleDtoUpdated.getRoleNameList();
	    List<String> dbRoles = roleDto.getRoleNameList();
	    List<String> dbIds = roleDto.getRoleIdList();
	    String[] deletedRoles = request.getParameterValues("rolesDeleted");
	    
	    if(deletedRoles != null){
	        for(String r: deletedRoles) {
                if(!roleOps.delete(dataParser.stringToInt(r))){
                    errMsgs.add("Error deleting role#"+r+". Role may still be associated with a person");
                } else {
                    success.add(r);
                }
            }
            if(!success.isEmpty()) {
                successMsgs.add("Successfully deleted: "+success);
                success = new ArrayList();
            }
	    }
	    
	    if(!updatedRoles.equals(dbRoles)) {
	        for(int i = 0; i<updatedId.size(); i++) {
	            String roleName = updatedRoles.get(i).toUpperCase().trim();
	            int id = dataParser.stringToInt(updatedId.get(i));
	            if(!roleName.equals("")) {
	                if(id != 0 && !dbRoles.contains(roleName)) {
	                    roleOps.update(id, roleName);  
	                    successMsgs.add("Role id#"+id+" updated with new role name: "+roleName);
	                } else if(id == 0) {
	                    if(!dbRoles.contains(roleName)) {
	                        roleOps.add(roleName); 
	                        success.add(roleName);
	                    } else {
                            errMsgs.add("Role "+roleName+" already exists!");
                        }   
	                } else {
                        if(!dbIds.get(dbRoles.indexOf(roleName)).equals(updatedId.get(i))){
                            errMsgs.add("Update failed. Role "+roleName+" already exists!");
                        }
                    } 
	            }
	        }
	    }
	    
	    if(!success.isEmpty()) {
            successMsgs.add("Successfully added role(s): "+success);
        }
	    
	    roleDto = roleOps.printRoleList();
        mav.addObject("roleDto", roleDto);
        mav.addObject("errMsgs", errMsgs);
        mav.addObject("successMsgs", successMsgs);
        return mav;        
	}
}
