<%@ tag import="com.hbfangrui.shop.web.util.ResourcesUtil" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="empty" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute type="java.lang.String" name="name" required="true" %>
<%
    for(String n : name.split(",")){
    out.append("<script src=\"")
            .append(ResourcesUtil.getJsHref(n))
            .append("\"></script>");
    }
%>