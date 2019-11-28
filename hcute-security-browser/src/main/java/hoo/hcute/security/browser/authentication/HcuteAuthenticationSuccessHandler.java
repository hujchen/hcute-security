package hoo.hcute.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import hoo.hcute.security.core.properties.LoginType;
import hoo.hcute.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class HcuteAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     *
     * @param request
     * @param response
     * @param authentication 封装认证信息的接口 【认证请求的信息：ip session等；用户信息】 由登录方式决定，下面是用户名和密码的实例
                             * {
                             *     "authorities": [-- 权限信息
                             *         {
                             *             "authority": "admin"
                             *         }
                             *     ],
                             *     "details": {
                             *         "remoteAddress": "0:0:0:0:0:0:0:1",
                             *         "sessionId": "e8db50dc-3e0e-44c5-806a-67abf4eb3242"
                             *     },
                             *     "authenticated": true, -- 已身份认证
                             *     "principal": { -- 用户的信息
                             *         "password": null, --密码
                             *         "username": "jojo",-- 用户名
                             *         "authorities": [ -- 权限
                             *             {
                             *                 "authority": "admin"
                             *             }
                             *         ],
                             *         "accountNonExpired": true,
                             *         "accountNonLocked": true,
                             *         "credentialsNonExpired": true,
                             *         "enabled": true
                             *     },
                             *     "credentials": null,
                             *     "name": "jojo"
                             * }
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");

        // 如果是数据请求，返回json
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }
        // 如果不是用默认的跳转
        else {
            super.onAuthenticationSuccess(request,response,authentication);
        }


    }
}
