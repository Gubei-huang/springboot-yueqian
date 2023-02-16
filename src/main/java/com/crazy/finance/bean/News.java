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
public class News {
    private Integer id;

    private String title;

    private String newsdesc;

    private Date createtime;
}
