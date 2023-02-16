package com.crazy.finance.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
public class Info {
    private Integer id;

    private Integer sendid;

    private Admin admin;

    private Integer receiveid;

    private User user;

    private Date createtime;

    private String title;

    private String infodesc;

    private Integer status;
}
