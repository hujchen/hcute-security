package hoo.hcute.security.core.validate.code.image;

import hoo.hcute.security.core.validate.code.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;

@Data
public class ImageCode extends ValidateCode implements Serializable {

    private static final long serialVersionUID = 8276547497628727211L;

    private BufferedImage image;
    /**
     *
     * @param image
     * @param code
     * @param expireIn  过期的时间
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code,expireIn);
        this.image = image;
    }

}
