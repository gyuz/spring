package crud.core.service;

import crud.core.model.Role;
import crud.core.dao.RoleDao;
import java.util.List;
import java.util.ArrayList;

public class RoleOperations extends GenericServiceImpl<Role> implements RoleService {
    private Role role;
    private RoleDao roleDao;
    private List<Integer> roleIdList;
    private List<String> roleNameList;
    
    public RoleOperations(){   
        role = new Role();
        roleDao = new RoleDao();
    }      
    
    public Role getRole(){
        return role;    
    } 
  
    public List<Integer> getRoleIdList(){
        return this.roleIdList;
    }
    
    public List<String> getRoleNameList(){
        return this.roleNameList;
    }
    
    public void closeSession(){
        roleDao.closeSession();    
    }
   
    public void addRoleName(String roleName) {
        setRoleName(roleName.toUpperCase());
        add(role);        
    }
    
    public void add(Role r){
        roleDao.add(r);
    }

    public void setRoleName(String roleName){
        role.setRoleName(roleName.toUpperCase());    
    }
    
    public boolean idExist(int id) {
        role = roleDao.getRoleById(id); 
        if (role != null) {
            return true;
        }
        return false;     
    }
    
    public boolean update(int id, String newRoleName) {
        newRoleName = newRoleName.toUpperCase();
        boolean exist = isDuplicate(newRoleName); 
        if(!exist) {
            role = roleDao.getRoleById(id); 
            role.setRoleName(newRoleName);
            roleDao.update(role);
        }
        return !exist;    
    }
    
    public boolean delete(int id) {
       role = roleDao.loadRole(id);
       if (role != null && role.getPersons().isEmpty()) {
            roleDao.delete(role);
            return true;
        } 
        return false;  
    }

    public boolean isDuplicate(String roleName){    
        RoleDao roleDao2 = new RoleDao();
        List roleList = roleDao2.getList("Role where roleName = '"+roleName.toUpperCase()+"'"); 
        if(roleList.isEmpty()) {
            return false;
        }
        return true;
    }
    
    public void printRoleList(){
       RoleDao roleDao2 = new RoleDao();
       List<Role> roleList = roleDao2.getList("Role ORDER BY ROLE_ID");
       roleIdList = new ArrayList<Integer>();
       roleNameList = new ArrayList<String>();
       for (Role roles : roleList){
            roleIdList.add(roles.getRoleId());
            roleNameList.add(roles.getRoleName());
       }  
    }
}
