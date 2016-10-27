package com.training.sysmanager.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.training.core.annotation.MapperClass;
import com.training.core.entity.BaseEntity;
import com.training.sysmanager.mapper.AclRoleMapper;

/**
 * Created by Athos on 2016-06-29.
 */
@NameStyle(value = Style.camelhumpAndLowercase)
@Table(name = "tbl_sysmgr_aclrole")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@MapperClass(AclRoleMapper.class)
public class AclRole extends BaseEntity {
    public AclRole(){}
    public AclRole(String roleName,String pronoun){
        this.roleName = roleName;
        this.pronoun = pronoun;
    }

    /**
     * 角色名
     */
    @Column
    private String roleName;

    /**
     * 角色代名词
     */
    @Column
    private String pronoun;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPronoun() {
        return pronoun;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }
}
