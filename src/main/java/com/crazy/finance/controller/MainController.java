package com.crazy.finance.controller;

import com.crazy.finance.bean.Admin;
import com.crazy.finance.bean.News;
import com.crazy.finance.bean.User;
import com.crazy.finance.service.AdminService;
import com.crazy.finance.service.NewsService;
import com.crazy.finance.service.UserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private NewsService newsService;

    /**
     * 404错误页面
     *
     * @return
     */
    @GetMapping("/404")
    public String error404() {
        return "error/404";

    }

    /**
     * 500错误页面
     *
     * @return
     */
    @GetMapping("/500")
    public String error500() {
        return "error/500";

    }

    @GetMapping("/toindex.html")
    public String toindex(HttpSession session) {
        Object user = session.getAttribute("loginUser");
        if (user != null) {
            return "forward:user/index.html";
        }
        Object admin = session.getAttribute("loginAdmin");
        if (admin != null) {
            return "forward:admin/index.html";
        }
        return "forward:404";
    }

    /**
     * 管理员登录首页
     *
     * @param model
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/admin/index.html")
    public String toAdminIndex(Model model,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        List<User> list = userService.selectAllUserOnline(pageNum, pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        //  log.info("toAdminIndex方法执行了");
        model.addAttribute("userPageInfo", pageInfo);
        model.addAttribute("userList", list);

        model.addAttribute("pageTopBarInfo", "系统首页");
        model.addAttribute("activeUrl", "indexActive");
        return "admin/main";
    }

    /**
     * 用户首页
     *
     * @param model
     * @return
     */
    @GetMapping("/user/index.html")
    public String toUserIndex(Model model) {

        List<News> list = newsService.selectAllNews();

        model.addAttribute("newsList", list);
        model.addAttribute("pageTopBarInfo", "系统首页");
        model.addAttribute("activeUrl", "indexActive");
        return "user/main";
    }

    /**
     * 注销用户（正常退出）
     *
     * @param logout
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(@RequestParam("logout") String logout, HttpSession session) {
        if (logout.equals("userLogout")) {
            User user = (User) session.getAttribute("loginUser");
            user.setStatus(0);
            userService.updateUserStatus(user);
            session.removeAttribute("loginUser");
            return "login";
        }
        if (logout.equals("adminLogout")) {
            Admin admin = (Admin) session.getAttribute("loginAdmin");
            admin.setStatus(0);
            adminService.updateAdminStatus(admin);
            session.removeAttribute("loginAdmin");
            return "login";
        }
        return "login";
    }
}
