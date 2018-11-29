package com.oee.pikachu.domain.VO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by Aqua on 2018/11/8.
 */
@Data
public class ExamRequestVO implements Serializable {
    private static final long serialVersionUID = 2253494252634197593L;

    @NotNull
    private Long examId;

    @NotEmpty
    private Map<Long, String> answers;
}
