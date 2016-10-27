package com.training.sysmanager.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.core.controller.BaseController;
import com.training.core.dto.ResultDataDto;
import com.training.core.exception.RuntimeServiceException;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/")
public class LoginController extends BaseController {
	
	
	// 登录成功，获取当前用户信息
	@RequestMapping(value = "getCurrentLoginedUser")
	public @ResponseBody ResultDataDto getCurrentLoginedUser() {

//		MemberInformation memberInformation = UserContainerSessionUtil.getMemberInformation();
//		if (null == memberInformation) {
//			return ResultDataDto.addSuccess();
//		}
//		Integer integral = 0;
//		MemberLoyaltyPoints memberLoyaltyPoints = memberLoyaltyPointsService.findMemberLoyaltyPointsCountTotal(memberInformation.getMember_id());
//		if (null != memberLoyaltyPoints) {
//			integral = memberLoyaltyPoints.getIntegral();
//		}
//
//		return ResultDataDto.addSuccess().setDatas(new MemberinformationLoginDto(memberInformation, integral));
		return ResultDataDto.addSuccess();
	}

	// 登录异常
	@RequestMapping(value = "getLoginError")
	public @ResponseBody ResultDataDto getLoginError(HttpSession session) {

		RuntimeException ex = (RuntimeException) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if (ex instanceof AuthenticationServiceException) {

			if (ex.getCause() instanceof NullPointerException) {
				// 此帐户已被锁定,请联系管理员
				throw new RuntimeServiceException("此帐户已被锁定,请联系管理员");
			}

			throw new RuntimeServiceException("用户名错误");
		} else if (ex instanceof BadCredentialsException) {
			// 密码错误
			throw new RuntimeServiceException("密码错误");
		} else if (ex instanceof DisabledException) {
			// 帐户锁定
			throw new RuntimeServiceException("账户未激活，请到邮箱激活");
		} else if (ex.getMessage().equals("checkcode")) {
			throw new RuntimeServiceException("验证码有误");
		} else {
			// 登陆验证错误
			throw new RuntimeServiceException("登陆验证错误");
		}
	}
}
