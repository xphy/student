package com.servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Student;
import com.model.dao.Studentdao;

@WebServlet("/students.do")
public class StudentSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Studentdao dao=new Studentdao(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid= request.getParameter("id");
		String method = request.getParameter("method");
		if(sid!=null&&!"".equals(sid)) {
		    int id=Integer.parseInt(sid);
		    if(method.equals("select")) {		    	
		    	Student student = this.dao.select(id);
		    	RequestDispatcher rd = request.getRequestDispatcher("student.jsp");
		    	request.setAttribute("student",student);
		    	rd.forward(request, response);
		    }else {
		    	boolean flag = this.dao.deleat(id);
		    	if(flag) {
		    		response.sendRedirect("students.do");
		    	}else {
		    		response.sendRedirect("student.jsp");
		    	}
		    }
		}else {
			List<Student> students = this.dao.select();
			RequestDispatcher rd= request.getRequestDispatcher("students.jsp");
			request.setAttribute("students", students);
			rd.forward(request, response);
		}
	}

}
