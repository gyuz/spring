package crud.core.service;

import crud.core.dao.CrudInterface;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
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

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> getList(String refObj) {
        return crudInterface.getList(refObj);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void add(T entity) {
        crudInterface.add(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(T entity) {
        crudInterface.update(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(T entity) {
        crudInterface.delete(entity);
    }
}
