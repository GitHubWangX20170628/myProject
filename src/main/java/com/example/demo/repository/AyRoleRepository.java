package com.example.demo.repository;

import com.example.demo.model.AyRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Decription:用户角色Repository
 * @author wangX
 * @date 2018/7/24 11:10
 *
 */
public interface AyRoleRepository extends JpaRepository<AyRole,String> {
}
