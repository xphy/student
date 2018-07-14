package com.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.model.Student;
import com.model.dao.Studentdao;

@WebServlet("/student.do")
@MultipartConfig(maxFileSize=1024*1024*4)
public class Stuone extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Studentdao dao= new Studentdao();   
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		boolean flag=false;
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sex =request.getParameter("sex");
		String age = request.getParameter("age");
		String data = request.getParameter("data");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		String stus = request.getParameter("stus");
		
		Student bean = new Student();
		bean.setName(name);
		bean.setSex(sex);
		bean.setAge(Integer.parseInt(age));
		bean.setData(data);
		bean.setPhone(Integer.parseInt(phone));
		bean.setAddr(addr);
		bean.setStus(Integer.parseInt(stus));
		//处理图片
		String pathbase=request.getServletContext().getRealPath("/images/");
		Part part = request.getPart("picture");
		if(part!=null&&part.getSize()>0) {
			String head = part.getHeader("content-disposition");
			if(head!=null&&!"".equals(head)) {				
				String[] hea = head.split(";");
				String str = hea[2].split("=")[1];
				String fm = str.replaceAll("\"", "");//原始文件的文件名字
				//时间戳算法
				String ext =fm.substring(fm.lastIndexOf("."));
				long t =System.currentTimeMillis();
				String filename=t+ext;
				InputStream is=part.getInputStream();
				OutputStream os= new FileOutputStream(pathbase+filename);
				byte[] b=new byte[512];
				while((is.read(b))!=-1) {
					os.write(b);
				}
				is.close();
				os.flush();
				os.close();
				bean.setPicture("images/"+filename);
		   }
		}else{
			String picture = request.getParameter("pic");
			bean.setPicture(picture);
		}
		if(id!=null&&!id.equals("")) {
			bean.setId(Integer.parseInt(id));
			flag = this.dao.update(bean);
		}else {
			flag = this.dao.insert(bean);
		}
		if(flag) {
			response.sendRedirect("students.do");
		}else {
			response.sendRedirect("student.jsp");			
		}
	}

}
