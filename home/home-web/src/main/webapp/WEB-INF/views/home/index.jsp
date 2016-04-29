<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/includes/common_tags.jsp" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
    <layouts:weixin_tab cur_tab="home">
        <jsp:attribute name="board">
            首页
        </jsp:attribute>
    </layouts:weixin_tab>
</body>
</html>
