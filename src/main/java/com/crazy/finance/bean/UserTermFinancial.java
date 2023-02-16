package com.crazy.finance.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class UserTermFinancial {
    private Integer id;

    private Integer userid;

    private Integer termid;

    private TermFinancial termFinancial;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;

    private Integer status;

    public UserTermFinancial(Integer userid, Integer termid, Date starttime, Date endtime, Integer status) {
        this.userid = userid;
        this.termid = termid;
        this.starttime = starttime;
        this.endtime = endtime;
        this.status = status;
    }
}
