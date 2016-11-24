package com.training.sysmanager.controller;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.training.core.mybatis.DataSourceContextHolder;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.training.core.controller.BaseController;
import com.training.sysmanager.service.AclRequestTypeService;

import java.sql.SQLException;

/**
 * Created by Athos on 2016-07-14.
 */
@RestController
@RequestMapping("/food")
public class TestController extends BaseController {

    @Resource
    private AclRequestTypeService aclRequestTypeService;

    @PreAuthorize("hasAuthority('AUTH_FOOD_QUERY')")
    @RequestMapping(value = "/queryFood",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void foods(){
        System.out.println(aclRequestTypeService.getEntityById(1));
        System.out.println("读取食物.....");
    }

    @PreAuthorize("hasAuthority('AUTH_FOOD_ADD')")
    @RequestMapping("/addFood")
    public  void add(){
        System.out.println("新增食物");
    }

    @RequestMapping("/selectCountByNeId")
    public  void selectCountByNeId() throws SQLException {
        DataSourceContextHolder.read();
        System.out.println(aclRequestTypeService.getEntityById(1).getPronoun());
        DataSourceContextHolder.write();
        System.out.println(aclRequestTypeService.getEntityById(1).getPronoun());
    }
}
