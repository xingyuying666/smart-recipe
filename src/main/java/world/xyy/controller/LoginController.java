package world.xyy.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.xyy.dto.RespResult;
import world.xyy.entity.User;
import world.xyy.utils.Assert;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Log in to the controller
 * <p>
 * ==========================================================================
 * @author xyy
 */
@RestController
@RequestMapping(value = "login")
public class LoginController extends BaseController<User> {

    /**
     * Register
     */
    @PostMapping("/register")
    public RespResult register(User user, String code) {
        String email = user.getUserEmail();
        if (Assert.isEmpty(email)) {
            return RespResult.fail("The mailbox cannot be empty");
        }
        Map<String, Object> codeData = (Map<String, Object>) session.getAttribute("EMAIL_CODE" + email);
        if (codeData == null) {
            return RespResult.fail("The verification code has not been sent yet");
        }
        String sentCode = (String) codeData.get("code");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((Date) codeData.get("time"));
        calendar.add(Calendar.MINUTE, 5);
        if (System.currentTimeMillis() > calendar.getTime().getTime()) {
            session.removeAttribute("EMAIL_CODE" + email);
            return RespResult.fail("The verification code has timed out");
        }
        if (!sentCode.equals(code)) {
            return RespResult.fail("验证码错误");
        }
        List<User> query = userService.query(User.builder().userAccount(user.getUserAccount()).build());
        if (Assert.notEmpty(query)) {
            return RespResult.fail("The account has been registered");
        }
        user.setRoleStatus(0);
        user.setImgPath("https://moti-cloud-v2.oss-cn-beijing.aliyuncs.com/Snipaste_2022-05-01_15-37-01.png");
        user = userService.save(user);
        session.setAttribute("loginUser", user);
        return RespResult.success("success", user);
    }

    /**
     * Log in
     */
    @PostMapping("/login")
    public RespResult login(User user) {
        List<User> users = userService.query(user);
        if (Assert.notEmpty(users)) {
            session.setAttribute("loginUser", users.get(0));
            return RespResult.success("success");
        }
        if (Assert.isEmpty(userService.query(User.builder().userAccount(user.getUserAccount()).build()))) {
            return RespResult.fail("The account has not been registered yet.");
        }
        return RespResult.fail("Incorrect password");
    }

    /**
     * Send the email verification code
     */
    @PostMapping("/sendEmailCode")
    public RespResult sendEmailCode(String email, Map<String, Object> map) {
        if (StrUtil.isEmpty(email)) {
            return RespResult.fail("The mailbox must not be empty");
        }
        // 发送验证码
        String verifyCode = emailClient.sendEmailCode(email);
        map.put("email", email);
        map.put("code", verifyCode);
        map.put("time", new Date());
        session.setAttribute("EMAIL_CODE" + email, map);
        return RespResult.success("Sent successfully");
    }
}
