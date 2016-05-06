<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/includes/common_tags.jsp" %>
<html>
<head>
    <title>产品&服务</title>
</head>
<body>
    <layouts:weixin_tab cur_tab="product">
        <jsp:attribute name="board">
            <div class="weui_panel weui_panel_access">
                <div class="weui_panel_hd">产品</div>
                <div class="weui_panel_bd">
                    <c:forEach items="${products}" var="product">
                        <div class="weui_media_box weui_media_text">
                            <a href="/product/detail/${product.id}" class="weui_media_title">${product.name}</a>
                            <p class="weui_media_desc">占地面积：${product.area}平方米、系统造价：${product.cost}元、投资回收周期： ${product.recoveryPeriod}年</p>
                        </div>
                    </c:forEach>
                </div>
                <%--<a href="javascript:void(0);" class="weui_panel_ft">查看更多</a>--%>
            </div>
            <div class="weui_panel weui_panel_access">
                <div class="weui_panel_hd">服务</div>
                <div class="weui_panel_bd">
                    <div class="weui_media_box weui_media_text">
                        <h4 class="weui_media_title">并网</h4>
                        <p class="weui_media_desc">提供全套并网手续，并协助进行并网工作</p>
                        <p class="weui_media_desc">控制器、逆变器、整套系统5年质保</p>
                    </div>
                    <div class="weui_media_box weui_media_text">
                        <h4 class="weui_media_title">设备质保</h4>
                        <p class="weui_media_desc">光伏组件10年质保、20年光伏组件功率衰减不低于80%</p>
                        <p class="weui_media_desc">控制器、逆变器、整套系统5年质保</p>
                    </div>
                    <div class="weui_media_box weui_media_text">
                        <h4 class="weui_media_title">售后服务</h4>
                        <p class="weui_media_desc">全套设备提供20年售后服务</p>
                    </div>
                </div>

                    <%--<a href="javascript:void(0);" class="weui_panel_ft">查看更多</a>--%>
            </div>
        </jsp:attribute>
    </layouts:weixin_tab>
</body>
</html>
