package crud.app;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import crud.core.service.PersonOperations;
import crud.core.service.RoleOperations;
import crud.core.service.DataParser;
import org.joda.time.LocalDate;
import org.apache.commons.validator.routines.EmailValidator;

public class PersonOps extends HttpServlet {
    public void doPost(HttpServletRequest request,
                  HttpServletResponse response)
    throws ServletException, IOException {
        PersonOperations personOps = new PersonOperations();
        RoleOperations roleOps = new RoleOperations();
        DataParser dataParser = new DataParser();
        RequestDispatcher dispatcher;
    
        String action = request.getParameter("action");
        int  id = dataParser.stringToInt(request.getParameter("personId"));
        String firstName = "";
        String lastName = "";
        String middleName = "";
        String title = "";
        String street = "";
        String brgy = "";
        String city = "";
        int zip = 0;
        char employed = 'N';
        LocalDate birthDate = null;
        LocalDate dateHired = null;
        double gwa = 0.0;
        int contactId = 0;
        String contactType = "";
        String contactDetail = "";
        int roleId = 0;
        String[] newContactTypes;
        String[] newContactDetails;
        String[] newRoles;
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        
        personOps.loadPerson(id);
        
        if ("DELETE".equals(action)){
            personOps.delete();
            personOps.closeSession();
            out.println("ID "+id+" deleted"); 
            dispatcher = request.getRequestDispatcher("PersonMain");
            dispatcher.include(request, response);  
        } else {
            if ("ADDCONT".equals(action)) {
                contactType = request.getParameter("contactType");
                contactDetail = request.getParameter("contactDetail");
                    if(validateContact(contactType, contactDetail)){
                        if (!personOps.addContact(contactType, contactDetail)){
                            out.println("Contact already exist!");
                        } else {
                            personOps.update();
                            personOps.closeSession();
                        }  
                    } else {
                        out.println("Invalid contact details");
                    }
                dispatcher = request.getRequestDispatcher("PersonDetails");
                dispatcher.include(request, response);
            } else if ("UPDCONT".equals(action)) {  
                contactId = dataParser.stringToInt(request.getParameter("contactId"));
                contactDetail = request.getParameter("contactDetail");
                if(personOps.contactIdExist(contactId)){
                     contactType = personOps.getContactType();
                     if(validateContact(contactType, contactDetail)){
                            if (!personOps.updateContact(contactDetail)){
                                out.println("Contact already exist!");
                            } 
                      } else {
                        out.println("Invalid contact details");
                    }
                  }
                dispatcher = request.getRequestDispatcher("PersonDetails");
                dispatcher.include(request, response);
            } else if ("DELCONT".equals(action)) {
                contactId = dataParser.stringToInt(request.getParameter("contactId"));
                if(personOps.contactIdExist(contactId)){
                    personOps.deleteContact();
                    personOps.closeSession();
                } 
                dispatcher = request.getRequestDispatcher("PersonDetails");
                dispatcher.include(request, response);
            } else if ("ADDROLE".equals(action)){
                roleId = dataParser.stringToInt(request.getParameter("roleId"));
                if (roleOps.idExist(roleId)) {
                   if (!personOps.addRole(roleOps.getRole())){
                        out.println("Role already exist for this person!");
                   } else {
                        personOps.update();
                        personOps.closeSession();
                   }  
                }
                dispatcher = request.getRequestDispatcher("PersonDetails");
                dispatcher.include(request, response);
            } else if ("DELROLE".equals(action)){
                roleId = dataParser.stringToInt(request.getParameter("roleId"));
                if (roleOps.idExist(roleId) && personOps.roleExistInSet(roleId)) {
                   personOps.deleteRole(roleOps.getRole());
                   personOps.closeSession();
                } else {
                   out.println("Role not found for this person"); 
                }
                dispatcher = request.getRequestDispatcher("PersonDetails");
                dispatcher.include(request, response);
            } else {
                firstName = request.getParameter("firstName").trim();
                lastName = request.getParameter("lastName").trim();
                middleName = request.getParameter("middleName").trim();
                title = request.getParameter("title");
                street = request.getParameter("street").trim();
                brgy = request.getParameter("brgy").trim();
                city = request.getParameter("city").trim();
                zip = dataParser.stringToInt(request.getParameter("zip").trim());
                employed = request.getParameter("employed").charAt(0);
                birthDate = dataParser.stringToDate(request.getParameter("birthDate").trim());
                dateHired = dataParser.stringToDate(request.getParameter("dateHired").trim());
                gwa = dataParser.stringToDouble(request.getParameter("gwa").trim());
                
                if ("CREATE".equals(action)) {
                    if ( birthDate == null || !validDate(birthDate)){
                        out.println("Invalid birth date!");
                    } else if (title == null) {
                        out.println("Title cannot be blank");
                    } else if (dateHired!=null && !validDate(dateHired)) {
                        out.println("Invalid date hired");
                    } else if (!alphabetOnly(firstName) || !alphabetOnly(middleName) || !alphabetOnly(lastName)) {
                        out.println("Lettes only for names!"); 
                    } else {
                        personOps = new PersonOperations();
                        roleOps = new RoleOperations();
                        if (personOps.isDuplicate(firstName, lastName, middleName)) {
                            out.println("Person already exist");
                        } else {
                            newContactTypes = request.getParameterValues("newContactType");
                            newContactDetails = request.getParameterValues("newContactDetail");
                            newRoles = request.getParameterValues("newRoleId");

                            if(newContactTypes != null){
                                for(int i = 0;i<newContactTypes.length; i++){
                                    contactType = newContactTypes[i];
                                    contactDetail = newContactDetails[i];
                                    if(validateContact(contactType, contactDetail)){
                                        if (!personOps.addContact(contactType, contactDetail)){
                                            out.println("Contact already added: "+contactDetail);
                                        }
                                    } else {
                                        out.println("Invalid contact details: "+contactDetail);
                                    } 
                                }
                            }
                            
                            if(newRoles != null){
                                for(String r: newRoles){
                                    roleId = dataParser.stringToInt(r);
                                    if (roleOps.idExist(roleId)) {
                                       if (!personOps.addRole(roleOps.getRole())){
                                            out.println("Duplicate adding: "+roleId);
                                       }
                                    }
                                }
                            }
                            personOps.savePerson(firstName, lastName, middleName, title, birthDate, street, brgy, city, zip, gwa, employed, dateHired);
                            personOps.add();
                            
                            out.println("<br>Person ID: "+personOps.getId()+" created");
                        }
                     }    
                     dispatcher = request.getRequestDispatcher("NewPerson");
                     dispatcher.include(request, response);    
                        
                } else if ("UPDATE".equals(action)) {
                        if ( birthDate == null || !validDate(birthDate)){
                            out.println("Invalid birth date!");
                        }  else if (title == null) {
                            out.println("Title cannot be blank");
                        } else if (dateHired!=null && !validDate(dateHired)) {
                            out.println("Invalid date hired");
                        } else if (!alphabetOnly(firstName) || !alphabetOnly(middleName) || !alphabetOnly(lastName)) {
                            out.println("Lettes only for names!"); 
                        } else {
                            personOps.savePerson(firstName, lastName, middleName, title, birthDate, street, brgy, city, zip, gwa, employed, dateHired);
                            personOps.update();
                            personOps.closeSession();
                            out.println("<br>Person Updated");
                        }
                        
                        dispatcher = request.getRequestDispatcher("PersonDetails");
                        dispatcher.include(request, response);
                 }
            }
        }
        out.close();
    }    
      
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
    
   
}
