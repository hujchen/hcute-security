package hoo.hcute.security.core.properties;


import lombok.Data;

@Data
public class SmsCodeProperties {
    private int length = 6;
    private int expire = 60;
    private String url;

}
