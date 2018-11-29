package com.oee.pikachu.repository;

import com.oee.pikachu.domain.TopicTypeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Aqua on 2018/11/18.
 */
public interface TopicTypeInfoRepository extends JpaRepository<TopicTypeInfo, Long> {

    List<TopicTypeInfo> findAll();
}
