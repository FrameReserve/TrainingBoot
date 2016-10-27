package com.training.sysmanager.controller;


//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Athos on 2016-09-29.
 */
@RestController("/json")
public class OAuthController {
    @RequestMapping("/list")
//    @PreAuthorize("hasAuthority('AUTH_FOOD_QUERY')")
    public void testOAuth(){

    }
}
