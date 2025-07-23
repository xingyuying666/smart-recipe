package world.xyy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import world.xyy.entity.Pageview;

/**
 * Paginated database access
 *
 * @author xyy
 */
@Repository
public interface PageviewDao extends BaseMapper<Pageview> {

}
