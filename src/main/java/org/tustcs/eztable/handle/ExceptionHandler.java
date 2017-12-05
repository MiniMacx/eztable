package org.tustcs.eztable.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tustcs.eztable.exception.FileException;
import org.tustcs.eztable.exception.UserException;
import org.tustcs.eztable.utils.Res;
import org.tustcs.eztable.utils.ResUtil;

@ControllerAdvice
public class ExceptionHandler {
    private static final Logger logger= LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = UserException.class)
    @ResponseBody
    public Res userHandle(Exception e){
        if(e instanceof UserException){
            UserException userException=(UserException) e;
            logger.error("【userException异常细节】 : {}",e);
            return ResUtil.error(userException.getCode(),userException.getMessage());
        }else {
            logger.error("【系统异常】：{}",e);
            return ResUtil.error(-1,"未知错误");
        }

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = FileException.class)
    @ResponseBody
    public Res fileHandle(Exception e){
        if(e instanceof FileException){
            FileException fileException=(FileException) e;
            logger.error("【fileException异常细节】 : {}",e);
            return ResUtil.error(fileException.getCode(),fileException.getMessage());
        }else {
            logger.error("【系统异常】：{}",e);
            return ResUtil.error(-1,"未知错误");
        }

    }
}
