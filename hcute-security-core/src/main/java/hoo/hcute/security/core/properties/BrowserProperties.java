package hoo.hcute.security.core.properties;


import lombok.Data;

@Data
public class BrowserProperties {

    private String loginPage = "/hcute-signIn.html";

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 3600;


}
