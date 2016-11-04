package crud.core.service;

import java.util.Set;
import java.util.LinkedHashSet;
import crud.core.model.Contact;
import crud.core.model.Person;
import crud.core.model.Types;
import crud.core.dao.ContactInterface;
import crud.core.dao.CrudInterface;

public class ContactOperations extends GenericServiceImpl<Contact> implements ContactService {
    private ContactInterface contactDao;
    private Contact contact;
    
    public ContactInterface getContactInterface(){
        return contactDao;
    }
    
    public void setContactInterface(ContactInterface contactDao){
        this.contactDao = contactDao;
    }
    
    public Contact getContact(){
        return contact;
    }
    
    public void setContact(Contact contact){
        this.contact = contact;
    }
    
    
    public void setContactDetails(String type, String details, Person person){      
        contact.setContactType(Types.valueOf(type));
        contact.setDetails(details);
        contact.setPerson(person); 
    }
    
    public void delete(){
        contactDao.delete(contact);    
    }  

    public void setDetail(String details){
        contact.setDetails(details);  
    }
    
    public String getContactDetails(){
        return contact.getDetails();    
    }

    public String getContactType(){
        return contact.getContactType().toString();    
    }
}
