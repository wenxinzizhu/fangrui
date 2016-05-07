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
                <p class="weui_tabbar_label">精品推荐</p>
            </a>
            <a href="/search/index" class="weui_tabbar_item <c:if test="${cur_tab == 'search'}">weui_bar_item_on</c:if>">
                <div class="weui_tabbar_icon">
                    <resources:image src="image/icon/icon_nav_article.png" />
                </div>
                <p class="weui_tabbar_label">搜一搜</p>
            </a>
            <%--<a href="/tuan/index" class="weui_tabbar_item <c:if test="${cur_tab == 'tuan'}">weui_bar_item_on</c:if>">--%>
                <%--<div class="weui_tabbar_icon">--%>
                    <%--<resources:image src="image/icon/icon_nav_cell.png" />--%>
                <%--</div>--%>
                <%--<p class="weui_tabbar_label">乐团</p>--%>
            <%--</a>--%>
            <a href="/center/order/index" class="weui_tabbar_item <c:if test="${cur_tab == 'order'}">weui_bar_item_on</c:if>">
                <div class="weui_tabbar_icon">
                    <resources:image src="image/icon/icon_nav_cell.png" />
                </div>
                <p class="weui_tabbar_label">我的订单</p>
            </a>
            <a href="/center/index" class="weui_tabbar_item <c:if test="${cur_tab == 'center'}">weui_bar_item_on</c:if>">
                <div class="weui_tabbar_icon">
                    <resources:image src="image/icon/icon_nav_msg.png" />
                </div>
                <p class="weui_tabbar_label">个人中心</p>
            </a>
        </div>
    </div>
</div>
