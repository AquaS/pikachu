package com.oee.pikachu.domain.VO;

import com.oee.pikachu.domain.Entitys;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by Aqua on 2018/11/11.
 */
@Data
public class SurveyRequestVO extends Entitys implements Serializable {

    private static final long serialVersionUID = -7500644106936073264L;

    @NotNull
    private Long surveyId;

    @NotEmpty
    private Map<Long, String> answers;
}
