package umc.week6.global.error;

import lombok.Getter;
import umc.week6.global.error.dto.ErrorCode;

@Getter
public class DefaultException extends RuntimeException {

    private ErrorCode errorCode;

    public DefaultException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public DefaultException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}