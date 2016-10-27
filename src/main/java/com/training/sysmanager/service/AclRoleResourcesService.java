package com.training.sysmanager.service;

import com.training.core.service.BaseService;
import com.training.sysmanager.entity.AclRoleResources;

/**
 * Created by Athos on 2016-07-12.
 */
public interface AclRoleResourcesService extends BaseService<AclRoleResources> {
    /**
     *  根据 roleIds 查询权限集合
     */
    String selectResourceIdsByRoleIds(String roleIds);
}
