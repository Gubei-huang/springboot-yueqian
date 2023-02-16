package com.crazy.finance.controller;

import com.crazy.finance.bean.Admin;
import com.crazy.finance.bean.User;
import com.crazy.finance.service.AdminService;
import com.crazy.finance.service.UserService;
import com.crazy.finance.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @GetMapping("/loginVerifyUsername/{username}")
    @ResponseBody
    public JsonUtils loginVerifyUsername(@PathVariable("username") String username) {
        //log.info(username);
        User user = userService.selectUserByUserName(username);
        if (user != null) {
            return JsonUtils.success();
        }
        Admin admin = adminService.selectAdminByUserName(username);

        if (admin != null) {
            return JsonUtils.success();
        }
        return JsonUtils.fail();
    }


    @GetMapping("/verifyLogin")
    @ResponseBody
    public JsonUtils verifyLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            // log.info("subject.login");
            subject.login(token);
            String name = (String) session.getAttribute("name");
            if (name.equals("user")) {
                return JsonUtils.success().add("url", "/user/index.html");
            } else {

                return JsonUtils.success().add("url", "/admin/index.html");
            }
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            return JsonUtils.fail();
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public JsonUtils register(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setStatus(0);
        user.setReputation("良好");
        userService.insertUser(user);

        return JsonUtils.success().add("url", "/");
    }
}
