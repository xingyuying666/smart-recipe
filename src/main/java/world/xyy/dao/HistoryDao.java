package world.xyy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import world.xyy.entity.History;

/**
 * Historical database access
 *
 * @author xyy
 */
@Repository
public interface HistoryDao extends BaseMapper<History> {

}
