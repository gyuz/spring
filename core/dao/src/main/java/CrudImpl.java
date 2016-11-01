package crud.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unchecked")
@Repository
public abstract class CrudImpl<T> implements CrudInterface<T> {
	@Autowired
    private SessionFactory sessionFactory;
    
    protected Session getSession() {
        Session sess = sessionFactory.getCurrentSession();
        if (sess == null) {
            sess = sessionFactory.openSession();
        }
        return sess;
    }
    
    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }  

    public void closeSession(){
        getSession().close();    
    }
    
    public void add(T entity) {
        getSession().save(entity);
	}

	public void update(T entity) {
       getSession().update(entity);
	}

	public void delete(T entity) {
       getSession().delete(entity);
	}
     
    public List<T> getList(String refObj) {
		List<T> entity = (List<T>) getSession().createQuery("from "+refObj).list();      
        return entity; 
	}
}
