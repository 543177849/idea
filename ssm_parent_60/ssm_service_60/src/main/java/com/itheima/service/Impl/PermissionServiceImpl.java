package com.itheima.service.Impl;

import com.itheima.dao.PermissionDao;
import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PermissionServiceImpl implements PermissionService{

    private PermissionDao permissionDao;
    @Override
    public List<Permission> findAllPrimission() {
        return permissionDao.findAllPrimission();
    }
}
