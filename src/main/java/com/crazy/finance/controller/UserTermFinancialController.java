package com.crazy.finance.controller;

import com.crazy.finance.bean.BankCard;
import com.crazy.finance.bean.TermFinancial;
import com.crazy.finance.bean.User;
import com.crazy.finance.bean.UserTermFinancial;
import com.crazy.finance.service.BankCardService;
import com.crazy.finance.service.TermFinancialService;
import com.crazy.finance.service.UserService;
import com.crazy.finance.service.UserTermFinancialService;
import com.crazy.finance.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户-期限理财
 */
@Controller
public class UserTermFinancialController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserTermFinancialService userTermFinancialService;
    @Autowired
    private TermFinancialService termFinancialService;
    @Autowired
    private BankCardService bankCardService;

    @ResponseBody
    @PostMapping("/user/buyTermFinancial")
    public JsonUtils buyTermFinancial(@RequestParam("termFinancialId") Integer termFinancialId,
                                      @RequestParam("userId") Integer userId,
                                      @RequestParam("passWord") Integer password){
        try {
            User user = userService.selectUserById(userId);
            List<BankCard> list = bankCardService.selectAllBankCardByUserId(userId);
            if (list==null||list.size()==0)
                return JsonUtils.failNu();
            if (user.getPaypwd()==null)
                return JsonUtils.failPs();
            if (user.getPaypwd().equals(password)){
                TermFinancial termFinancial = termFinancialService.selectTermFinancialById(termFinancialId);
                try {
                    userTermFinancialService.buyTermFinancial(userId,termFinancial);
                } catch (Exception e) {
                    e.printStackTrace();
                    return JsonUtils.failEx();
                }
                return JsonUtils.success();
            }else {
                 return JsonUtils.fail();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtils.fail();
        }

    }
}
