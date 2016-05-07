<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/includes/common_tags.jsp" %>
<html>
<head>
    <title>首页</title>
    <script type="text/javascript">
        $(function(){
            $('#container').on('focus', '#search_input', function () {
                var $weuiSearchBar = $('#search_bar');
                $weuiSearchBar.addClass('weui_search_focusing');
            }).on('blur', '#search_input', function () {
                var $weuiSearchBar = $('#search_bar');
                $weuiSearchBar.removeClass('weui_search_focusing');
                if ($(this).val()) {
                    $('#search_text').hide();
                } else {
                    $('#search_text').show();
                }
            }).on('input', '#search_input', function () {
                var $searchShow = $("#search_show");
                if ($(this).val()) {
                    $searchShow.show();
                } else {
                    $searchShow.hide();
                }
            }).on('touchend', '#search_cancel', function () {
                $("#search_show").hide();
                $('#search_input').val('');
            }).on('touchend', '#search_clear', function () {
                $("#search_show").hide();
                $('#search_input').val('');
            });
        })
    </script>
</head>
<body>
    <layouts:weixin_tab cur_tab="search">
        <jsp:attribute name="board">
           <div class="weui_search_bar" id="search_bar">
               <form class="weui_search_outer">
                   <div class="weui_search_inner">
                       <i class="weui_icon_search"></i>
                       <input type="search" class="weui_search_input" id="search_input" placeholder="搜索" required/>
                       <a href="javascript:" class="weui_icon_clear" id="search_clear"></a>
                   </div>
                   <label for="search_input" class="weui_search_text" id="search_text">
                       <i class="weui_icon_search"></i>
                       <span>搜索</span>
                   </label>
               </form>
               <a href="javascript:" class="weui_search_cancel" id="search_cancel">取消</a>
           </div>
            <div class="weui_cells weui_cells_access search_show" id="search_show">
                <div class="weui_cell">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>实时搜索文本</p>
                    </div>
                </div>
                <div class="weui_cell">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>实时搜索文本</p>
                    </div>
                </div>
                <div class="weui_cell">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>实时搜索文本</p>
                    </div>
                </div>
                <div class="weui_cell">
                    <div class="weui_cell_bd weui_cell_primary">
                        <p>实时搜索文本</p>
                    </div>
                </div>
            </div>
        </jsp:attribute>
    </layouts:weixin_tab>
</body>
</html>
