package com.ssj.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 首页展示contrroller
 */
@Controller
public class PortalController {

	@RequestMapping("/index")
	public String showIndex() {
		return "index";
	}
}
