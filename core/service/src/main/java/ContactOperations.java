package crud.core.service;

import java.util.Set;
import java.util.LinkedHashSet;
import crud.core.model.Contact;
import crud.core.model.Person;
import crud.core.model.Types;
import crud.core.dao.ContactDao;

public class ContactOperations extends GenericServiceImpl<Contact> implements ContactService {
    protected Contact contact;
    private ContactDao contactDao;
    
    public ContactOperations(){
        contact = new Contact();
        contactDao =  new ContactDao();    
    }

    public void setContactDetails(String type, String details, Person person){
        contact = new Contact();        
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

