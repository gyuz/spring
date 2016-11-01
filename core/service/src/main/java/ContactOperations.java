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
    
    @Autowired
    public ContactOperations(
            @Qualifier("contactDao") CrudInterface<Contact> crudInterface) {
        super(crudInterface);
        this.contactDao = (ContactInterface) crudInterface;
    }
   
}

