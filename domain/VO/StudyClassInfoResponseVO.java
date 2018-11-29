package com.oee.pikachu.domain.VO;

import com.oee.pikachu.domain.StudyClassInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aqua on 2018/11/27.
 */
@Data
@Accessors(chain = true)
public class StudyClassInfoResponseVO implements Serializable {
    private static final long serialVersionUID = 4260588446892389722L;

    private Long studyModuleId;

    private String studyModule;

    private List<StudyClassInfo> classInfos;
}
