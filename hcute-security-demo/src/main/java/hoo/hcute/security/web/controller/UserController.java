package hoo.hcute.security.web.controller;


import com.fasterxml.jackson.annotation.JsonView;
import hoo.hcute.security.dto.User;
import hoo.hcute.security.dto.UserQueryCondition;
import hoo.hcute.security.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

//    @GetMapping("/me")
//    public Object getCurrentUser(){
//        return SecurityContextHolder.getContext().getAuthentication();
//    }

//    @GetMapping("/me")
//    public Object getCurrentUser(Authentication authentication){
//        return authentication;
//    }

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }



    @PostMapping
    public User create(@Valid  @RequestBody User user) {

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getId());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;

    }

//    @PostMapping
//    public User create(@Valid  @RequestBody User user, BindingResult errors) {
//
//        if (errors.hasErrors()){
//            errors.getAllErrors().stream().forEach(error -> {
//                System.out.println(error.getDefaultMessage());
//            });
//        }
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
//        System.out.println(user.getId());
//        System.out.println(user.getBirthday());
//
//        user.setId(1);
//        return user;
//
//    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid  @RequestBody User user, BindingResult errors) {

        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> {
//                FieldError fieldError = (FieldError) error;
//                System.out.println(fieldError.getField() + ":" + fieldError.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            });
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getId());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;

    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id){
        System.out.println(id);
    }

//    @RequestMapping(value = "/user",method = RequestMethod.GET)
//    public List<User> query(@RequestParam(name = "username", required = false, defaultValue = "hcute") String nickname){
//        System.out.println(nickname);
//        List<User> users = new ArrayList<>();
//        users.add(new User());
//        users.add(new User());
//        users.add(new User());
//        return users;
//    }


    // Pageable 是SpringData 提供的对象
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    // swagger2 方法描述
    // @ApiOperation(value = "用户查询服务")
    // swagger2 参数描述
    // -》1。对象方式传入参数。在对象上标注@ApiModelProperty(value = "")
    // -》2。参数是基本类型，@ApiParam(value="")
    @ApiOperation(value = "用户查询服务")
    public List<User> query(UserQueryCondition condition,@PageableDefault(page = 2, size = 17 , sort =" username,asc") Pageable pageable){
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(pageable.getPageSize() + ": \n" + pageable.getPageNumber() + ": \n" + pageable.getSort());
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }


    @GetMapping(value = "/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@ApiParam(value = "用户id",example = "1") @PathVariable(value = "id") String idxxxx){
//          throw new RuntimeException("user not exist");
//        throw new UserNotExistException(1);
//        System.out.println("进入getInfo服务");
        User user = new User();
        user.setUsername("tom");
        return user;
    }


}
