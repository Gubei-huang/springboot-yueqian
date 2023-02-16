package com.crazy.finance.controller;

import com.crazy.finance.bean.*;
import com.crazy.finance.dao.FlowOfFundsDao;
import com.crazy.finance.service.BankCardService;
import com.crazy.finance.service.InfoService;
import com.crazy.finance.service.LoanService;
import com.crazy.finance.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
public class LoanController {
    @Autowired
    private LoanService loanService;
    @Autowired
    private InfoService infoService;

    @Autowired
    private BankCardService bankCardService;

    @Autowired(required = false)
    private FlowOfFundsDao flowOfFundsDao;

    /**
     * 审核通过
     *
     * @param id
     * @return
     */
    @PutMapping("/loan/passApplyStatus/{id}")
    @ResponseBody
    public JsonUtils passApplyStatus(@PathVariable("id") Integer id, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("loginAdmin");
        Loan loan = loanService.selectLoanById(id);
        loan.setAdminId(admin.getId());
        loan.setApplyStatus(2);
        loanService.updateLoan(loan);

        BankCard bankCard = bankCardService.selectDefaultBankCardByUserId(loan.getUserId());
        BigDecimal balance = bankCard.getBalance();
        BigDecimal amount = loan.getAmount();
        bankCard.setBalance(balance.add(amount));
        bankCardService.updateBankCardBalance(bankCard.getId(),bankCard.getBalance());

        FlowOfFunds flowOfFunds = new FlowOfFunds(loan.getUserId(), amount, 2, "网贷", new Date(), "");
        flowOfFundsDao.insertFlowOfFundsDao(flowOfFunds);
        Info info = new Info();
        info.setSendid(admin.getId());
        info.setReceiveid(loan.getUserId());
        info.setCreatetime(new Date());
        info.setTitle("网贷审核通过");
        info.setInfodesc("用户" + loan.getUser().getUsername() + "的" + loan.getAmount() + "元网贷申请审核通过！审核人为：" + admin.getUsername());
        info.setStatus(0);
        infoService.insertInfo(info);
        return JsonUtils.success();

    }

    /**
     * 审核不通过
     *
     * @param id
     * @return
     */
    @PutMapping("/loan/notPassApplyStatus/{id}")
    @ResponseBody
    public JsonUtils notPassApplyStatus(@PathVariable("id") Integer id, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("loginAdmin");
        Loan loan = loanService.selectLoanById(id);
        loan.setAdminId(admin.getId());
        loan.setApplyStatus(1);
        loanService.updateLoan(loan);

        Info info = new Info();
        info.setSendid(admin.getId());
        info.setReceiveid(loan.getUser().getId());
        info.setCreatetime(new Date());
        info.setTitle("网贷审核未通过");
        info.setInfodesc("用户" + loan.getUser().getUsername() + "的" + loan.getAmount() + "元网贷申请审核未通过！审核人为：" + admin.getUsername());
        info.setStatus(0);
        infoService.insertInfo(info);
        return JsonUtils.success();

    }

    /**
     * 提醒还款
     *
     * @param id
     * @param session
     * @return
     */
    @PutMapping("/loan/remindPay/{id}")
    @ResponseBody
    public JsonUtils remindPay(@PathVariable("id") Integer id, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("loginAdmin");
        Loan loan = loanService.selectLoanById(id);
        Info info = new Info();
        info.setSendid(admin.getId());
        info.setReceiveid(loan.getUser().getId());
        info.setCreatetime(new Date());
        info.setTitle("还款通知");
        info.setInfodesc("用户" + loan.getUser().getUsername() + "申请的" + loan.getAmount() + "元网贷该还款了！该提醒发送人为：" + admin.getUsername());
        info.setStatus(0);
        infoService.insertInfo(info);

        return JsonUtils.success();

    }

    /**
     * 跳转到网贷审核（管理员）
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/admin/loan/toLoanexam.html")
    public String toLoanexam(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             Model model) {

        PageHelper.startPage(pageNum, pageSize);
        List<Loan> list = loanService.selectAllLoanByApplyStatusAsc();

        PageInfo<Loan> pageInfo = new PageInfo(list);
        model.addAttribute("loanPageInfo", pageInfo);
        model.addAttribute("loanList", list);

        model.addAttribute("activeUrl1", "loanActive");
        model.addAttribute("activeUrl2", "loanexamActive");
        model.addAttribute("pageTopBarInfo", "网贷审核界面");
        return "admin/loan/loanexam";
    }

    /**
     * 跳转到网贷信息界面（管理员）
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/admin/loan/toLoaninfo.html")
    public String toLoaninfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             Model model) {
        PageHelper.startPage(pageNum, pageSize);
        List<Loan> list = loanService.selectAllExamedLoan();
        PageInfo<Loan> pageInfo = new PageInfo(list);
        model.addAttribute("loanPageInfo", pageInfo);
        model.addAttribute("loanList", list);

        model.addAttribute("activeUrl1", "loanActive");
        model.addAttribute("activeUrl2", "loaninfoActive");
        model.addAttribute("pageTopBarInfo", "网贷信息界面");
        return "admin/loan/loaninfo";
    }


    /**
     * 借贷还款
     *
     * @param id
     * @param session
     * @return
     */
    @ResponseBody
    @PutMapping("/user/repayment/{id}")
    public JsonUtils repayment(@PathVariable("id") Integer id,
                               HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        try {
            loanService.updateLoanById(id, loginUser.getId());

            return JsonUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtils.fail();
        }

    }

    /**
     * 我的借贷界面
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/user/personal/toMyLoan.html")
    public String toMyLoan(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                           Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");

        PageHelper.startPage(pageNum, pageSize);
        List<Loan> list = loanService.selectLoanByUserId(loginUser.getId());
        PageInfo<Loan> pageInfo = new PageInfo(list);
        model.addAttribute("myLoansPageInfo", pageInfo);
        model.addAttribute("loansList", list);

        model.addAttribute("pageTopBarInfo", "我的借贷界面");
        model.addAttribute("activeUrl1", "personalActive");
        model.addAttribute("activeUrl2", "myLoanActive");
        return "user/personal/myloan";
    }

    /**
     * 添加借贷
     *
     * @param amount
     * @param rate
     * @param term
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/user/applyLoan")
    public JsonUtils addLoan(@RequestParam("amount") BigDecimal amount,
                             @RequestParam("rate") BigDecimal rate,
                             @RequestParam("term") Integer term,
                             HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        Integer userId = user.getId();
        if (user.getRealname()==null)
            return JsonUtils.failEx();
        List<BankCard> list = bankCardService.selectAllBankCardByUserId(userId);
        if (list==null||list.size()==0)
            return JsonUtils.failNu();
        Loan loan = new Loan(userId, 1, new Date(), amount, term, rate, 0, 0);
        try {
            loanService.insertLoan(loan);
            return JsonUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtils.fail();
        }
    }

    /**
     * 申请的借贷界面
     *
     * @param model
     * @return
     */
    @RequestMapping("/user/tools/toApplyLoan.html")
    public String toLoan(Model model) {

        model.addAttribute("pageTopBarInfo", "网贷申请界面");
        model.addAttribute("activeUrl1", "toolsActive");
        model.addAttribute("activeUrl2", "applyLoanActive");
        return "user/tools/applyloan";
    }
}
