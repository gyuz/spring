package crud.core.dao;

import crud.core.model.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoleDao extends CrudImpl<Role> implements RoleInterface {   
    public RoleDao(){  
    }  
    
    public void update(Role role) {
        Session session2 = sessionGroup.getSession();
        Transaction tx = session2.beginTransaction();
		session2.update(role);
		tx.commit();
        session2.close();
	}
    
	public void delete(Role role) {
        Transaction tx = session.beginTransaction();
        session.delete(role);
        tx.commit();
        session.close();
	}
	
    public Role loadRole(int id) {
        session = sessionGroup.getSession();
        Transaction tx = session.beginTransaction();
        Role role = (Role) session.get(Role.class, id);
        if(role != null){
            tx.rollback();
        } else {
            tx.rollback();
            session.close();
        }    
        return role;
    }
    
    public Role getRoleById(int id) {
        Session session2 = sessionGroup.getSession();
        Transaction tx = session2.beginTransaction();
        Role role = (Role) session2.get(Role.class, id);
        tx.rollback();
        session2.close();
        return role;
    }
}
