package com.example.demo.security;

import com.example.demo.error.BusinessException;
import com.example.demo.model.AyUser;
import com.example.demo.model.AyUserRoleRel;
import com.example.demo.service.AyRoleService;
import com.example.demo.service.AyUserRoleRelService;
import com.example.demo.service.AyUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class CustomUsrService implements UserDetailsService {
    @Resource
    private AyUserService ayUserService;
    @Resource
    private AyRoleService ayRoleService;
    @Resource
    private AyUserRoleRelService ayUserRoleRelService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username :"+username);
        AyUser ayUser = ayUserService.findByUserName(username);
        if (ayUser == null) {
            throw new BusinessException("用户不存在");
        }
        //获取用户所有的关联角色
        List<AyUserRoleRel> ayUserRoleRelList=ayUserRoleRelService.findByUserId(ayUser.getId());
        List<GrantedAuthority> authorityList=new ArrayList<GrantedAuthority>();
        if (ayUserRoleRelList!=null && ayUserRoleRelList.size()>0){
            for(AyUserRoleRel rel:ayUserRoleRelList){
                    //获取用户关联角色名称
                String roleName =ayRoleService.find(rel.getRoleId()).getName();
                authorityList.add(new SimpleGrantedAuthority(roleName));
            }
        }
        return new User(ayUser.getName(),ayUser.getPassword(),authorityList);
    }
}
