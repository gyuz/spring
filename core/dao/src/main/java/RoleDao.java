package crud.core.dao;

import crud.core.model.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoleDao extends CrudImpl<Role> implements RoleInterface {   
    public RoleDao(){  
    }  
    
    public void update(Role role) {
		getSession().update(role);
	}
    
	public void delete(Role role) {
        getSession().delete(role);
	}
	
    public Role loadRole(int id) {
        Role role = (Role) getSession().load(Role.class, id);  
        return role;
    }
    
    public Role getRoleById(int id) {
        Role role = (Role) getSession().get(Role.class, id);
        return role;
    }
}
