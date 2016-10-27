package com.training.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import com.training.sysmanager.entity.AclResources;
import com.training.sysmanager.service.AclResourcesService;

/**
 * Created by Athos on 2016-10-16.
 */
@Component("mySecurityMetadataSource")
public class MySecurityMetadataSource  implements FilterInvocationSecurityMetadataSource {

    private static Map<String,Collection<ConfigAttribute>> aclResourceMap = null;
    private AclResourcesService aclResourcesService;

    /**
     * 构造方法
     */
    //1
    public MySecurityMetadataSource(AclResourcesService aclResourcesService){
        this.aclResourcesService=aclResourcesService;
        loadResourceDefine();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)throws IllegalArgumentException{
        HttpServletRequest request=((FilterInvocation)object).getRequest();
        Iterator<String> ite = aclResourceMap.keySet().iterator();
        while (ite.hasNext()){
            String resURL = ite.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if(requestMatcher.matches(request)){
                return aclResourceMap.get(resURL);
            }
        }
        return null;
    }
    //4
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        System.out.println("metadata : getAllConfigAttributes");
        return null;
    }
    //3
    @Override
    public boolean supports(Class<?> clazz) {
        System.out.println("metadata : supports");
        return true;
    }


    private void loadResourceDefine(){
        /**
         * 因为只有权限控制的资源才需要被拦截验证,所以只加载有权限控制的资源
         */
        List<AclResources> aclResourceses = aclResourcesService.selectAclResourcesTypeOfRequest();
        aclResourceMap = new HashMap<>();
        for (AclResources aclResources:aclResourceses){
            ConfigAttribute ca = new SecurityConfig(aclResources.getAuthority().toUpperCase());
            String url = aclResources.getUrl();
            if(aclResourceMap.containsKey(url)){
                Collection<ConfigAttribute> value = aclResourceMap.get(url);
                value.add(ca);
                aclResourceMap.put(url,value);

            }else {
                Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                atts.add(ca);
                aclResourceMap.put(url,atts);
            }
        }
    }
}
