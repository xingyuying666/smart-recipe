package world.xyy.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.xyy.dto.RespResult;
import world.xyy.entity.User;
import world.xyy.utils.Assert;

/**
 * User controller
 *
 * @author XYY
 */
@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController<User> {

    /**
     * Modify the materials
     */
    @PostMapping("/saveProfile")
    public RespResult saveProfile(User user) {
        if (Assert.isEmpty(user)) {
            return RespResult.fail("The saved object cannot be empty");
        }
        user = userService.save(user);
        session.setAttribute("loginUser", user);
        return RespResult.success("success");
    }

    /**
     * Change password
     */
    @PostMapping("/savePassword")
    public RespResult savePassword(String oldPass, String newPass) {
        if (!loginUser.getUserPwd().equals(oldPass)) {
            return RespResult.fail("The old password is incorrect");
        }
        loginUser.setUserPwd(newPass);
        loginUser = userService.save(loginUser);
        session.setAttribute("loginUser", loginUser);
        return RespResult.success("success");
    }
}
