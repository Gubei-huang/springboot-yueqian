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
public class PayMoney {
    private Integer id;

    private BigDecimal monthmoney;

    private Integer autointo;

    private Integer type;

    private String investerm;
}
