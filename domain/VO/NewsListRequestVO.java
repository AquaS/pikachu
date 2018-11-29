package com.oee.pikachu.domain.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Aqua on 2018/11/11.
 */
@Data
public class NewsListRequestVO extends BaseRequestVO implements Serializable {

    @NotNull
    private Long moduleId;

    private Long newId;
}
