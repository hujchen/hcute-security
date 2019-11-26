package hoo.hcute.security;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * exclude = {SecurityAutoConfiguration.class,ManagementWebSecurityAutoConfiguration.class}不进行Spring Security的拦截
 */
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,
//        ManagementWebSecurityAutoConfiguration.class})

@SpringBootApplication
@RestController
@EnableSwagger2
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }

    @GetMapping("hello")
    public String hello(){
        return "hello spring security";
    }

}
