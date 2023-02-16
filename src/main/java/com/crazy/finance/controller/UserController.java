package com.crazy.finance.controller;

import com.crazy.finance.bean.User;
import com.crazy.finance.service.UserService;
import com.crazy.finance.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 跳转到用户信誉管理界面（管理员）
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/admin/userinfo/toReputation.html")
    public String toUserReputation(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                   Model model) {
        List<User> list = userService.selectAllUser(pageNum,pageSize);
        PageInfo<User> pageInfo = new PageInfo(list);
        model.addAttribute("userPageInfo",pageInfo);
        model.addAttribute("userList",list);

        model.addAttribute("activeUrl1", "userInfoActive");
        model.addAttribute("activeUrl2", "reputationActive");
        model.addAttribute("pageTopBarInfo", "用户信誉界面");
        return "admin/userinfo/reputation";
    }
    /**
     * 添加用户（管理员）
     * @param user
     * @return
     */
    @PostMapping("/user/addUser")
    @ResponseBody
    public JsonUtils addUser(User user) {
        user.setStatus(0);
        user.setReputation("良好");
        userService.insertUserByAdmin(user);

        return JsonUtils.success();
    }

    /**
     * 删除用户（管理员）
     *
     * @param id
     * @param session
     * @return
     */
    @DeleteMapping("/user/deleteUserById/{id}")
    @ResponseBody
    public JsonUtils deleteUserById(@PathVariable("id") Integer id, HttpSession session) {
        Integer result = userService.deleteUserById(id);
        if (result == 1) {
            // 删除用户时应先判断这个用户是否在线
            Object loginUser = session.getAttribute("loginUser");
            if (loginUser != null) {
                User user = (User) loginUser;
                if (id == (user.getId())) {
                    session.removeAttribute("loginUser");
                }
            }
            return JsonUtils.success();
        }
        return JsonUtils.fail();
    }

    /**
     * 更新用户信息（管理员）
     *
     * @param userId
     * @param user
     * @param session
     * @return
     */
    @ResponseBody
    @PutMapping("/admin/updateUserProfile/{id}")
    public JsonUtils updateUserProfile(@PathVariable("id") Integer userId,
                                       User user, HttpSession session) {
        user.setId(userId);
        userService.updateUserByAdmin(user);
        User user1 = userService.selectUserById(userId);
        session.setAttribute("loginUser", user1);
        return JsonUtils.success();
    }

    /**
     * 修改用户信息时回显用户信息（管理员）
     *
     * @param id
     * @return
     */
    @GetMapping("/admin/getUserById/{id}")
    @ResponseBody
    public JsonUtils getUserInfoById(@PathVariable("id") Integer id) {
        User user = userService.selectUserById(id);
        return JsonUtils.success().add("user", user);
    }

    /**
     * 修改密码
     *
     * @param userId
     * @param oldpwd
     * @param newpwd
     * @return
     */
    @ResponseBody
    @PutMapping("/user/updatePwd")
    public JsonUtils updatePwd(@RequestParam("id") Integer userId, @RequestParam("oldpwd") String oldpwd, @RequestParam("newpwd") String newpwd) {
        User user = userService.selectUserById(userId);
        if (user.getPassword().equals(oldpwd)) {
            userService.updateUserPwdByUserId(userId, newpwd);

            return JsonUtils.success();
        }
        return JsonUtils.fail();


    }

    /**
     * 跳转到账户安全界面（用户）(修改密码)
     *
     * @param model
     * @return
     */
    @RequestMapping("/user/personal/toSecurity.html")
    public String toSecurity(Model model) {
        model.addAttribute("pageTopBarInfo", "账户安全界面");
        model.addAttribute("activeUrl1", "personalActive");
        model.addAttribute("activeUrl2", "securityActive");
        return "user/personal/security";
    }

    /**
     * 展示用户个人信息页面
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/user/personal/toProfile.html")
    public String userToProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        model.addAttribute("user", user);
        model.addAttribute("pageTopBarInfo", "个人信息界面");
        return "user/personal/profile";
    }
    /**
     * 更新用户信誉
     *
     * @param user
     * @param session
     * @param userId
     * @return
     */
    @ResponseBody
    @PutMapping("/user/updateUserReputation/{userId}")
    public JsonUtils updateUserReputation(User user, HttpSession session, @PathVariable("userId") Integer userId) {
        user.setId(userId);
        userService.updateUserReputation(user);
        User user1 = userService.selectUserById(userId);
        session.setAttribute("loginUser", user1);
        return JsonUtils.success();
    }
    /**
     * 更新用户信息
     *
     * @param user
     * @param session
     * @param userId
     * @return
     */
    @ResponseBody
    @PutMapping("/user/updateUserProfile/{userId}")
    public JsonUtils updateUserProfile(User user, HttpSession session, @PathVariable("userId") Integer userId) {
        user.setId(userId);
        userService.updateUserByUserId(user);
        User user1 = userService.selectUserById(userId);
        session.setAttribute("loginUser", user1);
        return JsonUtils.success();
    }

    /**
     * 用户强制下线（管理员更改用户信息）
     *
     * @param id
     * @param session
     * @return
     */
    @PutMapping("/admin/updateUserStatus/{id}")
    @ResponseBody
    public JsonUtils updateUserStatus(@PathVariable("id") Integer id, HttpSession session) {
        User user = userService.selectUserById(id);
        user.setStatus(0);
        try {
            userService.updateUserStatus(user);
            session.removeAttribute("loginUser");
            return JsonUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtils.fail();
        }
    }

    /**
     * 跳转到用户信息界面（管理员）
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/admin/userinfo/toUserInfo.html")
    public String toUserInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                             @RequestParam(value = "username",defaultValue = "") String username,
                             Model model) {
        List<User> list = userService.selectAllUser(pageNum, pageSize);
        if(!username.equals("")){
            list = userService.findUserByUsername(username);
        }
        PageInfo<User> pageInfo = new PageInfo(list);
        model.addAttribute("userPageInfo", pageInfo);
        model.addAttribute("userList", list);
        model.addAttribute("activeUrl1", "userInfoActive");
        model.addAttribute("activeUrl2", "userInfoActive");
        model.addAttribute("pageTopBarInfo", "用户信息界面");
        model.addAttribute("input",username);
        return "admin/userinfo/userinfo";
    }

    @GetMapping("/admin/findUserByUsername")
    @ResponseBody
    public JsonUtils findUser(String username,Model model){
        List<User> list = userService.findUserByUsername(username);
        PageInfo<User> pageInfo = new PageInfo(list);
        model.addAttribute("userPageInfo", pageInfo);
        model.addAttribute("userList", list);
        return JsonUtils.success();
    }

}
