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
public class TermFinancial {
    private Integer id;

    private String name;

    private String investerm;

    private BigDecimal leastmoney;

    private Integer profit;

    private BigDecimal annualincome;

    private BigDecimal income;
}
