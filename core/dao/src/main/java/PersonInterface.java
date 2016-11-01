package crud.core.dao;

import crud.core.model.Person;
import java.util.List;

public interface PersonInterface extends CrudInterface<Person> {
    public List<Person> listAscending(String column);
    public List<Person> listDescending(String column);
    public Person getPersonById(int id); 
}
