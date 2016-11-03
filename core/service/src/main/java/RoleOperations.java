package crud.core.service;

import crud.core.model.Role;
import crud.core.dao.RoleInterface;
import crud.core.dao.CrudInterface;
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
    
    public RoleInterface getRoleInterface(){
        return roleDao;
    }
    
    public void setRoleInterface(RoleInterface roleDao){
        this.roleDao = roleDao;
    }
    
    public Role getRole(){
        return role;
    }
    
    public void setRole(Role role){
        this.role = role;
    }
    
    public void closeSession(){
        roleDao.closeSession();    
    }
    
    public void addRole(String roleName){
        role.setRoleName(roleName);
        add(role);
    }
    
    public boolean idExist(int id) {
        role = roleDao.getRoleById(id); 
        if (role != null) {
            return true;
        }
        return false;     
    }
    
    public void DtoToEntity(RoleDto roleDto){
        role.setRoleId(roleDto.getRoleId());
        role.setRoleName(roleDto.getRoleName());
    }
    
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
    
    public boolean deleteRole(){
       //role = roleDao.loadRole(id);
       if (role != null && role.getPersons().isEmpty()) {
            delete(role);
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
    
    public void printRoleList(){
       List<Role> roleList = roleDao.getList("Role ORDER BY ROLE_ID");
       for (Role roles : roleList){
            roleDto.getRoleIdList().add(roles.getRoleId());
            roleDto.getRoleNameList().add(roles.getRoleName());
       }  
    }
}
