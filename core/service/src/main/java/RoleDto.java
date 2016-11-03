package crud.core.service;

import java.util.List;

public class RoleDto{
    private int roleId;
    private String roleName;
    private List<Integer> roleIdList;
    private List<String> roleNameList;
    
    public int getRoleId(){
        return roleId;    
    }    

    public String getRoleName(){
        return roleName;    
    }
    
    public List<Integer> getRoleIdList(){
        return this.roleIdList;
    }
    
    public List<String> getRoleNameList(){
        return this.roleNameList;
    }
    
    public void setRoleId(int roleId){
        this.roleId = roleId;    
    }
    
    public void setRoleName(String roleName){
        this.roleName = roleName;        
    }
    
    public void setRoleIdList(List roleIdList){
        this.roleIdList = roleIdList;
    }
    
    public void setRoleNameList(List roleNameList){
        this.roleNameList = roleNameList;
    }
  

}
