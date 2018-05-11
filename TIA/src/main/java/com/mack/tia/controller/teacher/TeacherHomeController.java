package com.mack.tia.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherHomeController {

	@RequestMapping(value = "/home")
	public String accessHome() {
		return "teacher/teacherHome";
	}
}
