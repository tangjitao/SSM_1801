<html>
<head>
    <title>学生界面</title>
    <script type="text/javascript">
        function f(a) {
            var f = document.getElementById("fid");
            if(a == 1){
                f.action = "/student/queryAll.do";
            }else if (a == 2){
                f.action = "/student/xiazai.do";
            }
        }
    </script>
</head>
<body>
<form id="fid" action="/student/queryAll.do" method="post">
    姓名：<input name="name">
    性别：男<input type="radio" name="sex" value="1">女<input type="radio" name="sex"value="2">
    班级：<select name="cid">
    <option value="0">--请选择--</option>
    <#list list1 as li>
        <option value="${li.cid}">${li.cname}</option>
    </#list>
</select>
    <button id="bid" onclick="f(1)">提交</button>
    <button id="bid1" onclick="f(2)">导出</button>
</form>
<form action="/student/uplaod.do" method="post" enctype="multipart/form-data">
    <input type="file" name="filename"/>
    <input type="submit" value="上传"/>
</form>
<table border="1">
    <tr>
        <td>学生编号</td>
        <td>学生姓名</td>
        <td>性别</td>
        <td>年龄</td>
        <td>学号</td>
        <td>班级</td>
        <td>操作<a href="/student/toAdd.do">添加</a></td>
    </tr>
        <#list list as i>
         <tr>
             <td>${i.id}</td>
             <td>${i.name}</td>
             <td>
                 <#if i.sex = "1">
                     男
                     <#else>
                     女
                 </#if>
             </td>
             <td>${i.age}</td>
             <td>${i.position}</td>
             <td>${i.classes.cname}</td>
             <td><a href="/student/toupdate/${i.id}.do">修改</a><a href="/student/delete/${i.id}.do">删除</a></td>
         </tr>
        </#list>
</table>
<a href="/student/sm.do">上一页</a>
<#--<input type="button" value="${num}">-->
<a href="/student/xm.do">下一页</a>
</body>
</html>
