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
import crud.core.service.RoleService;
import crud.core.model.RoleDto;
import crud.core.service.DataParser;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.ArrayList;

@Controller
public class RoleController {
	private RoleService roleOps;
	private RoleDto roleDto;
    private static final Logger logger = Logger.getLogger(RoleController.class);
    private DataParser dataParser;
    
    @Autowired
	public void setRoleService(RoleService roleOps) {
		this.roleOps = roleOps;
	}
    
    @Autowired
    public void setRole(RoleDto roleDto) {
        this.roleDto = roleDto;
    }
    
    @Autowired
    public void setDataParser(DataParser dataParser) {
        this.dataParser = dataParser;
    }
    
    @RequestMapping(value = "/role", method = RequestMethod.GET) 
    public String listRoles(Model model) {
        model.addAttribute("roleDto", this.roleOps.printRoleList());
        return "RoleDetails";
    }
    
    @RequestMapping(value = "/roleSave", method = RequestMethod.POST) 
    public String saveChanges(@ModelAttribute("roleDto") RoleDto roleDtoUpdated, HttpServletRequest request, Model model){
        List errMsgs = new ArrayList();
	    List successMsgs = new ArrayList();
	    List success = new ArrayList();
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
                    logger.error("Error deleting role#"+r+". Role may still be associated with a person", new Exception("RoleDelete"));
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
                            logger.error("Role "+roleName+" already exists!", new Exception("RoleExists"));
                        }   
	                } else {
                        if(!dbIds.get(dbRoles.indexOf(roleName)).equals(updatedId.get(i))){
                            errMsgs.add("Update failed. Role "+roleName+" already exists!");
                            logger.error("Role "+roleName+" already exists!", new Exception("RoleExists"));
                        }
                    } 
	            }
	        }
	    }
	    
	    if(!success.isEmpty()) {
            successMsgs.add("Successfully added role(s): "+success);
        }
	    
	    roleDto = roleOps.printRoleList();
        model.addAttribute("roleDto", roleDto);
        model.addAttribute("errMsgs", errMsgs);
        model.addAttribute("successMsgs", successMsgs);
        return "RoleDetails";        
    }
}
