package com.training.sysmanager.mapper;

import com.training.sysmanager.entity.AclRequestType;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Athos on 2016-07-12.
 */
public interface AclRequestTypeMapper extends Mapper<AclRequestType> {

    @Select("SELECT GROUP_CONCAT(aclrequesttype.pronoun) as pronoun FROM tbl_sysmgr_aclrequesttype as aclrequesttype where INSTR(CONCAT(',',#{requestTypeIds},','),CONCAT(',',aclrequesttype.id,','))")
    String findPronounStrByRequestTypeIds(String requestTypeIds);
}
