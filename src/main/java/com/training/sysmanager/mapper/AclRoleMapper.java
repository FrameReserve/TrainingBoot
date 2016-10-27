package com.training.sysmanager.mapper;

import com.training.sysmanager.entity.AclRole;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Athos on 2016-07-12.
 */
public interface AclRoleMapper extends Mapper<AclRole>{
    /**
     *  根据AclUser的属性值查询返回逗号间隔的角色集字符串
     */
    @Select("SELECT GROUP_CONCAT(aclrole.pronoun) as pronoun FROM tbl_sysmgr_aclrole as aclrole where INSTR(CONCAT(',',#{roleIds},','),CONCAT(',',aclrole.id,','))")
    String findAclRolesByAclUserRoleIds(String roleIds);
}