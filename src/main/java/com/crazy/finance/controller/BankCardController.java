package com.crazy.finance.controller;

import com.crazy.finance.bean.BankCard;
import com.crazy.finance.bean.User;
import com.crazy.finance.service.BankCardService;
import com.crazy.finance.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class BankCardController {
    @Autowired
    private BankCardService bankCardService;

    /**
     * 跳转到银行卡管理界面（管理员）
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/admin/userinfo/toBankCard.html")
    public String toBankCard1(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                              Model model) {
        PageHelper.startPage(pageNum, pageSize);
        List<BankCard> list = bankCardService.selectAllBankCard();

        PageInfo<BankCard> pageInfo = new PageInfo(list);
        model.addAttribute("bankcardPageInfo",pageInfo);
        model.addAttribute("bankcardList",list);

        model.addAttribute("pageTopBarInfo", "银行卡管理界面");
        model.addAttribute("activeUrl1", "userInfoActive");
        model.addAttribute("activeUrl2", "bankcardActive");
        return "admin/userinfo/bankcard";
    }

    /**
     * 新增银行卡 （用户）
     * @param bankCard
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/user/addBankCard")
    public JsonUtils addBnakCard(BankCard bankCard, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        Integer userId = user.getId();
        bankCard.setUserid(userId);
        List<BankCard> list = bankCardService.selectAllBankCardByUserId(userId);
        if (list==null|| list.size()==0)
            bankCard.setDefault(1);
        else
        bankCard.setDefault(0);
        bankCard.setBalance(new BigDecimal(10000));//默认一万
        try {
            bankCardService.insertBankCard(bankCard);
            return JsonUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return  JsonUtils.fail();
        }
    }

    /**
     * 银行卡界面（用户）
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/user/personal/toBankCard.html")
    public String toBankCard(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        List<BankCard> list = bankCardService.selectAllBankCardByUserId(user.getId());
        model.addAttribute("bankCardList", list);
        model.addAttribute("pageTopBarInfo", "银行卡管理界面");
        model.addAttribute("activeUrl1", "personalActive");
        model.addAttribute("activeUrl2", "bankCardActive");
        return "user/personal/bankcard";
    }

    /**
     * 修改用户默认支付的银行卡（用户）
     * @param id
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/user/personal/toBankCardDefault.html/{id}")
    public JsonUtils toBankCardDefault(@PathVariable("id") Integer id,
                                       HttpSession session) {

        User user = (User) session.getAttribute("loginUser");
        Integer userId = user.getId();
        bankCardService.updateBankCardDefault(id, userId);

        return JsonUtils.success().add("url", "/user/personal/toBankCard.html");
    }

    /**
     * 修改银行卡信息时回显银行卡信息(用户，管理员)
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/user/getBankCardById/{id}")
    public JsonUtils getBankCardById(@PathVariable("id")Integer id){

       BankCard bankCard =bankCardService.selectBankCardById(id);
        return JsonUtils.success().add("bankcard",bankCard);
    }

    /**
     *  修改银行卡信息（用户，管理员）
     * @param id
     * @param bankcard
     * @return
     */
    @ResponseBody
    @PutMapping("/user/updateBankCard/{update-id}")
    public JsonUtils updateBankCard(@PathVariable("update-id") Integer id, BankCard bankcard){
        bankcard.setId(id);
        bankCardService.updateBankCard(bankcard);
        return JsonUtils.success();
    }

    /**
     * 删除银行卡（用户，管理员）
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/user/deleteBankCard/{id}")
    public JsonUtils  deleteBankCard(@PathVariable("id")Integer id){

         bankCardService.deleteBankCardById(id);
        return  JsonUtils.success();
    }
}
