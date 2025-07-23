package world.xyy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import world.xyy.component.EmailClient;
import world.xyy.dto.RespResult;
import world.xyy.entity.User;
import world.xyy.service.*;
import world.xyy.utils.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


/**
 * Basic controller
 * <p>
 * ==========================================================================
 *
 * @author xyy
 */
public class BaseController<T> {

    @Autowired
    protected ApiService apiService;
    @Autowired
    protected UserService userService;

    @Autowired
    protected BaseService<T> service;

    @Autowired
    protected EmailClient emailClient;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected User loginUser;

    /**
     * Save, modify
     *
     */
    @ResponseBody
    @PostMapping("save")
    public RespResult save(T obj) {
        if (Assert.isEmpty(obj)) {
            return RespResult.fail("The saved object cannot be empty");
        }
        obj = service.save(obj);
        return RespResult.success("success", obj);
    }

    /**
     * Delete
     *
     * @param id
     * @return Response result
     */
    @ResponseBody
    @PostMapping("/delete")
    public RespResult delete(Integer id) {
        if (Assert.isEmpty(id)) {
            return RespResult.fail("The deleted ID cannot be empty");
        }
        if (service.delete(id) == 0) {
            T t = service.get(id);
            if (Assert.isEmpty(t)) {
                return RespResult.notFound("The data does not exist.");
            }
            return RespResult.fail("error");
        }
        return RespResult.success("success");
    }

    /**
     * Call it before each subclass method is called
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
        loginUser = (User) session.getAttribute("loginUser");
        session.setAttribute("kindList", new ArrayList<>());
    }
}
