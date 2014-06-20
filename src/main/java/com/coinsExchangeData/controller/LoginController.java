package com.coinsExchangeData.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login() {
		//System.out.println("Inside login method");
		return "login";
	}
	
	@RequestMapping(value="/loginFailed", method=RequestMethod.GET)
	public String loginFailed() {
		System.out.println("Inside loginFailed method");
		return "loginFailed";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
		System.out.println("Inside logout method");
		return "logout";
	}
	
	@RequestMapping(value="/403", method=RequestMethod.GET)
	public String error403() {
		System.out.println("Inside logout method");
		return "403";
	}
	
	
	
}
