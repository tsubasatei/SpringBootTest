package com.xt.springboot.repository;


import com.xt.springboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

/**
 * 继承 JpaRepository 完成对数据库的操作
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
