package com.crazy.finance.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class FlowOfFunds {
    private Integer id;

    private Integer userId;

    private BigDecimal flowMoney;

    private Integer type;

    private String source;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String fundDesc;

    public FlowOfFunds(Integer userId,  BigDecimal flowMoney, Integer type, String source, Date createTime, String fundDesc) {
        this.userId = userId;

        this.flowMoney = flowMoney;
        this.type = type;
        this.source = source;
        this.createTime = createTime;
        this.fundDesc = fundDesc;
    }
}
