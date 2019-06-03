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
<form action="/student/add.do" method="post">
    姓名:<input name="name"><br>
    性别:男<input type="radio" name="sex" value="1">女<input type="radio" name="sex" value="2"><br>
    年龄:<input name="age"><br>
    学号:<input name="position"><br>
    班级:<select name="cid"><br>
    <option>--请选择--</option>
    <c:forEach items="${list}" var="i">
        <option  value="${i.cid}">${i.cname}</option>
    </c:forEach>
</select><br>
    <input type="submit">
</form>
</body>
</html>
