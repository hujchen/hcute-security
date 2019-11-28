package hoo.hcute.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {


    private static final long serialVersionUID = 7388607069379561077L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
