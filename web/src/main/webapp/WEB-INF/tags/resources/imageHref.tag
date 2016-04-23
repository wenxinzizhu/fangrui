<%@ tag import="com.hbfangrui.home.web.util.ResourcesUtil" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute type="java.lang.String" name="name" required="true" %>
<%
    out.append(ResourcesUtil.getImageHref(name));
%>