package world.xyy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * User entity
 *
 * @author xyy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("user")
public class User {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * userAccount
     */
    private String userAccount;

    /**
     * userName
     */
    private String userName;

    /**
     * userPwd
     */
    private String userPwd;

    /**
     * userAge
     */
    private Integer userAge;

    /**
     * userSex
     */
    private String userSex;

    /**
     * userEmail
     */
    private String userEmail;

    /**
     * userTel
     */
    private String userTel;

    /**
     * Role status: 1 represents administrator, and 0 represents ordinary user
     */
    private Integer roleStatus;

    /**
     * imgPath
     */
    private String imgPath;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

}
