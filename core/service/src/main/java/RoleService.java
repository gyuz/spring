package crud.core.service;

import crud.core.model.Role;
import java.util.List;

public interface RoleService extends GenericService<Role> { 
    public void addRoleName(String roleName);
    public void setRoleName(String roleName);
    public boolean idExist(int id);
    public boolean update(int id, String newRoleName);
    public boolean delete(int id);
    public boolean isDuplicate(String roleName);
    public void printRoleList();
}
