package crud.core.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public abstract class CrudImpl<T> implements CrudInterface<T> {
	SessionGroup sessionGroup = new SessionGroup();
    Session session;
   
    public void startSession(){
        session = sessionGroup.getSession();    
    }    

    public void closeSession(){
        session.close();    
    }
    
    public void add(T entity) {
        session = sessionGroup.getSession();
        Transaction tx = session.beginTransaction();
		session.save(entity);
        tx.commit();
        session.close();
	}

	public void update(T entity) {
        Transaction tx = session.beginTransaction();
		session.update(entity);
		tx.commit();  
	}

	public void delete(T entity) {
        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit(); 
	}
     
    public List<T> getList(String refObj) {
        session = sessionGroup.getSession();
        Transaction tx = session.beginTransaction();
		List<T> entity = (List<T>) session.createQuery("from "+refObj).list();      
        tx.rollback();
        session.close();
        return entity; 
	}
}
