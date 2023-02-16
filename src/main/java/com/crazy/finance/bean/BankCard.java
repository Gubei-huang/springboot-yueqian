package com.crazy.finance.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class BankCard {
    private Integer id;

    private String cardbank;

    private Integer type;

    private String cardnum;

    private Integer userid;

    private BigDecimal balance;

    private Integer Default;

    private  User user;
}
