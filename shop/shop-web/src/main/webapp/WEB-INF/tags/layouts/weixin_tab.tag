<%@ include file="/WEB-INF/includes/common_tags.jsp"%>
<%@ tag pageEncoding="UTF-8" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="board" fragment="true" %>
<%@ attribute name="cur_tab" type="java.lang.String" required="true" %>
<%--<jsp:invoke fragment="hasPermission"/>--%>
<%--<%} else if (noPermission != null) {%>--%>
<%--<jsp:invoke fragment="noPermission"/>--%>
<%--<%}%>--%>
<div class="tabbar">
    <div class="weui_tab">
        <div class="weui_tab_bd">
            <jsp:invoke fragment="board" />
        </div>
        <div class="weui_tabbar">
            <a href="/home/index" class="weui_tabbar_item <c:if test="${cur_tab == 'home'}">weui_bar_item_on</c:if>">
                <div class="weui_tabbar_icon">
                    <resources:image src="image/icon/icon_nav_button.png" />
                </div>
                <p class="weui_tabbar_label">首页</p>
            </a>
            <a href="/corp/index" class="weui_tabbar_item <c:if test="${cur_tab == 'corp'}">weui_bar_item_on</c:if>">
                <div class="weui_tabbar_icon">
                    <resources:image src="image/icon/icon_nav_article.png" />
                </div>
                <p class="weui_tabbar_label">公司简介</p>
            </a>
            <a href="/product/index" class="weui_tabbar_item <c:if test="${cur_tab == 'product'}">weui_bar_item_on</c:if>">
                <div class="weui_tabbar_icon">
                    <resources:image src="image/icon/icon_nav_cell.png" />
                </div>
                <p class="weui_tabbar_label">产品&服务</p>
            </a>
            <a href="/case/index" class="weui_tabbar_item <c:if test="${cur_tab == 'case'}">weui_bar_item_on</c:if>">
                <div class="weui_tabbar_icon">
                    <resources:image src="image/icon/icon_nav_msg.png" />
                </div>
                <p class="weui_tabbar_label">成功案例</p>
            </a>
        </div>
    </div>
</div>
