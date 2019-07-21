package com.ydcoding.service;

import com.ydcoding.entity.Admin;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-05-12 23:53
 **/
public interface AdminService {
    Admin findAdmin(String adminEmail, String password);
}
