package hoo.hcute.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "hcute.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

}
