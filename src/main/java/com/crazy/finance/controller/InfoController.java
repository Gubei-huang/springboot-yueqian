package com.crazy.finance.controller;

import com.crazy.finance.bean.Info;
import com.crazy.finance.bean.User;
import com.crazy.finance.service.InfoService;
import com.crazy.finance.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class InfoController {
    @Autowired
    private InfoService infoService;

    /**
     * 跳转到我的消息界面
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/user/personal/toMyInfo.html")
    public String toMyInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                           HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        PageHelper.startPage(pageNum, pageSize);
        List<Info> list = infoService.selectInfoByUserId(user.getId());
        PageInfo<Info> pageInfo = new PageInfo<>(list);
        model.addAttribute("infoPageInfo", pageInfo);
        model.addAttribute("infoList", list);
        model.addAttribute("pageTopBarInfo", "我的消息界面");

        return "user/personal/myinfo";
    }

    /**
     * 更新消息（已读）
     *
     * @param infoId
     * @return
     */
    @ResponseBody
    @PutMapping("/user/updateInfo/{infoId}")
    public JsonUtils updateInfo(@PathVariable("infoId") Integer infoId) {
        System.out.println(infoId);
        infoService.updateInfoStatusById(infoId);
        return JsonUtils.success();
    }

    /**
     * 删除消息
     *
     * @param infoId
     * @return
     */
    @ResponseBody
    @DeleteMapping("/user/deleteInfo/{infoId}")
    public JsonUtils deleteInfo(@PathVariable("infoId") Integer infoId) {
        infoService.deleteInfoById(infoId);

        return JsonUtils.success();
    }
}
