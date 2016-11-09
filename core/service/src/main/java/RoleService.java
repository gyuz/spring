package crud.core.service;

import crud.core.model.Role;
import crud.core.model.RoleDto;
import java.util.List;

public interface RoleService extends GenericService<Role> { 
    public boolean idExist(int id);
    public Role getRole();
    public Role getRoleByName(String roleName);
    public void add(String roleName);
    public void update(int id, String newRoleName);
    public boolean delete(int id);
    public boolean isDuplicate(String roleName);
    public RoleDto printRoleList();
}
