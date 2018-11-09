package com.example.demo.service;

import com.example.demo.model.AyUserRoleRel;

import java.util.List;

/**
 * @Decription:用户角色关联Service
 * @author wangX
 * @date 2018/7/24 11:15
 *
 */
public interface AyUserRoleRelService {
    List<AyUserRoleRel> findByUserId(String userId);
}
