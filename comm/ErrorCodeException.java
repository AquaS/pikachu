package com.oee.pikachu.comm;

import com.oee.pikachu.domain.enums.ExceptionMsg;
import lombok.Data;

/**
 * Created by Aqua on 2018/11/7.
 */
@Data
public class ErrorCodeException extends RuntimeException {

    /** 返回信息码*/
    private String rspCode="000000";
    /** 返回信息内容*/
    private String rspMsg="操作成功";

    public ErrorCodeException(ExceptionMsg msg) {
        this.rspCode = msg.getCode();
        this.rspMsg = msg.getMsg();
    }
}
