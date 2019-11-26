package hoo.hcute.security.validator;

import hoo.hcute.security.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * ConstraintValidator <A, T>
 *     A 表述你要校验的注解
 *     T 表示你要校验的属性的类型
 * 这个类中可以使用 @Autowired 等注解引入spring管理的类 ,该类上不需要@Competent等注解
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {


    @Autowired
    private HelloService helloService;


    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my validator init");

    }

    /**
     *
     * @param value 校验的属性的值
     * @param constraintValidatorContext 校验的上下文
     * @return true 返回校验成功， false 返回校验失败
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        helloService.greeting("tom");
        System.out.println(value);
        return false;
    }
}
