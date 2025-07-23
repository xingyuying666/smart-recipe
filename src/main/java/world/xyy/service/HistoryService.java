package world.xyy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.xyy.dao.HistoryDao;
import world.xyy.entity.History;
import world.xyy.utils.Assert;
import world.xyy.utils.BeanUtil;
import world.xyy.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.*;

/**
 * Historical service category
 *
 * @author XYY
 */
@Service
public class HistoryService extends BaseService<History> {

    @Autowired
    protected HistoryDao historyDao;

    @Override
    public List<History> query(History o) {
        QueryWrapper<History> wrapper = new QueryWrapper();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return historyDao.selectList(wrapper);
    }

    @Override
    public List<History> all() {
        return query(null);
    }

    @Override
    public History save(History o) {
        if (Assert.isEmpty(o.getId())) {
            historyDao.insert(o);
        } else {
            historyDao.updateById(o);
        }
        return historyDao.selectById(o.getId());
    }

    @Override
    public History get(Serializable id) {
        return historyDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return historyDao.deleteById(id);
    }

    public boolean insetOne(Integer uid, Integer type, String nameValue) {
        History history = new History();
        history.setUserId(uid).setKeyword(nameValue).setOperateType(type);
        return historyDao.insert(history) > 0;
    }

}