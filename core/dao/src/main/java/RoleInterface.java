package crud.core.dao;

import crud.core.model.Role;

public interface RoleInterface extends CrudInterface<Role> {   
    public Role loadRole(int id);
    public Role getRoleById(int id);
}
