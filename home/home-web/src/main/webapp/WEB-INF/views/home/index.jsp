<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/includes/common_tags.jsp" %>
<html>
<head>
    <title>首页</title>
    <style>
        .context img{
            max-width:500px;
        //IE7、FF等其他非IE浏览器下最大宽度为500px;
            width:500px;
        //所有浏览器中图片的大小为500px;
            width:;
        //当图片大小大于500px，自动缩小为500px;
            overflow:hidden;
        }
    </style>
</head>
<body>
    <layouts:weixin_tab cur_tab="home">
        <jsp:attribute name="board">
             <article class="weui_article">
                 <h1>分布式光伏系统</h1>
                 <section>
                     <h2 class="title">1. 简介</h2>
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
                     <h2 class="title">2. 申请并网流程</h2>
                     <section>
                         <p>
                             提供全套并网手续，协助并网工作；待成功发电并售卖给国网，收取全部费用。
                         </p>
                         <p>

                             <img src='<resources:imageHref name="image/bw.jpg" />' style="max-width:300px;overflow: hidden">

                         </p>
                     </section>
                 </section>
                 <section>
                     <h2 class="title">3. 产品性能</h2>
                     <section>
                         <p>
                             光伏产品可根据用户需要进行量身定制。
                         </p>
                         <p>
                             <img src='<resources:imageHref name="image/products.jpg" />' style="max-width:300px;overflow: hidden">
                         </p>
                     </section>
                 </section>
                 <section>
                     <h2 class="title">4. 投资收益比</h2>
                     <section>
                         <p>
                             作为投资产品，和其他理财产品的投资收益比。
                         </p>
                         <p>
                             <img src='<resources:imageHref name="image/tzsy.jpg" />' style="max-width:300px;overflow: hidden">
                         </p>
                     </section>
                 </section>
             </article>
        </jsp:attribute>
    </layouts:weixin_tab>
</body>
</html>
