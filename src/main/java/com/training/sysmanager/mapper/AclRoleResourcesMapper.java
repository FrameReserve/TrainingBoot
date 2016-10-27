package com.training.sysmanager.mapper;

import com.training.sysmanager.entity.AclRoleResources;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Athos on 2016-07-12.
 */
public interface AclRoleResourcesMapper extends Mapper<AclRoleResources> {

    /**
     *  根据 roleIds 查询权限集合
     */
    @Select("SELECT GROUP_CONCAT(resource_ids) FROM tbl_sysmgr_aclroleresources WHERE id in (#{roleIds})")
    @ResultType(String.class)
    String selectResourceIdsByRoleIds(String roleIds);
}
