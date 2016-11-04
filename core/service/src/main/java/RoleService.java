package crud.core.service;

import crud.core.model.Role;
import java.util.List;

public interface RoleService extends GenericService<Role> { 
    public boolean idExist(int id);
    public void addRole(String roleName);
    public boolean update(int id, String newRoleName);
    public boolean deleteRole();
    public boolean isDuplicate(String roleName);
    public void DtoToEntity(RoleDto roleDto);
    public void printRoleList();
}
