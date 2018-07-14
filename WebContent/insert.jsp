<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息表</title>
<style>
  
</style>
</head>
<body>
   
   <form action="student.do"enctype="multipart/form-data" method="post" >
         <input name="id" value="${student.id}">
         <div>姓名：
            <input name="name" value="${student.name}" placeholder="请输入学生的姓名"></div>
	     <div> 性别：	         
	         <input type="radio" name="sex" value="男"checked>男
		     <input type="radio" name="sex" value="女">女      
	     </div>
         <div>年龄：
           <input name="age" value="${student.age}"placeholder="请输入学生的年龄">
         </div>
         <div>生日：
           <input type="date" value="${student.data}"name="data">
         </div>
         <div>手机号： 
            <input type="number" value="${student.phone}"name="phone" >
         </div>
         <div>住址： 
            <input name="addr" value="${student.addr}"placeholder="请输入学生的住址">
         </div>
	     <div>状态：
	          <input type="radio"name="stus" value="1" checked>上学
		      <input type="radio"name="stus" value="0">休学
	     </div>
         <c:if test="${student.picture!=null}">
           <div>本人照片</div> 
           <img width="50" src="${student.picture }"><br>
         </c:if>
         <c:if test="${student.picture==null}">
            <div>本人照片：<input type="file"name="picture"></div> 
         </c:if>
     <button>提交</button>        
   </form>
</body>
</html>