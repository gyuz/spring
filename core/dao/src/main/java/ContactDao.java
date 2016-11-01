package crud.core.dao;

import crud.core.model.Contact;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ContactDao extends CrudImpl<Contact> implements ContactInterface {
    public ContactDao(){ 
    } 

    public void delete(Contact contact) {
        getSession().delete(contact);
	}

}
