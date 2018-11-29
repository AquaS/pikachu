package com.oee.pikachu.repository;

import com.oee.pikachu.domain.StudyExamQuestionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Aqua on 2018/11/8.
 */
public interface StudyExamQuestionDetailRepository extends JpaRepository<StudyExamQuestionDetail, Long> {

    List<StudyExamQuestionDetail> findAllByQuestionIdIn(List<Long> questionIds);
}
