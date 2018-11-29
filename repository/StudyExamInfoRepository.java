package com.oee.pikachu.repository;

import com.oee.pikachu.domain.StudyExamInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Aqua on 2018/11/8.
 */
public interface StudyExamInfoRepository extends JpaRepository<StudyExamInfo, Long> {

    List<StudyExamInfo> findAllByModuleOneAndModuleTwo(String moduleOne, String moduleTwo);

    StudyExamInfo getById(Long id);
}
