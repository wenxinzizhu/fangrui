<%@ tag import="com.hbfangrui.home.web.util.ResourcesUtil" %>
<%@ tag import="org.apache.commons.lang3.StringUtils" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="empty" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute type="java.lang.String" name="src" required="true" %>
<%@ attribute name="id" type="java.lang.String" required="false" %>
<%@ attribute name="alt" type="java.lang.String" required="false" %>
<%@ attribute name="width" type="java.lang.String" required="false" %>
<%@ attribute name="height" type="java.lang.String" required="false" %>
<%
    out.append("<img src=\"")
            .append(ResourcesUtil.getImageHref(src)).append("\" ");
    if (StringUtils.isNotBlank(id)) {
        out.append(" id=\"").append(id).append("\" ");
    }
    if (StringUtils.isNotBlank(alt)) {
        out.append(" alt=\"").append(alt).append("\" ");
    }
    if (StringUtils.isNotEmpty(width)) {
        out.append(" with=\"").append(width).append("\"");
    }
    if (StringUtils.isNotBlank(height)) {
        out.append(" height=\"").append(height).append("\"");
    }
    out.append(" />");
%>