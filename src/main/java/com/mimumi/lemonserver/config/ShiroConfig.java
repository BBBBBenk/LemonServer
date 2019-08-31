package com.mimumi.lemonserver.config;

import com.mimumi.lemonserver.security.SystemShiroRealm;
import com.mimumi.lemonserver.filter.JWTFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/11.
 */
@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(systemShiroRealm());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());

        //关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    /**
     * 身份认证realm
     * @return
     */
    @Bean
    public SystemShiroRealm systemShiroRealm() {
        SystemShiroRealm systemShiroRealm = new SystemShiroRealm();
        systemShiroRealm.setCachingEnabled(true);
        systemShiroRealm.setCacheManager(cacheManager());
        return systemShiroRealm;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        //redisManager.setTimeout(timeout);
        redisManager.setPassword(password);
        redisManager.setExpire(3600);// 配置缓存过期时间
        return redisManager;
    }


    /***
     * 授权所用配置
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /***
     * 使授权注解起作用不如不想配置可以在pom文件中加入
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * Shiro生命周期处理器
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());
        factoryBean.setFilters(filterMap);

        factoryBean.setSecurityManager(securityManager);
        /*
         * 自定义url规则
         */
        Map<String, String> filterRuleMap = new LinkedHashMap<>();


        // 所有验证请求通过我们自己的JWT Filter
        filterRuleMap.put("/user/getcurrentuser", "jwt");
        filterRuleMap.put("/good/insert", "jwt");
        filterRuleMap.put("/user/getphonebyid", "jwt");
        filterRuleMap.put("/user/costpointphone", "jwt");
        filterRuleMap.put("/user/slience", "jwt");
        filterRuleMap.put("/user/chargepoint", "jwt");
        filterRuleMap.put("/user/initcode", "jwt");
        filterRuleMap.put("/comment/insert", "jwt");
        filterRuleMap.put("/scomment/insert", "jwt");
        filterRuleMap.put("/user/openvip", "jwt");
        filterRuleMap.put("/user/update", "jwt");
        filterRuleMap.put("/user/magetvip", "jwt");
        filterRuleMap.put("/user/changepoint", "jwt");
        filterRuleMap.put("/good/finished", "jwt");
        filterRuleMap.put("/social/insert", "jwt");
        filterRuleMap.put("/social/getlist", "jwt");
        filterRuleMap.put("/social/thumb", "jwt");
        filterRuleMap.put("/social/thumbdown", "jwt");
        filterRuleMap.put("/inco/getfanlist", "jwt");
        filterRuleMap.put("/divi/getmydivi", "jwt");
        filterRuleMap.put("/divi/getmytddivi", "jwt");
        filterRuleMap.put("/merchant/entering", "jwt");
        filterRuleMap.put("/user/privacymodify", "jwt");
        filterRuleMap.put("/moth/moth", "jwt");
        filterRuleMap.put("/moth/delete", "jwt");
        filterRuleMap.put("/good/deletegood", "jwt");
        filterRuleMap.put("/moth/checkmoth", "jwt");
        filterRuleMap.put("/wxpay/chargevip", "jwt");
        filterRuleMap.put("/wxpay/orderphone", "jwt");
        filterRuleMap.put("/wxpay/paySuccess", "jwt");
       /* filterRuleMap.put("*//**", "anon");*/

        // 访问401和404页面不通过我们的Filter
        //filterRuleMap.put("/401", "anon");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }


}