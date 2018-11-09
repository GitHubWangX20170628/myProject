package com.example.demo.service.impl;

import com.example.demo.model.AyUserRoleRel;
import com.example.demo.repository.AyUserRoleRelRepository;
import com.example.demo.service.AyUserRoleRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Decription:用户角色关联Service
 * @author wangX
 * @date 2018/7/24 11:18
 *
 */
@Service
public class AyUserRoleRelServiceImpl implements AyUserRoleRelService {
    @Resource
    AyUserRoleRelRepository ayUserRoleRelRepository;
    @Override
    public List<AyUserRoleRel> findByUserId(String userId) {
        return ayUserRoleRelRepository.findByUserId(userId);
    }
}
