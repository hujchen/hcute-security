package hoo.hcute.security.browser;

import hoo.hcute.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * web 应用安全配置适配器
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()// 表单登录
            .loginPage("/authentication/require") // 登录页面
            .loginProcessingUrl("/authentication/form") // 登录请求
            .and()
            .authorizeRequests() // 请求授权
            .antMatchers("/authentication/require",
                    securityProperties.getBrowser().getLoginPage()).permitAll() // 该请求不需要认证
            .anyRequest() // 任何请求
            .authenticated()// 都需要身份认证
            .and()
            .csrf().disable();// 跨站防护关闭

    }
}
