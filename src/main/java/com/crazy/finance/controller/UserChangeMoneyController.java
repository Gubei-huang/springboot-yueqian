package com.crazy.finance.controller;

import com.crazy.finance.bean.BankCard;
import com.crazy.finance.bean.ChangeMoney;
import com.crazy.finance.bean.User;
import com.crazy.finance.service.BankCardService;
import com.crazy.finance.service.ChangeMoneyService;
import com.crazy.finance.service.UserChangeMoneyService;
import com.crazy.finance.service.UserService;
import com.crazy.finance.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 用户-零钱理财
 */
@Controller
public class UserChangeMoneyController {
    @Autowired
    private UserService userService;
    @Autowired
    private ChangeMoneyService changeMoneyService;
    @Autowired
    private UserChangeMoneyService userChangeMoneyService;
    @Autowired
    private BankCardService bankCardService;

    /**
     * 用户买入零钱理财
     *
     * @param changeMoneyId 产品id
     * @param userId        用户id
     * @param password      支付密码
     * @return
     */
    @ResponseBody
    @PostMapping("/user/userBuyChangeMoney")
    public JsonUtils byChangeMoney(@RequestParam("changeMoneyId") Integer changeMoneyId,
                                   @RequestParam("userId") Integer userId,
                                   @RequestParam("passWord") Integer password) {
        try {
            User user = userService.selectUserById(userId);
            List<BankCard> list = bankCardService.selectAllBankCardByUserId(userId);
            if (list==null||list.size()==0)
                return JsonUtils.failNu();
            if (user.getPaypwd()==null)
                return JsonUtils.failPs();
            if (user.getPaypwd().equals(password)) {
                ChangeMoney changeMoney = changeMoneyService.selectChangeMoneyById(changeMoneyId);
                try {
                    userChangeMoneyService.buyChangeMoney(userId, changeMoney);
                } catch (Exception e) {
                    e.printStackTrace();
                     return JsonUtils.failEx();
                }
                return JsonUtils.success();
            } else {
                return JsonUtils.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtils.fail();
        }
    }

    /**
     * 删除用户的零钱理财
     * @param userChangeMoneyId
     * @param session
     * @return
     */
    @ResponseBody
    @PutMapping("/user/deleteUserChangeMoney")
    public JsonUtils deleteUserChangeMoney(@RequestParam("userChangeMoneyId") Integer userChangeMoneyId,
                                           HttpSession session) {
        User user = (User) session.getAttribute("loginUser");

        try {
            userChangeMoneyService.deleteUserChangeMoneyById(userChangeMoneyId, user.getId());
            return JsonUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtils.fail();
        }

    }
}
