package crud.core.service;

import java.util.List;

public interface GenericService <T> {
    public void add(T t);
    public void update(T t);
    public void delete(T t);
    public List<T> getList(String refObj);
}
