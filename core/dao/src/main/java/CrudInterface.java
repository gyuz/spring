package crud.core.dao;

import java.util.List;

public interface CrudInterface <T> {
    public void add(T t);
    public void update(T t);
    public void delete(T t);
    public List<T> getList(String refObj);
}
