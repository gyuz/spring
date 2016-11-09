package crud.core.service;

import crud.core.dao.CrudInterface;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericServiceImpl<T> implements GenericService<T> {
	private CrudInterface<T> crudInterface;
   
    public GenericServiceImpl(CrudInterface<T> crudInterface) {
        this.crudInterface = crudInterface;
    }
    
    public void setCrudInterface(CrudInterface<T> crudInterface) {
        this.crudInterface = crudInterface;
    }

    public GenericServiceImpl() {
    }

    public List<T> getList(String refObj) {
        return crudInterface.getList(refObj);
    }

    public void add(T entity) {
        crudInterface.add(entity);
    }

    public void update(T entity) {
        crudInterface.update(entity);
    }

    public void delete(T entity) {
        crudInterface.delete(entity);
    }
}
