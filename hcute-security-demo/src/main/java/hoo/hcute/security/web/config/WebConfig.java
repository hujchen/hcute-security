package hoo.hcute.security.web.config;

import hoo.hcute.security.web.filter.TimeFilter;
import hoo.hcute.security.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TimeInterceptor timeInterceptor;


    /**
     * 配置异步请求的相关配置
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        // deferredResult方式的拦截器注册
//        configurer.registerDeferredResultInterceptors();
        // callable 方式的拦截器注册
//        configurer.registerCallableInterceptors();
        // 异步处理的超时时间的设置
//        configurer.setDefaultTimeout(5000);

        // 配置线程池
        // Runnable 执行的时候，Spring使用自己简单的异步线程池，每次调用新开线程池
//        configurer.setTaskExecutor()
    }

    //    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);
        // 配置过滤器在哪些url 进行过滤
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);

        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(timeInterceptor);
    }
}
