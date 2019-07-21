package com.ydcoding.dao;

import com.ydcoding.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-03-12 23:45
 **/
public interface AdminDao  extends JpaRepository<Admin,Integer> {

     Admin findByAdminEmailAndPassword(String adminEmail,String password);

}
