package com.crazy.finance.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Bank {
    private Integer id;

    private String name;

    private String type;

    private String assets;

    private String bankdesc;
}
