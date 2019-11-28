package hoo.hcute.security.core.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ValidateCode implements Serializable {

    private static final long serialVersionUID = 8276547497628727211L;

    private String code;

    // 过期的时间点
    private LocalDateTime expireTime;


    /**
     * @param code
     * @param expireIn  过期的时间
     */
    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime){
        this.code = code;
        this.expireTime = expireTime;
    }


    public boolean isExpried(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
