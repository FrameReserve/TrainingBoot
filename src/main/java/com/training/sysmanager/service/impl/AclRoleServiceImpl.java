package com.training.sysmanager.service.impl;

import org.springframework.stereotype.Service;

import com.training.core.annotation.MapperClass;
import com.training.core.service.impl.MyBatisBaseServiceImpl;
import com.training.sysmanager.entity.AclRole;
import com.training.sysmanager.mapper.AclRoleMapper;
import com.training.sysmanager.service.AclRoleService;

/**
 * Created by Athos on 2016-07-12.
 */
@Service
@MapperClass(AclRoleMapper.class)
public class AclRoleServiceImpl extends MyBatisBaseServiceImpl<AclRole> implements AclRoleService{

    protected AclRoleMapper getMapper(){
        return super.getMapper(AclRole.class);
    }

    @Override
    public String findAclRolesByAclUserRoleIds(String roleIds) {
        return this.getMapper().findAclRolesByAclUserRoleIds(roleIds);
    }
}