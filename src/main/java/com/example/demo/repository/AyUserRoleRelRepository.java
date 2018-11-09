package com.example.demo.repository;

import com.example.demo.model.AyUserRoleRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangX
 * @Decription: 用户角色关联Repository
 * @date 2018/7/24 11:11
 */
public interface AyUserRoleRelRepository extends JpaRepository<AyUserRoleRel, String> {
    List<AyUserRoleRel> findByUserId(@Param("userId") String userId);
}
