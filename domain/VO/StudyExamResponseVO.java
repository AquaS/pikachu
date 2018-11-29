package com.oee.pikachu.domain.VO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Aqua on 2018/11/28.
 */
@Data
@Accessors(chain = true)
public class StudyExamResponseVO implements Serializable {
    private static final long serialVersionUID = 3783586656815917846L;

    private String studyExamName;

    private String status;

    private Integer grade;

    private Date beginTime;
}
