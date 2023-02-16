package com.crazy.finance.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class AdminPermissions {

    private Integer id;

    private Integer adminId;

    private Integer permissionId;



    private Permissions permissions;
}
