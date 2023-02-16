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
public class FundProduct {
    private Integer id;

    private Integer type;

    private Integer code;

    private String funddesc;

    private BigDecimal dailygrowth;

    private BigDecimal leastmoney;

    private BigDecimal profit;

    private String investerm;
}
