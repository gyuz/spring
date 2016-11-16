package crud.core.service;

import java.util.Set;
import java.util.LinkedHashSet;
import crud.core.model.Contact;
import crud.core.model.Person;
import crud.core.model.Types;
import crud.core.dao.ContactInterface;
import crud.core.dao.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactOperations extends GenericServiceImpl<Contact> implements ContactService {
    private ContactInterface contactDao;
    private Contact contact;
    
    @Autowired
    public ContactOperations(
            @Qualifier("contactDao") CrudInterface<Contact> genericDao) {
        super(genericDao);
        this.contactDao = (ContactInterface) genericDao;
    }
    
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
        contact = new Contact();          
        contact.setContactType(Types.valueOf(type));
        contact.setDetails(details);
        contact.setPerson(person); 
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
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
