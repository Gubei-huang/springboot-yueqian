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
public class ChangeMoney {
    private Integer id;

    private String name;

    private BigDecimal annualincome;

    private BigDecimal peiincome;

    private BigDecimal invesmoney;
}
