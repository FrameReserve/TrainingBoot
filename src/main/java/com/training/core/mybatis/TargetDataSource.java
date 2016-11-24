package com.training.core.mybatis;

/**
 * Created by ZhenWeiLai on 2016/11/22.
 */
public enum TargetDataSource {

    WRITE("write","主库"), READ("read","从库");

    final private String code;

    final private String name;

    TargetDataSource(String _code,String _name) {
        this.code = _code;
        this.name = _name;
    }

    public String getCode() {
        return code;
    }

    public String getName(){
        return name;
    }

    public static String getNameByCode(String _code){
        for(TargetDataSource item : TargetDataSource.values()){
            if(item.getCode().equals(_code))
            {
                return item.getName();
            }
        }
        return "";
    }
}
