package world.xyy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import world.xyy.entity.User;

/**
 * User database access
 *
 * @author xyy
 */
@Repository
public interface UserDao extends BaseMapper<User> {

}
