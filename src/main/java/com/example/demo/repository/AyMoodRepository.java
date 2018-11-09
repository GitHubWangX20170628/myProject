package com.example.demo.repository;

import com.example.demo.model.AyMood;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Decription:
 * @author wangX
 * @date 2018/7/20 14:48
 *
 */
public interface AyMoodRepository extends JpaRepository<AyMood,String> {
    
}
