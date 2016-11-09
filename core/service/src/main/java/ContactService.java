package crud.core.service;

import crud.core.model.Contact;
import crud.core.model.Person;

public interface ContactService extends GenericService<Contact> {
    public void setContactDetails(String type, String details, Person person);
    public void setContact(Contact contact);
    public void delete();  
    public void setDetail(String details);
    public Contact getContact();
    public String getContactDetails();
    public String getContactType();
}

