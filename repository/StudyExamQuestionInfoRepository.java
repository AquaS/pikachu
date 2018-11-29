package com.oee.pikachu.repository;

import com.oee.pikachu.domain.StudyExamQuestionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Aqua on 2018/11/8.
 */
public interface StudyExamQuestionInfoRepository extends JpaRepository<StudyExamQuestionInfo, Long> {

    List<StudyExamQuestionInfo> findAllByExamId(Long examId);
}
