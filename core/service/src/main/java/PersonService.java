package crud.core.service;

import crud.core.model.Person;
import crud.core.model.Contact;
import crud.core.model.Role;
import crud.core.model.Title;
import crud.core.model.Types;
import crud.core.model.PersonDto;
import java.util.List;

public interface PersonService extends GenericService<Person> {   
    public PersonDto getPersonDto();
    public boolean idExist(int id);
    public Person findBySso(int id);
    public boolean titleExist(String title);
    public void savePerson();
    public void saveDetails(PersonDto personDto);
    public void delete(int id);
    public void entityToDto();
    public boolean parseEmployed(char employed);
    public boolean isDuplicate(String firstName, String lastName, String middleName);
    public List printTitleList();
    public PersonDto printPersonList(int listChoice, int order);
    public boolean addRole(Role role);
    public void deleteRole(Role role);
    public boolean roleExistInSet(int roleId);
    public void printPersonRoleList();
    public boolean contactExist(Contact contact);
    public boolean addContact(String type, String detail);
    public boolean updateContact(String detail);
    public void deleteContact();
    public boolean contactIdExist(int contactId);
    public List printTypeList();
    public void printContactList();
}
