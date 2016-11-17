package crud.app;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;
import org.apache.commons.validator.routines.EmailValidator;
import org.joda.time.LocalDate;
import org.apache.log4j.Logger;
import java.util.List;
import crud.core.model.PersonDto;
import crud.core.model.RoleDto;

@Component
public class RolePersonValidator implements Validator {
    private static final Logger logger = Logger.getLogger(RolePersonValidator.class);
    
    @Override
    public boolean supports(Class c) {
        return PersonDto.class.equals(c) || RoleDto.class.equals(c);
    }

    @Override
    public void validate(Object command, Errors errors) {
        if(command instanceof PersonDto){
            PersonDto personDto = (PersonDto) command;
            List<String> updatedContactIds = personDto.getPersonContactIds();
	        List<String> updatedContactTypes = personDto.getPersonContactTypes();
	        List<String> updatedContactDetails = personDto.getPersonContactDetails();
	        
            if(!alphabetOnly(personDto.getFirstName().trim())){
                logger.error("Error in first name: "+ personDto.getFirstName(), new Exception("PersonDetails"));
                errors.rejectValue("firstName", "invalid.name");
            }
            if(!alphabetOnly(personDto.getMiddleName().trim())){
                logger.error("Error in middle name: "+ personDto.getMiddleName(), new Exception("PersonDetails"));
                errors.rejectValue("middleName", "invalid.name");
            }
            if(!alphabetOnly(personDto.getLastName().trim())){
                logger.error("Error in last name: "+ personDto.getLastName(), new Exception("PersonDetails"));
                errors.rejectValue("lastName", "invalid.name");
            }
            if(personDto.getBirthDate() == null || !validDate(personDto.getBirthDate())){
                logger.error("Error in birth date: "+ personDto.getBirthDate(), new Exception("PersonDetails"));
                errors.rejectValue("birthDate", "invalid.date");
            } 
            if(personDto.getDateHired() != null && !validDate(personDto.getDateHired())){
                logger.error("Error in date hired: "+ personDto.getDateHired(), new Exception("PersonDetails"));
                errors.rejectValue("hiredDate", "invalid.date");
            }
            
            if(!updatedContactIds.isEmpty()){
	            for(int i = 0; i<updatedContactIds.size(); i++){
	                String contactType = updatedContactTypes.get(i);
	                String details = updatedContactDetails.get(i);
	                if(contactType.equals("0") && !(details.equals(""))){
	                    logger.error("Error in contact details: Empty contact type for detail " + details , new Exception("PersonContact"));
	                    errors.rejectValue("PersonContactTypes", "invalid.contact");
	                    break;
	                } else if(!contactType.equals("0")){
                        if(!validateContact(contactType, details)){
                            logger.error("Error in contact details: " + contactType + ", " + details , new Exception("PersonContact"));
                            errors.rejectValue("PersonContactDetails", "invalid.contact");
                            break;
                        } 
                    }
                }
            }    
        }
    }
    
   protected boolean validDate(LocalDate date){
        LocalDate current = new LocalDate();
        if(date == null || date.getYear() < 1970 || date.getYear() > current.getYear()) {
            return false;
        }    
        return true;
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

