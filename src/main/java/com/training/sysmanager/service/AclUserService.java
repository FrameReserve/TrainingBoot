package com.training.sysmanager.service;

import com.training.core.service.BaseService;
import com.training.sysmanager.entity.AclUser;

/**
 * Created by Athos on 2016-07-02.
 */
public interface AclUserService extends BaseService<AclUser> {
    AclUser findAclUserByName(String userName);
}
