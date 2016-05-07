<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/includes/common_tags.jsp" %>
<html>
<head>
    <title>个人中心</title>
</head>
<body>
    <layouts:weixin_tab cur_tab="center">
        <jsp:attribute name="board">
           <div class="hd">
               <h1 class="page_title">WeUI</h1>
               <p class="page_desc">李涛</p>
           </div>
<div class="bd">
    <div class="weui_grids">
        <a href="/center/order" class="weui_grid">
            <div class="weui_grid_icon">
                <i class="icon icon_button"></i>
            </div>
            <p class="weui_grid_label">
                我的订单
            </p>
        </a>
        <a href="/center/favorite" class="weui_grid">
            <div class="weui_grid_icon">
                <i class="icon icon_cell"></i>
            </div>
            <p class="weui_grid_label">
                我的收藏
            </p>
        </a>
        <%--<a href="#/toast" class="weui_grid">--%>
            <%--<div class="weui_grid_icon">--%>
                <%--<i class="icon icon_toast"></i>--%>
            <%--</div>--%>
            <%--<p class="weui_grid_label">--%>
                <%--优惠券--%>
            <%--</p>--%>
        <%--</a>--%>
        <a href="/center/address" class="weui_grid">
            <div class="weui_grid_icon">
                <i class="icon icon_dialog"></i>
            </div>
            <p class="weui_grid_label">
                收货地址
            </p>
        </a>
        <a href="/center/setting" class="weui_grid">
            <div class="weui_grid_icon">
                <i class="icon icon_progress"></i>
            </div>
            <p class="weui_grid_label">
                设置
            </p>
        </a>

    </div>
</div>
        </jsp:attribute>
    </layouts:weixin_tab>
</body>
</html>
