<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 陈
  Date: 2017/9/17
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>多文件上传结果</title>
</head>
<body>
    <div style="margin: 0 auto;margin-top: 100px;">
        <%
            List<String> fileList = (List)request.getAttribute("files");
            for(String url : fileList){
                %>
                <a href="<%=url %>">
                    <img src="<%=url %>">
                </a>
                <%
            }
        %>
    </div>
</body>
</html>
