<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/includes/common_tags.jsp" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
    <layouts:weixin_tab cur_tab="home">
        <jsp:attribute name="board">
             <article class="weui_article">
                 <h1></h1>
                 <section>
                     <h2 class="title">分布式光伏系统简介</h2>
                     <section>
                         <p>
                             分布式光伏并网系统是消费者利用自家屋顶、限制场地，安装光伏组件，将太阳能直接转化为电能的发电系统。所发电量，可自用，可上网。享受国家和省、市财政补贴。
                             <span style="color: red">
                                 (国家补贴每度0.42元，20年。省财政补贴0.2元，3年)
                             </span>
                         </p>
                     <%--<p>--%>
                     <%--<img src="./images/pic_article.png" alt="">--%>
                     <%--<img src="./images/pic_article.png" alt="">--%>
                     <%--</p>--%>
                     </section>
                 </section>
                 <section>
                     <h2 class="title">申请并网流程</h2>
                     <section>
                         <p>
                             提供全套并网手续，协助并网工作；待成功发电并售卖给国网，收取全部费用。
                         </p>
                         <p>
                             <resources:image src="image/bw.jpg" width="690px" height="700px" />
                         </p>
                     </section>
                 </section>
                 <section>
                     <h2 class="title">产品性能</h2>
                     <section>
                         <p>
                             光伏产品可根据用户需要进行量身定制。
                         </p>
                         <p>
                             <resources:image src="image/products.jpg" width="690px" height="700px" />
                         </p>
                     </section>
                 </section>
                 <section>
                     <h2 class="title">投资收益比</h2>
                     <section>
                         <p>
                             作为投资产品，和其他理财产品的投资收益比。
                         </p>
                         <p>
                             <resources:image src="image/tzsy.jpg" width="690px" height="700px" />
                         </p>
                     </section>
                 </section>
             </article>
        </jsp:attribute>
    </layouts:weixin_tab>
</body>
</html>
