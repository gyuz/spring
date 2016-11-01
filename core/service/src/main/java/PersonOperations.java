package crud.core.service;

import crud.core.model.Person;
import crud.core.model.Contact;
import crud.core.model.Role;
import crud.core.model.Title;
import crud.core.model.Types;
import crud.core.dao.PersonDao;
import crud.core.dao.RoleDao;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import org.joda.time.LocalDate;

public class PersonOperations extends GenericServiceImpl<Person> implements PersonService {   
    private Person person;
    private PersonDao personDao;
    private ContactOperations contactOps;
    private List<Integer> personIdList;
    private List<String> firstNameList;
    private List<String> lastNameList;
    private List<String> middleNameList;
    private List<String> titleList; 
    private List<LocalDate> birthDateList;  
    private List<LocalDate> dateHiredList;  
    private List<String> streetList;
    private List<String> cityList;
    private List<String> brgyList;
    private List<Integer> zipList; 
    private List<Character> employedList;
    private List<Double> gwaList;
    private List<Integer> personRoleIds;
    private List<Integer> personContactIds;
    private List<String> personRoleNames;
    private List<String> personContactTypes;
    private List<String> personContactDetails;
    private Map<Integer, LocalDate> dateHiredMap;
   
    public PersonOperations(){
        person = new Person();
        personDao = new PersonDao();
        contactOps = new ContactOperations();
    }
    
    public boolean idExist(int id) {
       this.person = personDao.getPersonById(id);
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
    
    public void loadPerson(int id){
        this.person = personDao.getPersonById(id);
    }
    
    public void savePerson(String firstName, String lastName, String middleName, String title, LocalDate birthDate, String street, String brgy, String city, int zip, double gwa, char employed, LocalDate dateHired){
        person.getName().setFirstName(firstName.toUpperCase());
        person.getName().setLastName(lastName.toUpperCase());
        person.getName().setMiddleName(middleName.toUpperCase());
        person.setTitle(Title.valueOf(title));    
        person.getAddress().setStreet(street.toUpperCase());
        person.getAddress().setBrgy(brgy.toUpperCase());
        person.getAddress().setCity(city.toUpperCase());
        person.getAddress().setZip(zip);
        person.setGwa(gwa);    
        person.setBirthDate(birthDate);     
        person.setEmployed(parseEmployed(employed));     
        if(employed == 'Y' && dateHired != null) {
            person.setDateHired(dateHired);
        }          
    }    
    
    public void add(){
        personDao.add(person);    
    }
    
    public void update(){
        personDao.update(person);
    }
    
    public void setPerson(int id){
        this.person = personDao.getPersonById(id);
    }
   
    public boolean parseEmployed(char employed){
        if(employed == 'Y') return true;
        return false;  
    }    
    
    public int getId(){
        return person.getId();    
    }

    public String getFirstName(){
       return person.getName().getFirstName();
    }

    public String getMiddleName(){
       return person.getName().getMiddleName();
    }
    
    public String getLastName(){
       return person.getName().getLastName(); 
    }

    public String getTitle(){
       return person.getTitle().toString(); 
    }

    public LocalDate getBirthDate(){
       return person.getBirthDate();  
    }

    public LocalDate getDateHired(){
       return person.getDateHired();  
    }
    
    public String getStreet(){
       return person.getAddress().getStreet();
    }
    
    public String getCity(){
       return person.getAddress().getCity();
    }

    public String getBrgy(){
       return person.getAddress().getBrgy();
    }

    public int getZip(){
       return person.getAddress().getZip(); 
    }
    
    public char getEmployed(){
       if(person.getEmployed()){
            return 'Y';
       }
       return 'N';
    }

    public double getGwa(){
        return person.getGwa();
    }

    public List<Integer> getPersonIdList(){
        return personIdList;    
    }

    public List<String> getFirstNameList(){
       return firstNameList;
    }

    public List<String> getMiddleNameList(){
       return middleNameList;
    }
    
    public List<String> getLastNameList(){
       return lastNameList; 
    }

    public List<String> getTitleList(){
       return titleList; 
    }

    public List<LocalDate> getBirthDateList(){
       return birthDateList;  
    }

    public Map<Integer, LocalDate> getDateHiredMap(){
       return dateHiredMap;  
    }
    
    public List<String> getStreetList(){
       return streetList;
    }
    
    public List<String> getCityList(){
       return cityList;
    }

    public List<String> getBrgyList(){
       return brgyList;
    }

    public List<Integer> getZipList(){
       return zipList; 
    }
    
    public List<Character> getEmployedList(){
       return employedList;
    }

    public List<Double> getGwaList(){
        return gwaList;
    }

    public List<Integer> getPersonRoleIds(){
        return personRoleIds;
    }
    
    public List<Integer> getPersonContactIds(){
        return personContactIds;
    }
    
    public List<String> getPersonRoleNames(){
       return personRoleNames;
    }
    
    public List<String> getPersonContactTypes(){
       return personContactTypes;
    }
    
     public List<String> getPersonContactDetails(){
       return personContactDetails;
    }
    
    public void delete() {
        personDao.delete(person); 
    }
    
    public void closeSession(){
        personDao.closeSession();
    }

    public boolean isDuplicate(String firstName, String lastName, String middleName){
        PersonDao personDao2 = new PersonDao();
        List<Person> personList = personDao2.getList("Person where name.firstName = '"+firstName.toUpperCase()+"' AND name.lastName='"+lastName.toUpperCase()+"' AND name.middleName='"+middleName.toUpperCase()+"'"); 
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
       PersonDao personDao2 = new PersonDao();
       List<Person> personList = new ArrayList<Person>();
       personIdList = new ArrayList<Integer>();
       firstNameList = new ArrayList<String>();
       lastNameList = new ArrayList<String>();
       middleNameList = new ArrayList<String>();
       titleList = new ArrayList<String>();
       birthDateList = new ArrayList<LocalDate>();
       streetList = new ArrayList<String>();
       brgyList = new ArrayList<String>();
       cityList = new ArrayList<String>();
       zipList = new ArrayList<Integer>();
       employedList = new ArrayList<Character>();
       gwaList = new ArrayList<Double>();
       dateHiredMap = new HashMap<Integer, LocalDate>();
        
       if(listChoice == 1){
            personList = personDao2.getList("Person"); 
            if(order == 1) {
                Collections.sort(personList);
            } else {
                Collections.sort(personList, Collections.reverseOrder());
            }
       } else if (listChoice == 2) {
            if (order == 1) {
                personList = personDao2.listAscending("name.lastName");
            } else {
                personList = personDao2.listDescending("name.lastName");
            }
       } else if (listChoice == 3) {
            if (order == 1){
                personList = personDao2.listAscending("dateHired");
            } else {
                personList = personDao2.listDescending("dateHired");
            }
       } else {
            if (order == 1){
                personList = personDao2.listAscending("id");
            } else {
                personList = personDao2.listDescending("id");
            }
       }
       for (Person persons : personList){
            char employ = 'N';
            if(persons.getEmployed()) {
                employ = 'Y';
            }  
            personIdList.add(persons.getId());
            firstNameList.add(persons.getName().getFirstName());
            lastNameList.add(persons.getName().getLastName());
            middleNameList.add(persons.getName().getMiddleName());
            titleList.add(persons.getTitle().toString());
            birthDateList.add(persons.getBirthDate());
            streetList.add(persons.getAddress().getStreet());
            brgyList.add(persons.getAddress().getBrgy());
            cityList.add(persons.getAddress().getCity());
            zipList.add(persons.getAddress().getZip());
            employedList.add(employ);
            gwaList.add(persons.getGwa());
            
            if(persons.getDateHired() != null){
                dateHiredMap.put(persons.getId(), persons.getDateHired());   
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
        personDao.update(person);    
    }

    public boolean roleExistInSet(int id){
        Set<Role> roleSet = person.getRoles();
        for(Role r : roleSet){
          if(r.getRoleId() == id){
            return true;
          }
        }
        return false; 
    }

    public void printPersonRoleList(){
        Set<Role> roleSet = person.getRoles();
        personRoleIds = new ArrayList<Integer>();
        personRoleNames = new ArrayList<String>();
        for(Role r : roleSet){
          personRoleIds.add(r.getRoleId());
          personRoleNames.add(r.getRoleName());
        }  
    }  
    
    public boolean contactExist(Contact contact){
        return person.getContacts().contains(contact);
    }

    public String getContactType(){
        return contactOps.getContactType();    
    }

    public boolean addContact(String type, String detail){
        contactOps.setContactDetails(type, detail, person);
        Contact contact = contactOps.contact;
        if(contactExist(contact)) {
            return false;
        } else {
             person.getContacts().add(contact);
        }
        return true;
    } 
    
    public boolean updateContact(String detail){
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
    
    public void deleteContact(){
        person.getContacts().remove(contactOps.contact);
        contactOps.delete();
        personDao.update(person);   
    }

    public boolean contactIdExist(int id){
        Set<Contact> contactSet = person.getContacts();
        for(Contact c : contactSet){
          if(c.getContactId() == id) {
            contactOps.contact = c;
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
    
    public void printContactList(){
        Set<Contact> contactSet = person.getContacts();
        personContactIds = new ArrayList<Integer>();
        personContactTypes = new ArrayList<String>();
        personContactDetails = new ArrayList<String>();
        for(Contact c : contactSet){
          personContactIds.add(c.getContactId());
          personContactTypes.add(c.getContactType().name()); 
          personContactDetails.add(c.getDetails());
        }   
    }
}
