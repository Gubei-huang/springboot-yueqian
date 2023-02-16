package com.crazy.finance.controller;

import com.crazy.finance.bean.ChangeMoney;
import com.crazy.finance.service.ChangeMoneyService;
import com.crazy.finance.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChangeMoneyController {
    @Autowired(required = false)
    private ChangeMoneyService changeMoneyService;

    /**
     * 更新
     * @param id
     * @param changeMoney
     * @return
     */
    @PutMapping("/admin/updateChangeMoney/{id}")
    @ResponseBody
    public JsonUtils updateChangeMoney(@PathVariable("id") Integer id, ChangeMoney changeMoney) {
        changeMoney.setId(id);
        changeMoneyService.updateChangeMoney(changeMoney);

        return JsonUtils.success();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/admin/deleteChangeMoneyById/{id}")
    @ResponseBody
    public JsonUtils deleteChangeMoneyById(@PathVariable("id") Integer id) {
       changeMoneyService.deleteChangeMoneyById(id);

        return JsonUtils.success();

    }

    /**
     * 更新时回显信息
     *
     * @param id
     * @return
     */
    @GetMapping("/admin/getChangeMoneyInfoById/{id}")
    @ResponseBody
    public JsonUtils getChangeMoneyInfoById(@PathVariable("id") Integer id) {
        ChangeMoney changeMoney = changeMoneyService.selectChangeMoneyById(id);
        return JsonUtils.success().add("changeMoney", changeMoney);
    }

    /**
     * 新增零钱理财产品
     *
     * @param changeMoney
     * @return
     */
    @PostMapping("/admin/addChangeMoney")
    @ResponseBody
    public JsonUtils addChangeMoney(ChangeMoney changeMoney) {
        changeMoneyService.insertChangeMoney(changeMoney);

        return JsonUtils.success();

    }

    /**
     * 零钱理财产品界面(管理员)
     *
     * @param model
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/admin/finance/toChangeMoney.html")
    public String adminToChangeMoney(Model model,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ChangeMoney> list = changeMoneyService.selectAllChangeMoney();
        PageInfo<ChangeMoney> pageInfo = new PageInfo<>(list);
        model.addAttribute("changeMoneyList", list);
        model.addAttribute("cmPageInfo", pageInfo);

        model.addAttribute("pageTopBarInfo", "零钱理财界面");
        model.addAttribute("activeUrl1", "financeActive");
        model.addAttribute("activeUrl2", "changeMoneyActive");
        return "admin/finance/changemoney";
    }

    /**
     * 零钱理财产品界面(用户)
     *
     * @param model
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/user/finance/toChangeMoney.html")
    public String userToChangeMoney(Model model,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ChangeMoney> list = changeMoneyService.selectAllChangeMoney();
        PageInfo<ChangeMoney> pageInfo = new PageInfo<>(list);
        model.addAttribute("changeMoneyList", list);
        model.addAttribute("cmPageInfo", pageInfo);

        model.addAttribute("pageTopBarInfo", "零钱理财界面");
        model.addAttribute("activeUrl1", "financeActive");
        model.addAttribute("activeUrl2", "changeMoneyActive");
        return "user/finance/changemoney";
    }

}
