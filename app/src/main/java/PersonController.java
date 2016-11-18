package crud.app;

import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.log4j.Logger;
import crud.core.service.PersonService;
import crud.core.service.RoleService;
import crud.core.service.DataParser;
import crud.core.model.PersonDto;
import crud.core.model.RoleDto;
import java.util.List;
import java.util.ArrayList;

@Controller
public class PersonController {
	private PersonService personOps;
	private RoleService roleOps;
	private DataParser dataParser;
	private PersonDto personDto;
	private RoleDto roleDto;
	private static final Logger logger = Logger.getLogger(PersonController.class);
    private RolePersonValidator personValidator;

    @Autowired
	public void setPersonService(PersonService personOps) {
		this.personOps = personOps;
	}
	
	@Autowired
	public void setPersonDto(PersonDto personDto) {
	    this.personDto = personDto;
	}
	
	@Autowired
	public void setRoleService(RoleService roleOps) {
		this.roleOps = roleOps;
	}
	
	@Autowired
	public void setRoleDto(RoleDto roleDto) {
	    this.roleDto = roleDto;
	}
	
	@Autowired
	public void setDataParser(DataParser dataParser) {
	    this.dataParser = dataParser;
	}
	
	@Autowired
	public void setRolePersonValidator(RolePersonValidator personValidator){
	    this.personValidator = personValidator;
	}
	
	@RequestMapping(value = "/person", method = RequestMethod.GET) 
    public String personRedirect(@RequestParam(value="action", required = true) String action,
                                 @RequestParam(value="list", required = false) String list,
                                 @RequestParam(value="order", required = false) String order,
                                 @RequestParam(value="personId", required = false, defaultValue = "0") int  id,
                                 Model model) {
        List errMsgs = new ArrayList();
        List successMsgs = new ArrayList();
        if ("SEARCH".equals(action)) {
            if(id != 0){
                if(!personOps.idExist(id)){
                    errMsgs.add("ID#"+id+" does not exist!");
                    model.addAttribute("errMsgs", errMsgs);
                    return "PersonMain";   
                } else {
                    personDto = personOps.getPersonDto();
                    model.addAttribute("list", list);
                    model.addAttribute("order", order);
                    model.addAttribute("personDto", personDto);
                }
            } else {
                errMsgs.add("Enter ID number to search");
                model.addAttribute("errMsgs", errMsgs);
                return "PersonMain"; 
            }  
        } else if ("LIST".equals(action) || "DELETE".equals(action)) {
            if("DELETE".equals(action)){
                personOps.delete(id);  
                successMsgs.add("Successfully deleted Person ID#"+id);
                model.addAttribute("successMsgs", successMsgs); 
            }
            
            model.addAttribute("list", list);
            model.addAttribute("order", order);
            model.addAttribute("personDto", this.personOps.printPersonList(dataParser.stringToInt(list), dataParser.stringToInt(order)));  
            return "PersonList";   
        } 
        return "PersonDetails";
    }
    
    @RequestMapping(value = "/personSave", method = RequestMethod.POST) 
    public String saveChanges(@RequestParam(value="contactsDeleted", required = false) String[] deletedContacts,
                              @RequestParam(value="rolesDeleted", required = false) String[] deletedRoles,
                              @RequestParam(value="id", required = false, defaultValue = "0") int  id,
                              @ModelAttribute("personDto") PersonDto personDto, 
                              BindingResult result,
                              Model model){
        personValidator.validate(personDto, result);
        if(!result.hasErrors()){ 
            List errMsgs = new ArrayList();
	        List<String> updatedRoleIds = personDto.getPersonRoleIds();
	        List<String> updatedRoleNames = personDto.getPersonRoleNames();
	        List<String> updatedContactIds = personDto.getPersonContactIds();
	        List<String> updatedContactTypes = personDto.getPersonContactTypes();
	        List<String> updatedContactDetails = personDto.getPersonContactDetails();
        
            if(id != 0) { 
                if(deletedRoles != null){
	                for(String r: deletedRoles) {
	                    int roleId = dataParser.stringToInt(r);
	                    if(roleId != 0){
                             if (roleOps.idExist(roleId) && personOps.roleExistInSet(roleId)) {
                                personOps.deleteRole(roleOps.getRole());
                             }
                        }
                    }
	            }
	            
	            if(deletedContacts != null){
	                for(String r: deletedContacts) {
	                    int contactId = dataParser.stringToInt(r);
	                    if(contactId != 0){
                            if(personOps.contactIdExist(contactId)){
                                personOps.deleteContact();
                            } 
                        }
                    }
	            }
	        }
	        
	        personOps.saveDetails(personDto);
	            
	        if(!updatedRoleIds.isEmpty()){
	            for(int i = 0; i<updatedRoleNames.size(); i++){
                    if(!updatedRoleNames.get(i).equals("") && updatedRoleIds.get(i).equals("")){
                        if(!personOps.addRole(roleOps.getRoleByName(updatedRoleNames.get(i)))){
                           errMsgs.add(updatedRoleNames.get(i) + " role already exist for this person");
                           logger.error(updatedRoleNames.get(i) + " role already exist for this person", new Exception("PersonRole"));
                        }
                    }
                }    
            }
            
            if(!updatedContactIds.isEmpty()){
	            for(int i = 0; i<updatedContactIds.size(); i++){
	                String contactType = updatedContactTypes.get(i);
	                String details = updatedContactDetails.get(i);
	                if(updatedContactIds.get(i).equals("")){
                        if(!contactType.equals("0")){
                            if(!personOps.addContact(contactType, details)){
                                errMsgs.add("Add Contact: " + contactType + " - " + details + " failed. Contact already exist!");
                                logger.error(contactType + " - " + details + " contact already exist for this person", new Exception("PersonContact"));
                            } 
                        }  
                    } else {
                        if(!contactType.equals("0")) {
                            if(personOps.contactIdExist(dataParser.stringToInt(updatedContactIds.get(i)))){
                                if(!personOps.updateContact(details)){
                                    errMsgs.add("Update Contact: " + contactType + " - " + details + " failed. Contact already exist!");
                                    logger.error(contactType + " - " + details + " contact already exist for this person", new Exception("PersonContact"));
                                } 
                            }
                        }
                    }
                }    
            }
            
            personOps.savePerson(); 
            personOps.entityToDto(); 
            model.addAttribute("errMsgs", errMsgs);   
        }
        
        model.addAttribute("personDto", personDto);  
        return "PersonDetails";   
    } 
    
    @ModelAttribute("titles")
    public List<String> titleList(){
        return personOps.printTitleList();
    }
    
    @ModelAttribute("typeList")
    public List<String> typeList(){
        return personOps.printTypeList();
    }
    
    @ModelAttribute("roleDto")
    public RoleDto masterRoleList(){
        return roleOps.printRoleList();
    }
}
