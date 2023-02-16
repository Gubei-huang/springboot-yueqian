package com.crazy.finance.controller;

import com.crazy.finance.bean.User;
import com.crazy.finance.bean.UserChangeMoney;
import com.crazy.finance.bean.UserFundProduct;
import com.crazy.finance.bean.UserTermFinancial;
import com.crazy.finance.service.UserChangeMoneyService;
import com.crazy.finance.service.UserFundProductService;
import com.crazy.finance.service.UserTermFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserFinanceController {
    @Autowired
    private UserFundProductService userFundProductService;
    @Autowired
    private UserChangeMoneyService userChangeMoneyService;
    @Autowired
    private UserTermFinancialService userTermFinancialService;

    /**
     * 查询我的理财
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/user/personal/toMyFinance.html")
    public String myFinance(Model model, HttpSession session) {

        User user = (User) session.getAttribute("loginUser");

        List<UserChangeMoney> userChangeMonies = userChangeMoneyService.selectAllUserChangeMoneyById(user.getId());

        List<UserFundProduct> userFundProducts = userFundProductService.selectAllUserFundProductById(user.getId());

        List<UserTermFinancial> userTermFinancials = userTermFinancialService.selectAllUserTermFinancialById(user.getId());


        model.addAttribute("userChangeMoneyList", userChangeMonies);
        model.addAttribute("userFundProductList", userFundProducts);
        model.addAttribute("userTermFinancialList", userTermFinancials);


        model.addAttribute("pageTopBarInfo", "我的理财界面");
        model.addAttribute("activeUrl1", "personalActive");
        model.addAttribute("activeUrl2", "myFinanceActive");

        if (session.getAttribute("myFinanceActiveUrl") == null) {
            session.setAttribute("myFinanceActiveUrl", "changeMoneyActive");
        }

        return "user/personal/myfinance";
    }
}
