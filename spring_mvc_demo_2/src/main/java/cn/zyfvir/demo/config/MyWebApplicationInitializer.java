//package cn.zyfvir.demo;
//
//import org.springframework.web.WebApplicationInitializer;
//
//import javax.servlet.FilterRegistration;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
///**
// * 初始化 web 应用
// */
//public class MyWebApplicationInitializer implements WebApplicationInitializer {
//
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        // 注册一个 servlet
//        ServletRegistration.Dynamic servlet = servletContext.addServlet("myservlet1", MyServlet.class);
//        servlet.addMapping("/custom/**");
//
//        // 注册一个过滤器
//        FilterRegistration.Dynamic filter1 = servletContext.addFilter("filter1", MyFilter1.class);
//        filter1.addMappingForUrlPatterns(null, false, "/custom/*");
//    }
//}
