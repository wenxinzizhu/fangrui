<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/includes/common_tags.jsp" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->

<!-- BEGIN HEAD -->

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <resources:css name="libs/weui.css,weixin/example.css" />
    <resources:js name="libs/jquery-1.8.3.min.js" />
    <title>
        <sitemesh:write property='title'/>
    </title>
    <sitemesh:write property='head'/>
</head>

<body ontouchstart>
<div class="container">
    <sitemesh:write property='body'/>
</div>
</body>
</html>
