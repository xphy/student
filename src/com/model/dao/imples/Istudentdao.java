package com.model.dao.imples;

import java.util.List;

import com.model.Student;

public interface Istudentdao {
	 public void close();
     public boolean insert(Student bean);
     public boolean deleat(int id);
     public boolean update(Student bean);
     public List<Student> select();
     public Student select(int id);
     
}
