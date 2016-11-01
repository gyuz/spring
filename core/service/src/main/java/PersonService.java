package crud.core.service;

import crud.core.model.Person;
import crud.core.model.Contact;
import crud.core.model.Role;
import crud.core.model.Title;
import crud.core.model.Types;
import java.util.List;
import java.util.Set;
import org.joda.time.LocalDate;

public interface PersonService extends GenericService<Person> {   
    public boolean idExist(int id);
    public boolean titleExist(String title);
    public Person loadPerson(int id);
    public boolean parseEmployed(char employed);
    public boolean isDuplicate(String firstName, String lastName, String middleName);
    public List printTitleList();
    public List printPersonList(int listChoice, int order);
    public boolean addRole(Role role, int id);
    public void deleteRole(Role role, int id);
    public boolean roleExistInSet(int roleId, int id);
    public Set printPersonRoleList(int id);
    public boolean contactExist(Contact contact, int id);
    //public boolean addContact(String type, String detail, int id);
    //public boolean updateContact(String detail, int id);
    public void deleteContact(Contact contact, int id);
    public boolean contactIdExist(int contactId, int id);
    public List printTypeList();
    public Set printContactList(int id);
}
