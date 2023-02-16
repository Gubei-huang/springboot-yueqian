package com.crazy.finance.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class User {
    private Integer id;

    private String username;

    private String realname;

    private String password;

    private String idcard;

    private String phone;

    private String email;

    private Integer paypwd;

    private Integer status;

    private String reputation;

}
