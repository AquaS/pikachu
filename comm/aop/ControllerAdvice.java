package com.oee.pikachu.comm.aop;

import com.google.common.base.Throwables;
import com.oee.pikachu.comm.ErrorCodeException;
import com.oee.pikachu.domain.result.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Aqua on 2018/11/11.
 */
@org.springframework.web.bind.annotation.ControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(ErrorCodeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response handleErrorCodeException(ErrorCodeException codeException) {
        log.error("handle errorCode exception. due to error[{}]",
                Throwables.getStackTraceAsString(codeException));
        return new Response(codeException.getRspCode(), codeException.getRspMsg());
    }
}
