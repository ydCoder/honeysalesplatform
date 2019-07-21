package com.ydcoding.service.impl;

import com.ydcoding.dao.AdminDao;
import com.ydcoding.entity.Admin;
import com.ydcoding.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-05-12 23:56
 **/
@Service
public class AdminServiceImpl  implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    public Admin findAdmin(String adminEmail, String password) {
        return adminDao.findByAdminEmailAndPassword(adminEmail,password);
    }
}
