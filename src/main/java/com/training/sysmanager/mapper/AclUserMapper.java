package com.training.sysmanager.mapper;

import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.training.sysmanager.entity.AclUser;

/**
 * Created by Athos on 2016-06-30.
 */
public interface AclUserMapper extends Mapper<AclUser> {
    @Select("SELECT * FROM tbl_sysmgr_acluser WHERE user_name = #{userName}")
    AclUser findAclUserByName(String userName);
}
