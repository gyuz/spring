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
        Session session2 = sessionGroup.getSession();
        Transaction tx = session2.beginTransaction();
        List<Person> personList = session2.createCriteria(Person.class)
                              .addOrder(Order.asc(column))
                              .list();
        tx.rollback();
        session2.close();
        return personList;
    }

     public List<Person> listDescending(String column){
        Session session2 = sessionGroup.getSession();
        Transaction tx = session2.beginTransaction();
        List<Person> personList = session2.createCriteria(Person.class)
                              .addOrder(Order.desc(column))
                              .list();
        tx.rollback();
        session2.close();
        return personList;
    }

    public Person getPersonById(int id) {
        session = sessionGroup.getSession();
        Transaction tx = session.beginTransaction();
        Person person = (Person) session.get(Person.class, id);
        if(person != null){
            tx.rollback();
        } else {
            tx.rollback();
            session.close();
        }    
        return person;
    }  
}
