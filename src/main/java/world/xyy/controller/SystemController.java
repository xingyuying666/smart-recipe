package world.xyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import world.xyy.entity.*;
import world.xyy.utils.Assert;

import java.util.*;

/**
 * System jump controller
 * <p>
 * ==========================================================================
 *
 * @author xyy
 */
@Controller
public class SystemController extends BaseController<User> {

    /**
     * index
     */
    @GetMapping("/index.html")
    public String index(Map<String, Object> map) {
        return "index";
    }

    /**
     * AI assistant
     */
    @GetMapping("/assistant")
    public String doctor(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        return "assistant";
    }

    /**
     * Log out
     */
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/index.html";
    }

    /**
     * My information
     */
    @GetMapping("/profile")
    public String profile(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        return "profile";
    }
}
