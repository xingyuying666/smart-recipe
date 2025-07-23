package world.xyy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Browse the entity
 *
 * @author xyy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("pageview")
public class Pageview implements Serializable {

    /**
     * id
     */
    private int id;

    /**
     * pageviews
     */
    private Integer pageviews;

}
