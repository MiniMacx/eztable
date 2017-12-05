package org.tustcs.eztable.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.tustcs.eztable.enums.ResultEnums;

import java.io.File;

@EqualsAndHashCode(callSuper = true)
@Data
public class FileException extends RuntimeException{
    private Integer code;

    public FileException(ResultEnums resultEnums){
        super(resultEnums.getMessage());
        this.code=resultEnums.getCode();
    }
}
