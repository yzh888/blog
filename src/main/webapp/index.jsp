<%--
  Created by IntelliJ IDEA.
  User: Yzh
  Date: 2019/11/19
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>图片上传页面</title>
</head>
<body>
<%--<img src="/api/code" alt="">
<form action="/api/login" method="post">
    <label>
        <input type="text" name="code">
    </label>
    <input type="submit" value="登录">
</form>--%>
<form action="/api/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="filename">
    <input type="submit" value="上传">
</form>
<p>${msg}</p>
<%--<a href="api/image">获取图片</a>--%>
</body>
</html>
