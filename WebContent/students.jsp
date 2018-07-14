<%@ page  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="ftm"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>学生信息表</title>
   <style>
      table{
        width:80%;
        border-collapse: collapse;
        border-top:1px solid #ccc;
        border-left:1px solid #ccc;
      }
      table td{
        text-align:center;
        border-right:1px solid #ccc;
        border-bottom:1px solid #ccc;
      }
   </style>
</head>
<body>
    <table >
       <tr>
         <td>编号</td><td>本人照片</td><td>姓名</td><td>性别</td><td>年龄</td><td>入学日期</td>
         <td>手机号</td><td>家庭住址</td><td>状态</td><td>修改</td><td>删除</td>
       </tr>
       <c:forEach items="${students}" var="s" varStatus="i" >
          <tr>
             <td>${i.index+1}</td>
             <td><img width="50"src="${s.picture }"></td>
             <td>${s.name}</td>
             <td>${s.sex}</td>
             <td>${s.age}</td>
             <td>${s.data}</td>
             <td>${s.phone}</td>
             <td>${s.addr}</td>
             <td>${s.stus}</td>
             <td><a href="students.do?id=${s.id}&&method='select'">编辑</a></td>
             <td><a href="students.do?id=${s.id}&&method='delete'">删除</a></td>             
          </tr>
       </c:forEach>
    </table>
    <a href="insert.jsp">添加</a>
</body>
</html>