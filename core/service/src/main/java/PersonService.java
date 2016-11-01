package crud.core.service;

import crud.core.model.Person;
import crud.core.model.Contact;
import crud.core.model.Role;
import crud.core.model.Title;
import crud.core.model.Types;
import java.util.List;
import org.joda.time.LocalDate;

public interface PersonService extends GenericService<Person> {   
    public boolean idExist(int id);
    public boolean titleExist(String title);
    public void loadPerson(int id);
    public void savePerson(String firstName, String lastName, String middleName, String title, LocalDate birthDate, String street, String brgy, String city, int zip, double gwa, char employed, LocalDate dateHired);
    public boolean parseEmployed(char employed);
    public boolean isDuplicate(String firstName, String lastName, String middleName);
    public List printTitleList();
    public void printPersonList(int listChoice, int order);
    public boolean addRole(Role role);
    public void deleteRole(Role role);
    public boolean roleExistInSet(int id);
    public void printPersonRoleList();
    public boolean contactExist(Contact contact);
    public boolean addContact(String type, String detail);
    public boolean updateContact(String detail);
    public void deleteContact();
    public boolean contactIdExist(int id);
    public List printTypeList();
    public void printContactList();
}
