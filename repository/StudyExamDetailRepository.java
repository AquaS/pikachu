package com.oee.pikachu.repository;

import com.oee.pikachu.domain.StudyExamDetail;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aqua on 2018/11/8.
 */
public interface StudyExamDetailRepository extends JpaRepository<StudyExamDetail, Long> {

    List<StudyExamDetail> findAllByExamIdAndJobNumber(Long examId, String jobNumber);

    @Transactional
    @Modifying
    @CacheEvict(cacheNames = "secondlevels", allEntries = true)
    @Query(value = "insert into study_exam_detail(exam_id, question_id, job_number,real_name,my_option, question_no, my_right, my_grade, insert_time, update_time) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, now(), now())", nativeQuery = true)
    void insertExamResult(Long examId, Long questionId, String jobNumber, String realName, String myOption,
                            Integer questionNo, Integer myRight, Integer myGrade);

    @Override
    default <S extends StudyExamDetail> List<S> saveAll(Iterable<S> iterable) {
        List<S> resultList = new ArrayList<>();
        iterable.forEach(p ->{
            insertExamResult(p.getExamId(), p.getQuestionId(), p.getJobNumber(), p.getRealName(), p.getMyOption(),
                    p.getQuestionNo(), p.getMyRight(), p.getMyGrade());
            resultList.add(p);
        });
        return resultList;
    }
}
