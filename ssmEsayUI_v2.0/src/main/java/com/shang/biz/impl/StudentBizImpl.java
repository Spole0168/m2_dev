package com.shang.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shang.biz.IStudentBiz;
import com.shang.dal.dao.StudentMapper;
import com.shang.dal.model.Student;

@Service("studentBiz")  
public class StudentBizImpl implements IStudentBiz{
	
	@Autowired
	private StudentMapper studentMapper;
	
	public Student getStudentById(String id) {
		
		return null;
	}

	public List<Student> listStudents(Student stu) {
		
		return studentMapper.selectByExample(null);
	}

	public void insertSelective(Student stu) {
		studentMapper.insertSelective(stu);
	}

}
