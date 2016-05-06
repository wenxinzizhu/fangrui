<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/includes/common_tags.jsp" %>
<html>
<head>
    <title>公司简介</title>
</head>
<body>
    <layouts:weixin_tab cur_tab="corp">
        <jsp:attribute name="board">
            <article class="weui_article">
                <h1>公司简介</h1>
                <section>
                    <h2 class="title">简介</h2>
                    <section>
                        <h3>1.1 节标题</h3>
                        <p></p>
                        <p>
                            <img src="./images/pic_article.png" alt="">
                            <img src="./images/pic_article.png" alt="">
                        </p>
                    </section>
                    <section>
                        <h3>1.2 节标题</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                            cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                            proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                    </section>
                </section>
            </article>
        </jsp:attribute>
    </layouts:weixin_tab>
</body>
</html>
