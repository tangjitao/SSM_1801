<html>
<head>
    <title>修改</title>
</head>
<body>
<form action="/student/update.do" method="post">
    <input type="hidden" name="id" value="${stu.id}">
    姓名:<input name="name"value="${stu.name}">
    性别:男<input type="radio" name="sex" value="1"<#if stu.sex = "1">checked<#else> </#if>> 女<input type="radio" name="sex" value="2"<#if stu.sex = "2">checked<#else></#if>>
    年龄:<input name="age" value="${stu.age}">
    学号:<input name="position" value="${stu.position}">
    班级:<select name="cid">
    <option>--请选择--</option>
    <#list list as li>
        <option value="${li.cid}" <#if li.cid = stu.cid>selected</#if>>${li.cname}</option>
    </#list>
</select>
    <input type="submit" value="提交修改">
</form>
</body>
</html>
