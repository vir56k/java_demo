package cn.zyfvir.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.stereotype.Component;

// 启用：组件搜索
// 启用：aspect 的自动代理
@Component
@ComponentScan
@EnableAspectJAutoProxy
public class MySrpingConfig {


}
