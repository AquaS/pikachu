package com.oee.pikachu.repository;

import com.oee.pikachu.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Aqua on 2018/11/10.
 */
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> queryAllByModuleId(Long moduleId);

    News queryById(Long id);
}
