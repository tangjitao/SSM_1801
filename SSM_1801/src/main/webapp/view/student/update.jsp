<%--
  Created by IntelliJ IDEA.
  User: 辛辣天森
  Date: 2019/5/9 0009
  Time: 下午 02:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加</title>
</head>
<body>
<form action="/student/update.do" method="post">
    <input type="hidden" name="id" value="${stu.id}">
    姓名:<input name="name"value="${stu.name}">
    性别:男<input type="radio" name="sex" value="1"${stu.sex eq '1'?"checked":""}> 女<input type="radio" name="sex" value="2"${stu.sex eq '2'?'checked':''}>
    年龄:<input name="age" value="${stu.age}">
    学号:<input name="position" value="${stu.position}">
    班级:<select name="cid">
    <option>--请选择--</option>
    <c:forEach items="${list}" var="i">
        <option  value="${i.cid}" ${i.cid == stu.cid ? "selected":""} >${i.cname}</option>
    </c:forEach>
</select>
    <input type="submit" value="提交修改">
</form>
</body>
</html>
