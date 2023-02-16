package com.crazy.finance.controller;

import com.crazy.finance.bean.TermFinancial;
import com.crazy.finance.service.TermFinancialService;
import com.crazy.finance.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TermFinancialController {

    @Autowired
    private TermFinancialService termFinancialService;

    /**
     * 新增期限理财产品
     *
     * @return
     */
    @PostMapping("/admin/addTermFinancial")
    @ResponseBody
    public JsonUtils addTermFinancial(TermFinancial termFinancial){
        termFinancialService.insertTermFinancial(termFinancial);
            return JsonUtils.success();
    }

    /**
     * 更新时回显信息
     * @param id
     * @return
     */
    @GetMapping("/admin/getTermFinancialInfoById/{id}")
    @ResponseBody
    public JsonUtils getTermFinancialInfoById(@PathVariable("id") Integer id){
        TermFinancial termFinancial = termFinancialService.selectTermFinancialById(id);
        return JsonUtils.success().add("termFinancial",termFinancial);
    }

    /**
     * 更新
     * @param id
     *
     * @return
     */
    @PutMapping("/admin/updateTermFinancial/{id}")
    @ResponseBody
    public JsonUtils updateTermFinancial(@PathVariable("id") Integer id,TermFinancial termFinancial){
        termFinancial.setId(id);
        termFinancialService.updateTermFinancial(termFinancial);

            return JsonUtils.success();

    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/admin/deleteTermFinancialById/{id}")
    @ResponseBody
    public JsonUtils deleteTermFinancialById(@PathVariable("id") Integer id){
       termFinancialService.deleteTermFinancialById(id);

            return JsonUtils.success();

    }
    /**
     * 基金理财产品界面（管理员）
     * @param model
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/admin/finance/toTermFinancial.html")
    public String adminToTermFinancial(Model model,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){

        PageHelper.startPage(pageNum, pageSize);
        List<TermFinancial> list = termFinancialService.selectAllTermFinancial();
        PageInfo<TermFinancial> pageInfo = new PageInfo<>(list);
        model.addAttribute("tfPageInfo", pageInfo);
        model.addAttribute("termFinancialList", list);

        model.addAttribute("pageTopBarInfo", "期限理财界面");
        model.addAttribute("activeUrl1", "financeActive");
        model.addAttribute("activeUrl2", "termFinancialActive");
        return "admin/finance/termfinancial";
    }
    /**
     * 基金理财产品界面（用户）
     * @param model
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/user/finance/toTermFinancial.html")
    public String userToTermFinancial(Model model,
                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){

        PageHelper.startPage(pageNum, pageSize);
        List<TermFinancial> list = termFinancialService.selectAllTermFinancial();
        PageInfo<TermFinancial> pageInfo = new PageInfo<>(list);
        model.addAttribute("tfPageInfo", pageInfo);
        model.addAttribute("termFinancialList", list);

        model.addAttribute("pageTopBarInfo", "期限理财界面");
        model.addAttribute("activeUrl1", "financeActive");
        model.addAttribute("activeUrl2", "termFinancialActive");
        return "user/finance/termfinancial";
    }
}
