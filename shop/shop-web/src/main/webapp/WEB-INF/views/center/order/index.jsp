<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/includes/common_tags.jsp" %>
<html>
<head>
    <title>个人中心</title>
</head>
<body>
    <layouts:weixin_tab cur_tab="order">
        <jsp:attribute name="board">
           <div class="weui_tab">
               <div class="weui_navbar">
                   <div class="weui_navbar_item weui_bar_item_on">
                       全部订单
                   </div>
                   <div class="weui_navbar_item">
                       待付款
                   </div>
                   <div class="weui_navbar_item">
                       待收货
                   </div>
               </div>
               <div class="weui_tab_bd">
                    暂无订单
               </div>
           </div>
        </jsp:attribute>
    </layouts:weixin_tab>
</body>
</html>
