package crud.core.dao;

import crud.core.model.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import java.util.Date;
import java.util.List;

public class PersonDao extends CrudImpl<Person> implements PersonInterface {
    public PersonDao(){ 
    }   
    
    public List<Person> listAscending(String column){
        List<Person> personList = getSession().createCriteria(Person.class)
                              .addOrder(Order.asc(column))
                              .list();
        return personList;
    }

     public List<Person> listDescending(String column){
        List<Person> personList = getSession().createCriteria(Person.class)
                              .addOrder(Order.desc(column))
                              .list();
        return personList;
    }

    public Person getPersonById(int id) {
        Person person = (Person) getSession().get(Person.class, id); 
        return person;
    }  
}
