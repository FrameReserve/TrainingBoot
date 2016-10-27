package com.training.sysmanager.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.training.core.annotation.MapperClass;
import com.training.core.service.impl.MyBatisBaseServiceImpl;
import com.training.sysmanager.entity.AclResources;
import com.training.sysmanager.mapper.AclResourcesMapper;
import com.training.sysmanager.service.AclResourcesService;

/**
 * Created by Athos on 2016-07-12.
 */
@Service
@MapperClass(AclResourcesMapper.class)
public class AclResourcesServiceImpl extends MyBatisBaseServiceImpl<AclResources> implements AclResourcesService {

    protected AclResourcesMapper getMapper(){
        return super.getMapper(AclResources.class);
    }

    @Override
    public List<AclResources> selectAclResourcesTypeOfRequest(){
       return getMapper().selectAclResourcesTypeOfRequest();
    }

    @Override
    public List<AclResources> selectAclResourcesByResourceIds(String resourceIds) {
        return getMapper().selectAclResourcesByResourceIds(resourceIds.split(","));
}
}
