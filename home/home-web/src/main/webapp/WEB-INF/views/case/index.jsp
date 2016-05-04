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
                    <a href="/case/detail/1" class="weui_media_box weui_media_appmsg">
                        <div class="weui_media_hd">
                            <img class="weui_media_appmsg_thumb" src="" alt="">
                        </div>
                        <div class="weui_media_bd">
                            <h4 class="weui_media_title">长果庄村自建5KW</h4>
                            <p class="weui_media_desc">高阳长果庄村自家屋顶5KW项目</p>
                        </div>
                    </a>
                    <a href="/case/detail/2" class="weui_media_box weui_media_appmsg">
                        <div class="weui_media_hd">
                            <img class="weui_media_appmsg_thumb" src="" alt="">
                        </div>
                        <div class="weui_media_bd">
                            <h4 class="weui_media_title">布里村自建3KW</h4>
                            <p class="weui_media_desc">高阳布里村自家屋顶3KW项目</p>
                        </div>
                    </a>
                </div>
                <%--<a class="weui_panel_ft" href="javascript:void(0);">查看更多</a>--%>
            </div>
        </jsp:attribute>
    </layouts:weixin_tab>
</body>
</html>
