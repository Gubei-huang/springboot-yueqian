package com.crazy.finance.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class UserChangeMoney implements Serializable {
    private Integer id;

    private Integer userid;

    private Integer changeid;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date alsttime;

    private ChangeMoney changeMoney;

    private BigDecimal profit;

    private Integer status;

    public UserChangeMoney(Integer userid, Integer changeid, Date starttime, Date alsttime,Integer status) {
        this.userid = userid;
        this.changeid = changeid;
        this.starttime = starttime;
        this.alsttime = alsttime;
        this.status = status;
    }

    public UserChangeMoney(Integer userid, Integer changeid, Date starttime, BigDecimal profit, Integer status) {
        this.userid = userid;
        this.changeid = changeid;
        this.starttime = starttime;
        this.profit = profit;
        this.status = status;
    }

    public UserChangeMoney(Integer userid, Integer changeid, Date starttime, Date alsttime, BigDecimal profit, Integer status) {
        this.userid = userid;
        this.changeid = changeid;
        this.starttime = starttime;
        this.alsttime = alsttime;
        this.profit = profit;
        this.status = status;
    }
}
