package com.oee.pikachu.repository;

import com.oee.pikachu.domain.NewsModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Aqua on 2018/11/10.
 */
public interface NewsModuleRepository extends JpaRepository<NewsModule, Long> {

    List<NewsModule> queryAllByStatus(String status);
}
