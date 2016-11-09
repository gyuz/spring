package crud.app;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.validator.routines.EmailValidator;
import org.joda.time.LocalDate;

import crud.core.model.PersonDto;
import crud.core.model.RoleDto;

public class PersonValidator implements Validator {

    @Override
    public boolean supports(Class c) {
        return PersonDto.class.equals(c);
    }

    @Override
    public void validate(Object command, Errors errors) {
    /*
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.username.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "field.phone.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.password.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rePassword", "field.rePassword.empty");
        RegistrationBean regBean = (RegistrationBean)command;
        if(!regBean.getPassword().equals(regBean.getRePassword()))
            errors.rejectValue("rePassword","password.notmatch");
        if(regBean.getPhone().trim().length() != 10)
            errors.rejectValue("phone","field.phone.length");
        if(!isNumber(regBean.getPhone().trim()))
            errors.rejectValue("phone", "field.phone.NAN");
            */
    }
/*
   protected boolean validDate(LocalDate date){
        LocalDate current = new LocalDate();
        if(date.getYear() < 1970 || date.getYear() > current.getYear()) {
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
    */
}

