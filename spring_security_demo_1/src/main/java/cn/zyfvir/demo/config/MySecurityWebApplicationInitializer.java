package cn.zyfvir.demo.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Spring会发现这个类，并用它在Web容器中注册
 * DelegatingFilterProxy
 */
public class MySecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}
