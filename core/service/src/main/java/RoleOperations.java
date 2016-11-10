package crud.core.service;

import crud.core.model.Role;
import crud.core.model.RoleDto;
import crud.core.dao.RoleInterface;
import java.util.List;
import java.util.ArrayList;

public class RoleOperations extends GenericServiceImpl<Role> implements RoleService {
    private RoleInterface roleDao;
    private Role role;
    private RoleDto roleDto;
    
    public RoleDto getRoleDto(){
        return roleDto;
    }
    
    public void setRoleDto(RoleDto roleDto){
        this.roleDto = roleDto;
    }
    
    public RoleInterface getRoleInterface() {
        return roleDao;
    }
    
    public void setRoleInterface(RoleInterface roleDao) {
        this.roleDao = roleDao;
    }
    
    public Role getRole(){
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    public Role getRoleByName(String roleName) {
        List<Role> roles = roleDao.getList("Role where roleName = '"+roleName.toUpperCase()+"'");
        role = roles.get(0);
        return role;     
    }
    
    public void add(String roleName) {
        role = new Role();
        role.setRoleName(roleName);
        roleDao.add(role);
    }
    
    public boolean idExist(int id) {
        role = roleDao.getRoleById(id); 
        if (role != null) {
            return true;
        }
        return false;     
    }
    
    public void update(int id, String newRoleName) {
         role = roleDao.getRoleById(id); 
         role.setRoleName(newRoleName);
         roleDao.update(role);   
    }
    
    public boolean delete(int id){
       role = roleDao.loadRole(id);
       if (role != null && role.getPersons().isEmpty()) {
            roleDao.delete(role);
            return true;
        } 
        return false;  
    }

    public boolean isDuplicate(String roleName){    
        List roleList = roleDao.getList("Role where roleName = '"+roleName.toUpperCase()+"'"); 
        if(roleList.isEmpty()) {
            return false;
        }
        return true;
    }
    
    public RoleDto printRoleList(){
       roleDto = new RoleDto();
       List<Role> roleList = roleDao.getList("Role ORDER BY ROLE_ID");
       for (Role roles : roleList){
            roleDto.getRoleIdList().add(roles.getRoleId()+"");
            roleDto.getRoleNameList().add(roles.getRoleName());
       } 
       return roleDto; 
    }
}
