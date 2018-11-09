package com.example.demo.service;

import com.example.demo.model.AyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author wangX
 * @description:用戶服務接口
 * @date 2018/7/16 20:37
 */
public interface AyUserService {
    AyUser findById(String id);

    List<AyUser> findAll();

    //异步查询
    Future<List<AyUser>> findAsynAll();

    List<AyUser> findByName(String name);

    List<AyUser> findByNameLike(String name);

    List<AyUser> findByIdIn(Collection<String> ids);

    AyUser save(AyUser user);

    void delte(String id);

    //分頁
    Page<AyUser> findAll(Pageable pageable);

    AyUser findByNameAndPassword(String name, String password);

    AyUser findByNameAndPassworRetry(String name, String password);
    AyUser findByUserName(String name);

}
