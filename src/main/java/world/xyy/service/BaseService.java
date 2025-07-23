package world.xyy.service;

import org.springframework.beans.factory.annotation.Autowired;
import world.xyy.dao.*;

/**
 * Basic service category
 *
 * @author XYY
 */
public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected HistoryDao historyDao;

    @Autowired
    protected PageviewDao pageviewDao;

}
