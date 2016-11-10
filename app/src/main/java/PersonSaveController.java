package crud.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.validation.BindException;
import java.util.List;
import java.util.ArrayList;
import crud.core.service.PersonService;
import crud.core.service.RoleService;
import crud.core.model.PersonDto;
import crud.core.model.RoleDto;
import crud.core.service.DataParser;
import org.joda.time.LocalDate;
import org.apache.commons.validator.routines.EmailValidator;


@SuppressWarnings("deprecation")
public class PersonSaveController extends SimpleFormController {
	private PersonService personOps;
	private RoleService roleOps;
	private PersonDto personDto;
	private RoleDto roleDto;
	private DataParser dataParser;

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
	            
	            if(!updatedRoleIds.isEmpty()){
	                for(int i = 0; i<updatedRoleNames.size(); i++){
                        if(!updatedRoleNames.get(i).equals("") && updatedRoleIds.get(i).equals("")){
                            if(!personOps.addRole(roleOps.getRoleByName(updatedRoleNames.get(i)))){
                                errMsgs.add(updatedRoleNames.get(i) + " role already exist for this person");
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
                                if(validateContact(contactType, details)){
                                    if(!personOps.addContact(contactType, details)){
                                          errMsgs.add("Add Contact: " + contactType + " - " + details + " failed. Contact already exist!");
                                    } 
                                } else {
                                    errMsgs.add("Add Contact: " + contactType + " - " + details + " failed. Invalid contact details.");
                                }  
                            }  
                        } else {
                            if(!contactType.equals("0")) {
                                if(validateContact(contactType, details)){
                                    if(personOps.contactIdExist(dataParser.stringToInt(updatedContactIds.get(i)))){
                                        if(!personOps.updateContact(details)){
                                            errMsgs.add("Update Contact: " + contactType + " - " + details + " failed. Contact already exist!");
                                         } 
                                    } else {
                                            errMsgs.add("Update Contact: " + contactType + " - " + details + " failed. Invalid contact details.");
                                    }
                               }
                            }
                        }
                    }    
                }
            
            personOps.savePerson(dataParser.stringToDate(personDto.getBirthDate()), dataParser.stringToDate(personDto.getDateHired()), personDto);
        }
        
        personOps.entityToDto();
        List titleList = personOps.printTitleList();
        List contactTypes = personOps.printTypeList();
        roleDto = roleOps.printRoleList();
        mav.addObject("errMsgs", errMsgs);
        mav.addObject("titles", titleList);
        mav.addObject("typeList", contactTypes); 
        mav.addObject("roleDto", roleDto); 
        mav.addObject("personDto", personDto);  
        return mav;        
	}

    protected boolean alphabetOnly(String text){
       if(text.equals("") || !text.matches("[a-zA-Z ]*")) {
            return false;
        }   
        return true; 
    }
    protected boolean numericOnly(String number, int type){
        if(!number.matches(("[0-9]+")) || detailInvalid(number, type)) {
            return false;
        }    
        return true;
    }

    protected boolean validateEmail(String email){
        EmailValidator emailValidator = EmailValidator.getInstance();
        if(!emailValidator.isValid(email) || email.length() > 20) {
            return false;
        }    
        return true;
    }
    
    private boolean detailInvalid(String number, int type){
        if(type == 1 && number.length() != 7){
            return true;        
        } else if (type == 2 && number.length() != 11){
            return true;        
        }
        return false;
    }
    
    protected boolean validateContact(String contactType, String contactDetail){
        if(contactType.equals("LANDLINE") && !numericOnly(contactDetail, 1)) {
            return false;
        } else if (contactType.equals("MOBILE") && !numericOnly(contactDetail, 2)){
            return false;
        } else if (contactType.equals("EMAIL") && !validateEmail(contactDetail)) { 
            return false;
        }
        return true;
    }
    
}
