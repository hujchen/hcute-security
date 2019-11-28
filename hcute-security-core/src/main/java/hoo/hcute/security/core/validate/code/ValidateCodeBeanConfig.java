package hoo.hcute.security.core.validate.code;

import hoo.hcute.security.core.properties.SecurityProperties;
import hoo.hcute.security.core.validate.code.image.ImageCodeGenerator;
import hoo.hcute.security.core.validate.code.sms.DefaultSmsSender;
import hoo.hcute.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * @ConditionalOnMissingBean(name = "imageCodeGenerator")
     * spring 容器在初始化的时候会查找上下文中是否有 名字为imageCodeGenerator 的的Bean
     * 如果有 则不进行实例化
     * 如果没有 则实例化这个bean
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsSender();
    }
}
