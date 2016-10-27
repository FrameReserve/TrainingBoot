package com.training.sysmanager.entity;

/**
 * Created by Athos on 2016-07-16.
 */
public enum AclResourceTypeEnum {

    MODULE("M"),    //模块

    SUBMODULE("S"),//子模块

    REQUEST("R");//请求,CRUD


    String value;

    AclResourceTypeEnum() {
    }

    AclResourceTypeEnum(String value) {
        this.value = value;
    }
}
