package com.crazy.finance.config.shiro;

import com.crazy.finance.bean.Admin;
import com.crazy.finance.bean.Permissions;
import com.crazy.finance.bean.User;
import com.crazy.finance.service.AdminService;
import com.crazy.finance.service.PermissionService;
import com.crazy.finance.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private PermissionService permissionService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        String name  = (String) subject.getPrincipal();
        User user = userService.selectUserByUserName(name);
        if (user!=null){
            List<Permissions> list = permissionService.selectUserById(1);
            for (Permissions p:list){
                info.addStringPermission(p.getPermission());
            }

        }
        Admin admin = adminService.selectAdminByUserName(name);
        if (admin!=null){
            List<Permissions> list = permissionService.selectAdminById(1);
            for (Permissions p:list){
                info.addStringPermission(p.getPermission());
            }
        }

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)token;
        String username = usernamePasswordToken.getUsername();
        User user = userService.selectUserByUserName(username);
        if (user!=null){
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            session.setAttribute("name","user");
            session.setAttribute("loginUser",user);
            user.setStatus(1);
            userService.updateUserStatus(user);
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        }

        Admin admin = adminService.selectAdminByUserName(username);
        if(admin!=null){
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            session.setAttribute("name","admin");
            session.setAttribute("loginAdmin",admin);
            admin.setStatus(1);
            adminService.updateAdminStatus(admin);
            return new SimpleAuthenticationInfo(admin.getUsername(), admin.getPassword(),getName());
        }

        return null;
    }
}
