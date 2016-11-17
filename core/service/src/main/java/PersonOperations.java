package crud.core.service;

import crud.core.model.Person;
import crud.core.model.PersonDto;
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
import org.springframework.context.annotation.Scope;

@Service
public class PersonOperations extends GenericServiceImpl<Person> implements PersonService {   
    private PersonInterface personDao;
    private Person person;
    private PersonDto personDto;
    private ContactService contactOps;

    @Autowired
    public PersonOperations(
            @Qualifier("personDao") CrudInterface<Person> genericDao) {
        super(genericDao);
        this.personDao = (PersonInterface) genericDao;
    }

    public PersonDto getPersonDto() {
        return personDto;
    }
    
    @Autowired
    @Scope("prototype")
    public void setPersonDto(PersonDto personDto) {
        this.personDto = personDto;
    }
    
    public PersonInterface getPersonInterface() {
        return personDao;
    }
    
    public void setPersonInterface(PersonInterface personDao) {
        this.personDao = personDao;
    }
    
    public Person getPerson() {
        return person;
    }
    
    public void setPerson(Person person) {
        this.person = person;
    }
    
    public ContactService getContactService() {
        return contactOps;
    } 
    
    @Autowired
    public void setContactService(ContactService contactOps) {
        this.contactOps = contactOps;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean idExist(int id) {
       person = personDao.getPersonById(id);
       if (person != null) {
            entityToDto();
            return true;
       }
       return false;     
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(int id) {
        person = personDao.getPersonById(id);
        personDao.delete(person);
    }
    
    public boolean titleExist(String title){
        for (Title t : Title.values()) {
            if (t.name().equals(title)) {
                return true;
            }
        }
        return false;
    }
    
    public void entityToDto() {
        personDto.setId(person.getId());
        personDto.setFirstName(person.getName().getFirstName());
        personDto.setMiddleName(person.getName().getMiddleName());
        personDto.setLastName(person.getName().getLastName());
        personDto.setTitle(person.getTitle().toString());
        personDto.setStreet(person.getAddress().getStreet());
        personDto.setBrgy(person.getAddress().getBrgy());
        personDto.setCity(person.getAddress().getCity());
        personDto.setZip(person.getAddress().getZip());
        personDto.setBirthDate(person.getBirthDate().toString("MM/dd/yyyy"));
        personDto.setGwa(person.getGwa());
        if(person.getDateHired() != null){
            personDto.setDateHired(person.getDateHired().toString("MM/dd/yyyy"));
        }
        if(person.getEmployed()) {
            personDto.setEmployed('Y');
        } else {
            personDto.setEmployed('N');
        }    
        printContactList();
        printPersonRoleList();
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void savePerson(){
        add(person); 
    }
    
    public void saveDetails(LocalDate birth, LocalDate hired, PersonDto personDto){
        this.personDto = personDto;
        if(personDto.getId() == 0){
            person = new Person();
        }
        person.getName().setFirstName(personDto.getFirstName().toUpperCase().trim());
        person.getName().setLastName(personDto.getLastName().toUpperCase().trim());
        person.getName().setMiddleName(personDto.getMiddleName().toUpperCase().trim());
        person.setTitle(Title.valueOf(personDto.getTitle()));    
        person.getAddress().setStreet(personDto.getStreet().toUpperCase().trim());
        person.getAddress().setBrgy(personDto.getBrgy().toUpperCase().trim());
        person.getAddress().setCity(personDto.getCity().toUpperCase().trim());
        person.getAddress().setZip(personDto.getZip());
        person.setGwa(personDto.getGwa());    
        person.setBirthDate(birth);     
        person.setEmployed(parseEmployed(personDto.getEmployed()));     
        if(personDto.getEmployed() == 'Y' && personDto.getDateHired() != null) {
            person.setDateHired(hired);
        }         
    }    
   
    public boolean parseEmployed(char employed){
        if(employed == 'Y') return true;
        return false;  
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
    public PersonDto printPersonList(int listChoice, int order){
       List<Person> personList = new ArrayList<Person>();
       personDto = new PersonDto();
       
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
       
       for (Person persons : personList){
            char employ = 'N';
            if(persons.getEmployed()) {
                employ = 'Y';
            }  
            personDto.getPersonIdList().add(persons.getId());
            personDto.getFirstNameList().add(persons.getName().getFirstName());
            personDto.getLastNameList().add(persons.getName().getLastName());
            personDto.getMiddleNameList().add(persons.getName().getMiddleName());
            personDto.getTitleList().add(persons.getTitle().toString());
            personDto.getBirthDateList().add(persons.getBirthDate());
            personDto.getStreetList().add(persons.getAddress().getStreet());
            personDto.getBrgyList().add(persons.getAddress().getBrgy());
            personDto.getCityList().add(persons.getAddress().getCity());
            personDto.getZipList().add(persons.getAddress().getZip());
            personDto.getEmployedList().add(employ);
            personDto.getGwaList().add(persons.getGwa());
            
            if(persons.getDateHired() != null){
                personDto.getDateHiredMap().put(persons.getId(), persons.getDateHired());   
            }
       }
       return personDto;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean addRole(Role role){
        if(person.getRoles().contains(role)) { 
            return false;
        } else {
            person.getRoles().add(role);
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRole(Role role){
        person.getRoles().remove(role);    
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean roleExistInSet(int roleId){
        Set<Role> roleSet = person.getRoles();
        for(Role r : roleSet){
          if(r.getRoleId() == roleId){
            return true;
          }
        }
        return false; 
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public void printPersonRoleList(){
        Set<Role> roleSet = person.getRoles();
        List roleIds = new ArrayList();
        List roleNames = new ArrayList();
        for(Role r : roleSet){
          roleIds.add(r.getRoleId());
          roleNames.add(r.getRoleName());
        }  
        personDto.setPersonRoleIds(roleIds);
        personDto.setPersonRoleNames(roleNames);
    }  

    public boolean contactExist(Contact contact){
        return person.getContacts().contains(contact);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addContact(String type, String detail){
        contactOps.setContactDetails(type, detail, person);
        Contact contact = contactOps.getContact();
        if(contactExist(contact)) {
            return false;
        } else {
             person.getContacts().add(contact);
        }
        return true;
    } 
    
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateContact(String detail){
        Contact contact = contactOps.getContact();
        person.getContacts().remove(contact);
        contactOps.setDetail(detail);
        contact = contactOps.getContact();
        if(contactExist(contact)) {
            return false;
        } else {
            person.getContacts().add(contact);
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteContact(){
        Contact contact = contactOps.getContact();
        person.getContacts().remove(contact);
        contactOps.delete(contact); 
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean contactIdExist(int contactId){
        Set<Contact> contactSet = person.getContacts();
        for(Contact c : contactSet){
          if(c.getContactId() == contactId) {
            contactOps.setContact(c);
            return true;
          }
        } 
        return false;
    }
    
    public List printTypeList(){
        List typeList = new ArrayList<String>();
        for (Types t : Types.values()) {
            typeList.add(t.name());
        }
        return typeList; 
    }  
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public void printContactList(){
        Set<Contact> contactSet = person.getContacts();
        List contactIds = new ArrayList();
        List contactTypes = new ArrayList();
        List contactDetails = new ArrayList();
        for(Contact c : contactSet) {
          contactIds.add(c.getContactId());
          contactTypes.add(c.getContactType().name()); 
          contactDetails.add(c.getDetails());
        }
        personDto.setPersonContactIds(contactIds);  
        personDto.setPersonContactTypes(contactTypes);
        personDto.setPersonContactDetails(contactDetails);
    }
}
