package crud.core.model;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class RoleDto{
    private List<String> roleIdList = new ArrayList<String>();
    private List<String> roleNameList = new ArrayList<String>();
    
    public List<String> getRoleIdList(){
        return this.roleIdList;
    }
    
    public List<String> getRoleNameList(){
        return this.roleNameList;
    }
    
    public void setRoleIdList(List roleIdList){
        this.roleIdList = roleIdList;
    }
    
    public void setRoleNameList(List roleNameList){
        this.roleNameList = roleNameList;
    }
  

}
