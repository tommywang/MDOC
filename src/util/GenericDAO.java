package util;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, PK extends Serializable> {
    void create(T newInstance);
    T read(PK id);
    List<T> readAll();
    void update(T transientObject);
    void delete(T persistentObject);
}
