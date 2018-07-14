package com.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



import com.db.DButil;
import com.model.Student;
import com.model.dao.imples.Istudentdao;

public class Studentdao implements Istudentdao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    
 
	public void close() {
			try {
				if(this.rs!=null)ps.close();
				if(this.ps!=null)ps.close();
				if(this.conn!=null&&!this.conn.isClosed())ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public boolean insert(Student bean) {
		String sql="insert into student(name,sex,age,data,phone,addr,stus,picture) values(?,?,?,?,?,?,?,?)";
		this.conn= DButil.getConnection();
		String stus=bean.getStus();
		int stu=0;
		if(stus=="上学") stu=1;
		if(stus=="毕业") stu=-1;
		try {
			this.ps=this.conn.prepareStatement(sql);
			this.ps.setString(1, bean.getName());
			this.ps.setString(2, bean.getSex());
			this.ps.setInt(3, bean.getAge());
			this.ps.setString(4, bean.getData());
			this.ps.setInt(5, bean.getPhone());
			this.ps.setString(6,bean.getAddr());			
			this.ps.setInt(7, stu);
			this.ps.setString(8,bean.getPicture());
			//this.ps.setInt(9, bean.getId());
			int e = this.ps.executeUpdate();
			if(e>0) return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return false;
	}

	@Override
	public boolean deleat(int id) {
		String sql="update student set stus='0' where id=?";
		this.conn= DButil.getConnection();
		try {
			this.ps=this.conn.prepareStatement(sql);
			this.ps.setInt(1, id);
			int e = this.ps.executeUpdate();
			if(e>0) return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return false;
	}

	@Override
	public boolean update(Student bean) {
		String sql="update student set name=?,sex=?,age=?,data=?,phone=?,addr=?,stus=?, picture=?  where id=?";
		this.conn= DButil.getConnection();
		String stus=bean.getStus();
		int stu=0;
		if(stus=="上学") stu=1;
		try {
			this.ps=this.conn.prepareStatement(sql);
			this.ps.setString(1, bean.getName());
			this.ps.setString(2, bean.getSex());
			this.ps.setInt(3, bean.getAge());
			this.ps.setString(4, bean.getData());
			this.ps.setInt(5, bean.getPhone());
			this.ps.setString(6,bean.getAddr());			
			this.ps.setInt(7, stu);
			this.ps.setString(8,bean.getPicture());
			this.ps.setInt(9, bean.getId());
			int e = this.ps.executeUpdate();
			if(e>0) return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return false;
	}
	@Override
	public List<Student> select() {
		List<Student>list=new ArrayList<Student>();
		String sql="select * from student where stus='1'";
		this.conn=DButil.getConnection();
		try {
			this.ps=this.conn.prepareStatement(sql);
			this.rs=this.ps.executeQuery();
			while(rs.next()) {
				Student bean=new Student();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setAge(rs.getInt("age"));
				bean.setSex(rs.getString("sex"));
				bean.setData(rs.getString("data"));
				bean.setPhone(rs.getInt("phone"));
				bean.setAddr(rs.getString("addr"));
				bean.setStus(rs.getInt("stus"));
				bean.setPicture(rs.getString("picture"));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return list;
	}

	@Override
	public Student select(int id) {
		String sql="select * from student where id=?";
		this.conn=DButil.getConnection();
		Student bean=new Student();
		try {
			this.ps=this.conn.prepareStatement(sql);
			this.ps.setInt(1,id);
			this.rs=this.ps.executeQuery();
			while(rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setAge(rs.getInt("age"));
				bean.setSex(rs.getString("sex"));
				bean.setPhone(rs.getInt("phone"));
				bean.setAddr(rs.getString("addr"));
				bean.setStus(rs.getInt("stus"));
				bean.setPicture(rs.getString("picture"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return bean;
	}

}
