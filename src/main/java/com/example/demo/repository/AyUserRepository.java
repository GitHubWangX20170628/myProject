package com.example.demo.repository;

import com.example.demo.model.AyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author wangX
 * @date 2018/7/16 20:33
 */
public interface AyUserRepository extends JpaRepository<AyUser, String> {
    /**
     * 描述：通過名字相等查詢，參數為name
     * 相當於：select  u  from ay_user u where  u.name = ?1
     */
    List<AyUser> findByName(String name);

    /**
     * 描述：通過名字like查詢，參數為name
     * 相當於；select u  from ay_user u where u.name like ?1
     */
    List<AyUser> findByNameLike(String name);

    /**
     * 描述：通過主鍵id集合查詢，參數為id集合
     * 相當於：select u  from ay_user u where id in (?,?,?)
     *
     * @param ids
     */
    List<AyUser> findByIdIn(Collection<String> ids);
}
