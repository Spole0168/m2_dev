package com.shang.biz;

import java.util.List;

import com.shang.dal.model.Student;

public interface IStudentBiz {
	
	public void insertSelective(Student stu);
	
	public Student getStudentById(String id);
	
	public List<Student> listStudents(Student stu);
}
