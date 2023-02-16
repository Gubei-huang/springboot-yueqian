package com.crazy.finance.controller;

import com.crazy.finance.bean.FlowOfFunds;
import com.crazy.finance.bean.User;
import com.crazy.finance.service.FlowOfFundsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FlowOfFundsController {
    @Autowired
    private FlowOfFundsService flowOfFundsService;
    @RequestMapping("/user/tools/toRecord.html")
    public String toFlowOfFunds(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                Model model, HttpSession session){

        User user = (User) session.getAttribute("loginUser");
        PageHelper.startPage(pageNum,pageSize);
        List<FlowOfFunds> list = flowOfFundsService.selectAllFlowOfFundsByUserId(user.getId());
        PageInfo<FlowOfFunds> pageInfo = new PageInfo<>(list);

        model.addAttribute("flowOfFundsList",list);
        model.addAttribute("flowOfFundsPageInfo",pageInfo);
        model.addAttribute("pageTopBarInfo", "资金记录界面");
        model.addAttribute("activeUrl1", "toolsActive");
        model.addAttribute("activeUrl2", "recordActive");
        return "user/tools/record";
    }
}
