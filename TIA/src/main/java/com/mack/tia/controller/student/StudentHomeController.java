package com.mack.tia.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/student")
public class StudentHomeController {

	@RequestMapping(value = "/home")
	public String accessHome() {
		return "student/studentHome";
	}
}
