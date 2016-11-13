package crud.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.validation.BindException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import crud.core.service.PersonService;
import crud.core.service.RoleService;
import crud.core.service.DataParser;
import crud.core.model.PersonDto;
import crud.core.model.RoleDto;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.validation.Errors;
import org.apache.log4j.Logger;


@SuppressWarnings("deprecation")
public class PersonSaveController extends SimpleFormController {
	private PersonService personOps;
	private RoleService roleOps;
	private PersonDto personDto;
	private RoleDto roleDto;
	private DataParser dataParser;
	
	private static final Logger logger = Logger.getLogger(PersonSaveController.class);

	public PersonSaveController() {
		setCommandClass(PersonDto.class);
		setCommandName("personDto");
	}

	public void setPersonService(PersonService personOps) {
		this.personOps = personOps;
	}
    
    public void setPersonDto(PersonDto personDto) {
        this.personDto = personDto;
    }
    
    public void setDataParser(DataParser dataParser) {
        this.dataParser = dataParser;
    }
    
    public void setRoleService(RoleService roleOps) {
		this.roleOps = roleOps;
	}
	
	public void setRoleDto(RoleDto roleDto) {
	    this.roleDto = roleDto;
	}
    
    @Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException {
	    ModelAndView mav = new ModelAndView("PersonDetails");
	    List errMsgs = new ArrayList();
	    String[] deletedContacts = request.getParameterValues("contactsDeleted");
	    String[] deletedRoles = request.getParameterValues("rolesDeleted");
	    personDto = (PersonDto) command;
	    List<String> updatedRoleIds = personDto.getPersonRoleIds();
	    List<String> updatedRoleNames = personDto.getPersonRoleNames();
	    List<String> updatedContactIds = personDto.getPersonContactIds();
	    List<String> updatedContactTypes = personDto.getPersonContactTypes();
	    List<String> updatedContactDetails = personDto.getPersonContactDetails();
        int id = dataParser.stringToInt(request.getParameter("id"));
        
        if(!errors.hasErrors()){ 
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
	        
	        personOps.saveDetails(dataParser.stringToDate(personDto.getBirthDate()), dataParser.stringToDate(personDto.getDateHired()), personDto);
	            
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
                                logger.error(updatedRoleNames.get(i) + " contact already exist for this person", new Exception("PersonContact"));
                            } 
                        }  
                    } else {
                        if(!contactType.equals("0")) {
                            if(personOps.contactIdExist(dataParser.stringToInt(updatedContactIds.get(i)))){
                                if(!personOps.updateContact(details)){
                                    errMsgs.add("Update Contact: " + contactType + " - " + details + " failed. Contact already exist!");
                                    logger.error(updatedRoleNames.get(i) + " contact already exist for this person", new Exception("PersonContact"));
                                } 
                            }
                        }
                    }
                }    
            }
            
            personOps.savePerson();     
        }
        
        personOps.entityToDto();
        List titleList = personOps.printTitleList();
        List contactTypes = personOps.printTypeList();
        roleDto = roleOps.printRoleList();
        mav.addObject("titles", titleList);
        mav.addObject("typeList", contactTypes); 
        mav.addObject("roleDto", roleDto); 
        mav.addObject("errMsgs", errMsgs);
        mav.addObject("personDto", personDto);  
        return mav;        
	}
	
	@Override
	protected Map referenceData(HttpServletRequest request, Object command, Errors errors) 
	    throws Exception {

		Map referenceData = new HashMap();
		List titleList = personOps.printTitleList();
        List contactTypes = personOps.printTypeList();
        roleDto = roleOps.printRoleList();
		referenceData.put("titles", titleList);
		referenceData.put("typeList", contactTypes);
		referenceData.put("roleDto", roleDto);
		return referenceData;
	}
    
}
