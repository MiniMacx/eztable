package org.tustcs.eztable.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.tustcs.eztable.enums.ResultEnums;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserException extends RuntimeException{

    private Integer code;

    public UserException(ResultEnums resultEnums) {
        super(resultEnums.getMessage());
        this.code = resultEnums.getCode();
    }
}
