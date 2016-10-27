package com.training.sysmanager.service.impl;

import org.springframework.stereotype.Service;

import com.training.core.annotation.MapperClass;
import com.training.core.service.impl.MyBatisBaseServiceImpl;
import com.training.sysmanager.entity.AclUser;
import com.training.sysmanager.mapper.AclUserMapper;
import com.training.sysmanager.service.AclUserService;

/**
 * Created by Athos on 2016-07-02.
 */
@Service
@MapperClass(AclUserMapper.class)
public class AclUserServiceImpl extends MyBatisBaseServiceImpl<AclUser> implements AclUserService {
//    @CountTime
    @Override
    public AclUser findAclUserByName(String userName) {
        return getMapper().findAclUserByName(userName);
    }
    protected AclUserMapper getMapper(){
        return super.getMapper(AclUser.class);
    }
}
