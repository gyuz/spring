package crud.core.dao;

import crud.core.model.Contact;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ContactDao extends CrudImpl<Contact> implements ContactInterface {
    public ContactDao(){ 
    } 

    public void delete(Contact contact) {
        Session session2 = sessionGroup.getSession();
        Transaction tx = session2.beginTransaction();
        session2.delete(contact);
        tx.commit();
        session2.close();
	}

}
