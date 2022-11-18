package umc.week6.global.error;

import lombok.Getter;
import umc.week6.global.error.dto.ErrorCode;

@Getter
public class DefaultNullPointerException extends NullPointerException{

    private ErrorCode errorCode;

    public DefaultNullPointerException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}