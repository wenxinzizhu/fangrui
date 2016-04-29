<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/includes/common_tags.jsp" %>
<html>
<head>
    <title>成功案例</title>
</head>
<body>
    <layouts:weixin_tab cur_tab="case">
        <jsp:attribute name="board">
            <div class="weui_panel weui_panel_access">
                <div class="weui_panel_hd">成功案例</div>
                <div class="weui_panel_bd">
                    <a href="javascript:void(0);" class="weui_media_box weui_media_appmsg">
                        <div class="weui_media_hd">
                            <img class="weui_media_appmsg_thumb" src="" alt="">
                        </div>
                        <div class="weui_media_bd">
                            <h4 class="weui_media_title">标题一</h4>
                            <p class="weui_media_desc">由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。</p>
                        </div>
                    </a>
                    <a href="javascript:void(0);" class="weui_media_box weui_media_appmsg">
                        <div class="weui_media_hd">
                            <img class="weui_media_appmsg_thumb" src="" alt="">
                        </div>
                        <div class="weui_media_bd">
                            <h4 class="weui_media_title">标题二</h4>
                            <p class="weui_media_desc">由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。</p>
                        </div>
                    </a>
                </div>
                <%--<a class="weui_panel_ft" href="javascript:void(0);">查看更多</a>--%>
            </div>
        </jsp:attribute>
    </layouts:weixin_tab>
</body>
</html>
