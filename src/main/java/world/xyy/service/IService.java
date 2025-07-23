package world.xyy.service;

import java.io.Serializable;
import java.util.List;

/**
 * Basic service interface
 *
 * @author XYY
 */
public interface IService<T> {

    /**
     * Save
     */
    T save(T t);

    /**
     * Obtained by the primary key
     */
    T get(Serializable id);

    /**
     * Delete according to the primary key
     */
    int delete(Serializable id);

    /**
     * Query
     */
    List<T> query(T o);

    /**
     * Query all
     */
    List<T> all();
}
