package com.example.demo.service.impl;

import com.example.demo.model.AyRole;
import com.example.demo.repository.AyRoleRepository;
import com.example.demo.service.AyRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Decription:用户角色Service
 * @author wangX
 * @date 2018/7/24 11:17
 *
 */
@Service
public class AyRoleServiceImpl implements AyRoleService {
    @Resource
    private AyRoleRepository ayRoleRepository;
    @Override
    public AyRole find(String id) {
        return ayRoleRepository.findById(id).get();
    }
}
