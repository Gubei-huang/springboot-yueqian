package com.crazy.finance.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Configuration
public class ShiroConfig {

   @Bean
   public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
       ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
       bean.setSecurityManager(securityManager);

       Map<String, String> filterMap = new LinkedHashMap();

       filterMap.put("/error/**", "anon");
       filterMap.put("/", "anon");
       filterMap.put("/index.html", "anon");
       filterMap.put("/toregister.html", "anon");
       filterMap.put("/login/**", "anon");
       filterMap.put("/asserts/**", "anon");
       filterMap.put("/bootstrap/**", "anon");
       filterMap.put("/images/**", "anon");
       filterMap.put("/lyear/**", "anon");
       filterMap.put("/js/**", "anon");

       filterMap.put("/**", "authc");

       filterMap.put("/logout", "logout");

       bean.setFilterChainDefinitionMap(filterMap);
//     设置登录请求（认证界面）
       bean.setLoginUrl("/");

       return bean;
   }

    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean("userRealm")
    public UserRealm getUserRealm() {
        return new UserRealm();
    }

    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

}
