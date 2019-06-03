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
     <#list list as li>
        <option value="${li.cid}">${li.cname}</option>
     </#list>
</select><br>
    <input type="submit">
</form>
</body>
</html>
