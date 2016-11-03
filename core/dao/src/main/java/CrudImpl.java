package crud.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public abstract class CrudImpl<T> implements CrudInterface<T> {
    private SessionFactory sessionFactory;
    
    protected Session getSession() {
        Session sess = sessionFactory.getCurrentSession();
        if (sess == null) {
            sess = sessionFactory.openSession();
        }
        return sess;
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }  
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    } 

    public void closeSession(){
        getSession().close();    
    }
    
    public void add(T entity) {
        getSession().saveOrUpdate(entity);
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
