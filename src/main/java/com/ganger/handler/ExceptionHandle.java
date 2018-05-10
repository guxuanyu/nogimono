package com.ganger.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ganger.VO.ResponseVo;
import com.ganger.exception.MyException;
import com.ganger.utils.ResponseUtil;

@ControllerAdvice
public class ExceptionHandle {
    public final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseVo handle(Exception e) {
        if (e instanceof MyException) {
        	logger.error("[err] ----- {}",((MyException) e).getLogmsg());
            return ResponseUtil.error(((MyException) e).getShowmsg());
        } else {
            logger.error("[err][sys] ===== {}", e);
            
            return ResponseUtil.error("系统异常"+e.getMessage());
        }
    }
}

