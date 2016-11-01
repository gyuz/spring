package crud.core.service;

import crud.core.model.Role;
import crud.core.dao.RoleInterface;
import crud.core.dao.CrudInterface;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleOperations extends GenericServiceImpl<Role> implements RoleService {
    private RoleInterface roleDao;
    
    @Autowired
    public RoleOperations(
            @Qualifier("roleDao") CrudInterface<Role> crudInterface) {
        super(crudInterface);
        this.roleDao = (RoleInterface) crudInterface;
    }
    
    public void closeSession(){
        roleDao.closeSession();    
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean idExist(int id) {
        Role role = roleDao.getRoleById(id); 
        if (role != null) {
            return true;
        }
        return false;     
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(int id, String newRoleName) {
        newRoleName = newRoleName.toUpperCase();
        boolean exist = isDuplicate(newRoleName); 
        if(!exist) {
            Role role = roleDao.getRoleById(id); 
            role.setRoleName(newRoleName);
            roleDao.update(role);
        }
        return !exist;    
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(int id) {
       Role role = roleDao.loadRole(id);
       if (role != null && role.getPersons().isEmpty()) {
            roleDao.delete(role);
            return true;
        } 
        return false;  
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean isDuplicate(String roleName){    
        List roleList = roleDao.getList("Role where roleName = '"+roleName.toUpperCase()+"'"); 
        if(roleList.isEmpty()) {
            return false;
        }
        return true;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List printRoleList(){
       List<Role> roleList = roleDao.getList("Role ORDER BY ROLE_ID");
       return roleList; 
    }
}
