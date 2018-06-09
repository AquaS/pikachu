package com.oee.pikachu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @Description:
 * @Auth: yangyanming
 * @Date: 2018/6/9
 * @Version: 1.0
 **/
public class Entitys implements Serializable {
    private static final long serialVersionUID = 4684184794650724639L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
