package com.oee.pikachu.domain.VO;

import com.oee.pikachu.domain.StudyExamQuestionDetail;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aqua on 2018/11/8.
 */
@Data
@Accessors(chain = true)
public class StudyExamQuestionVO implements Serializable {

    private static final long serialVersionUID = -2490946442420145617L;

    private Long questionId;

    private String questionName;

    private String questionType;

    private List<StudyExamQuestionDetail> options;
}
