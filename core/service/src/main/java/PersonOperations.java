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

public class PersonOperations extends GenericServiceImpl<Person> implements PersonService {   
    private PersonInterface personDao;
    private Person person;
    private PersonDto personDto;
    private DataParser dataParser;
    private ContactService contactOps;

    public PersonDto getPersonDto(){
        return personDto;
    }
    
    public void setPersonDto(PersonDto personDto){
        this.personDto = personDto;
    }
    
    public PersonInterface getPersonInterface(){
        return personDao;
    }
    
    public void setPersonInterface(PersonInterface personDao){
        this.personDao = personDao;
    }
    
    public Person getPerson(){
        return person;
    }
    
    public void setPerson(Person person){
        this.person = person;
    }
    
    public ContactService getContactService(){
        return contactOps;
    } 
    
    public void setContactService(ContactService contactOps){
        this.contactOps = contactOps;
    }
    
    public DataParser getDataParses(){
        return dataParser;
    }
    
    public void setDataParser(DataParser dataParser){
        this.dataParser = dataParser;
    }
    
    public boolean idExist(int id) {
       person = personDao.getPersonById(id);
       if (person != null) {
            entityToDto();
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
    
    public void entityToDto() {
        personDto.setFirstName(person.getName().getFirstName());
        personDto.setMiddleName(person.getName().getMiddleName());
        personDto.setLastName(person.getName().getLastName());
        personDto.setStreet(person.getAddress().getStreet());
        personDto.setBrgy(person.getAddress().getBrgy());
        personDto.setCity(person.getAddress().getCity());
        personDto.setZip(person.getAddress().getZip());
        personDto.setBirthDate(person.getBirthDate());
        personDto.setId(person.getId());
        personDto.setGwa(person.getGwa());
        personDto.setDateHired(person.getDateHired());
        if(person.getEmployed()) {
            personDto.setEmployed('Y');
        } else {
            personDto.setEmployed('N');
        }    
    }
    
    public void savePerson(){
        person.getName().setFirstName(personDto.getFirstName());
        person.getName().setLastName(personDto.getLastName());
        person.getName().setMiddleName(personDto.getMiddleName());
        person.setTitle(Title.valueOf(personDto.getTitle()));    
        person.getAddress().setStreet(personDto.getStreet());
        person.getAddress().setBrgy(personDto.getBrgy());
        person.getAddress().setCity(personDto.getCity());
        person.getAddress().setZip(personDto.getZip());
        person.setGwa(personDto.getGwa());    
        person.setBirthDate(personDto.getBirthDate());     
        person.setEmployed(parseEmployed(personDto.getEmployed()));     
        if(personDto.getEmployed() == 'Y' && personDto.getDateHired() != null) {
            person.setDateHired(personDto.getDateHired());
        }          
    }    
    /*
    public Person loadPerson(int id){
        return personDao.getPersonById(id);
    }
    */
    public boolean parseEmployed(char employed){
        if(employed == 'Y') return true;
        return false;  
    }    
   
    public void closeSession(){
        personDao.closeSession();
    }
    
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
    
    public void printPersonList(int listChoice, int order){
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
    }

    public boolean addRole(Role role){
        if(person.getRoles().contains(role)) { 
            return false;
        } else {
            person.getRoles().add(role);
        }
        return true;
    }

    public void deleteRole(Role role){
        person.getRoles().remove(role); 
        //personDao.update(person);    
    }

    public boolean roleExistInSet(int roleId){
        Set<Role> roleSet = person.getRoles();
        for(Role r : roleSet){
          if(r.getRoleId() == roleId){
            return true;
          }
        }
        return false; 
    }

    public void printPersonRoleList(){
        Set<Role> roleSet = person.getRoles();
        for(Role r : roleSet){
          personDto.getPersonRoleIds().add(r.getRoleId());
          personDto.getPersonRoleNames().add(r.getRoleName());
        }  
    }  

    public boolean contactExist(Contact contact){
        return person.getContacts().contains(contact);
    }

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
    
    public boolean updateContact(String detail){
        Contact contact = contactOps.getContact();
        person.getContacts().remove(contact);
        contactOps.setDetail(detail);
        contact = contactOps.getContact();
        if(contactExist(contact)) {
            return false;
        } else {
            person.getContacts().add(contact);
           //personDao.update(person);   
        }
        return true;
    }

    public void deleteContact(Contact contact){
        person.getContacts().remove(contact);
        contactOps.delete(contact);
        //personDao.update(person);   
    }

    public boolean contactIdExist(int contactId){
        Set<Contact> contactSet = person.getContacts();
        for(Contact c : contactSet){
          if(c.getContactId() == contactId) {
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
    
    public void printContactList(){
        Set<Contact> contactSet = person.getContacts();
        for(Contact c : contactSet) {
          personDto.getPersonContactIds().add(c.getContactId());
          personDto.getPersonContactTypes().add(c.getContactType().name()); 
          personDto.getPersonContactDetails().add(c.getDetails());
        }  
    }
}
