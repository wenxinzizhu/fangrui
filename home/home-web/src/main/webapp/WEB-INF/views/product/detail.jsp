<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/includes/common_tags.jsp" %>
<html>
<head>
    <title>${product.name}</title>
</head>
<body>
    <layouts:weixin_tab cur_tab="case">
        <jsp:attribute name="board">
            <article class="weui_article">
                <h1>${product.name}</h1>
                <section>
                    <%--<h2 class="title">简介</h2>--%>
                    <section>
                        <h3>占地面积</h3>
                        <p>${product.area}平方米</p>
                    </section>
                    <section>
                        <h3>系统造价</h3>
                        <p>${product.cost}元</p>
                    </section>
                    <section>
                        <h3>投资回收周期</h3>
                        <p>${product.recoveryPeriod}年</p>
                    </section>
                </section>
            </article>
        </jsp:attribute>
    </layouts:weixin_tab>
</body>
</html>
