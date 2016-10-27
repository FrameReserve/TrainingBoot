package com.training.sysmanager.service.impl;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.training.core.annotation.MapperClass;
import com.training.core.service.impl.MyBatisBaseServiceImpl;
import com.training.sysmanager.entity.AclRoleResources;
import com.training.sysmanager.mapper.AclRoleResourcesMapper;
import com.training.sysmanager.service.AclRoleResourcesService;

/**
 * Created by Athos on 2016-07-12.
 */
@Service
@MapperClass(AclRoleResources.class)
public class AclRoleResourcesServiceImpl extends MyBatisBaseServiceImpl<AclRoleResources> implements AclRoleResourcesService{

    protected AclRoleResourcesMapper getMapper(){
        return super.getMapper(AclRoleResources.class);
    }

    @Override
    public String selectResourceIdsByRoleIds(String roleIds) {
        String resourceIds = this.getMapper().selectResourceIdsByRoleIds(roleIds);
        String [] resourceIdsArray = resourceIds.split(",");
        ArrayList<String> list = new ArrayList<>();
        for(String resourceId : resourceIdsArray){
            if(!list.contains(resourceId)){
                list.add(resourceId);
            }
        }
        return StringUtils.join(list.toArray(),",");
    }
}
