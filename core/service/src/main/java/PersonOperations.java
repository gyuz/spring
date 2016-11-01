package crud.core.service;

import crud.core.model.Person;
import crud.core.model.Contact;
import crud.core.model.Role;
import crud.core.model.Title;
import crud.core.model.Types;
import crud.core.dao.PersonInterface;
import crud.core.dao.CrudInterface;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonOperations extends GenericServiceImpl<Person> implements PersonService {   
    private PersonInterface personDao;
    private ContactOperations contactOps;
    
    @Autowired
    public PersonOperations(
            @Qualifier("personDao") CrudInterface<Person> crudInterface) {
        super(crudInterface);
        this.personDao = (PersonInterface) crudInterface;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean idExist(int id) {
       Person person = personDao.getPersonById(id);
       if (person != null) {
            return true;
       }
       
       return false;     
    }
    
    public boolean titleExist(String title){
        for (Title t : Title.values()) {
            if (t.name().equals(title)) {
                return true;
            }
        }
        return false;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Person loadPerson(int id){
        return personDao.getPersonById(id);
    }
   
    public boolean parseEmployed(char employed){
        if(employed == 'Y') return true;
        return false;  
    }    
   
    public void closeSession(){
        personDao.closeSession();
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean isDuplicate(String firstName, String lastName, String middleName){
        List<Person> personList = personDao.getList("Person where name.firstName = '"+firstName.toUpperCase()+"' AND name.lastName='"+lastName.toUpperCase()+"' AND name.middleName='"+middleName.toUpperCase()+"'"); 
       if(personList.isEmpty()) {
         return false;
       }
       return true;
    }
    
    public List printTitleList(){
       List titleList = new ArrayList<String>();
        for (Title t : Title.values()) {
            titleList.add(t.name());
        }
        return titleList; 
    }  
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)  
    public List printPersonList(int listChoice, int order){
       List<Person> personList = new ArrayList<Person>();
       
       if(listChoice == 1){
            personList = personDao.getList("Person"); 
            if(order == 1) {
                Collections.sort(personList);
            } else {
                Collections.sort(personList, Collections.reverseOrder());
            }
       } else if (listChoice == 2) {
            if (order == 1) {
                personList = personDao.listAscending("name.lastName");
            } else {
                personList = personDao.listDescending("name.lastName");
            }
       } else if (listChoice == 3) {
            if (order == 1){
                personList = personDao.listAscending("dateHired");
            } else {
                personList = personDao.listDescending("dateHired");
            }
       } else {
            if (order == 1){
                personList = personDao.listAscending("id");
            } else {
                personList = personDao.listDescending("id");
            }
       }
       
       return personList;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean addRole(Role role, int id){
        Person person = personDao.getPersonById(id);
        if(person.getRoles().contains(role)) { 
            return false;
        } else {
            person.getRoles().add(role);
        }
        return true;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteRole(Role role, int id){
        Person person = personDao.getPersonById(id);
        person.getRoles().remove(role); 
        personDao.update(person);    
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean roleExistInSet(int id, int roleId){
        Person person = personDao.getPersonById(id);
        Set<Role> roleSet = person.getRoles();
        for(Role r : roleSet){
          if(r.getRoleId() == roleId){
            return true;
          }
        }
        return false; 
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Set printPersonRoleList(int id){
        Person person = personDao.getPersonById(id);
        Set<Role> roleSet = person.getRoles();
        return roleSet; 
    }  
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean contactExist(Contact contact, int id){
        Person person = personDao.getPersonById(id);
        return person.getContacts().contains(contact);
    }
/*
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean addContact(String type, String detail, int id){
        Person person = personDao.getPersonById(id);
        //contactOps.setContactDetails(type, detail, person);
        //Contact contact = contactOps.contact;
        if(contactExist(contact)) {
            return false;
        } else {
             person.getContacts().add(contact);
        }
        return true;
    } 
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean updateContact(String detail, int id){
        Person person = personDao.getPersonById(id);
        Contact contact = contactOps.contact;
        person.getContacts().remove(contact);
        contactOps.setDetail(detail);
        contact = contactOps.contact;
        if(contactExist(contact)) {
            return false;
        } else {
            person.getContacts().add(contact);
            personDao.update(person);   
        }
        return true;
    }
    */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteContact(Contact contact, int id){
        Person person = personDao.getPersonById(id);
        person.getContacts().remove(contact);
        contactOps.delete(contact);
        personDao.update(person);   
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean contactIdExist(int contactId, int id){
        Person person = personDao.getPersonById(id);
        Set<Contact> contactSet = person.getContacts();
        for(Contact c : contactSet){
          if(c.getContactId() == contactId) {
            return true;
          }
        } 
        return false;
    }
    
    public List printTypeList(){
        int ctr = 1;
        List typeList = new ArrayList<String>();
        for (Types t : Types.values()) {
            typeList.add(t.name());
        }
        return typeList; 
    }  
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Set printContactList(int id){
        Person person = personDao.getPersonById(id);
        Set<Contact> contactSet = person.getContacts();
        return contactSet; 
    }
}
