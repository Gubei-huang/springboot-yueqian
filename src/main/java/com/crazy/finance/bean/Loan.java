package com.crazy.finance.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Loan {
    private Integer id;

    private Integer userId;

    private Integer adminId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    private BigDecimal amount;

    private Integer term;

    private BigDecimal rate;

    private Integer applyStatus;

    private Integer loanStatus;

    private User user;

    public Loan(Integer userId, Integer adminId, Date startTime, BigDecimal amount, Integer term, BigDecimal rate, Integer applyStatus, Integer loanStatus) {
        this.userId = userId;
        this.adminId = adminId;
        this.startTime = startTime;
        this.amount = amount;
        this.term = term;
        this.rate = rate;
        this.applyStatus = applyStatus;
        this.loanStatus = loanStatus;
    }
}
