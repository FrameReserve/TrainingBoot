package com.training.sysmanager.service;

import com.training.core.service.BaseService;
import com.training.sysmanager.entity.AclRequestType;

/**
 * Created by Athos on 2016-07-12.
 */
public interface AclRequestTypeService extends BaseService<AclRequestType> {
    String findPronounStrByRequestTypeIds(String requestTypeIds);
    int selectCountByNeId(AclRequestType aclRequestType);
}
