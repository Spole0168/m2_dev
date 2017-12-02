package com.shang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shang.biz.IStudentBiz;
import com.shang.dal.model.Student;

@Controller
@RequestMapping("/stu")
public class StudentController {
	@Autowired
	private IStudentBiz studentBiz;
	
	@RequestMapping(value = "/listStudent")
	public ModelAndView listStudent(){
		List<Student> listStudents = null;
		listStudents = studentBiz.listStudents(null);
		return new ModelAndView("listStudent","list",listStudents);
	}
	@RequestMapping(value = "/listMenu")
	public ModelAndView listMenu(){
		return new ModelAndView("listMenu");
	}
	
	@RequestMapping(value = "/jsonTest",method = RequestMethod.POST)
	@ResponseBody
	public String jsonTest(@RequestBody String req){
		System.out.println(req);
		return "123";
	}
}
