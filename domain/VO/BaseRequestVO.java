package com.oee.pikachu.domain.VO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by Aqua on 2018/11/11.
 */
@Data
public class BaseRequestVO implements Serializable {

    private static final long serialVersionUID = -3111167184764152472L;

    @NotBlank
    private String openId;

    private String jobNum;
}
