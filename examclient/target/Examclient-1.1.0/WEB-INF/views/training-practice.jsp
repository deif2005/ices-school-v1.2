<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp"%>
<%@include file="common/banner.jsp" %>
<!-- Slider starts -->


<div class="content" style="margin-bottom: 50px;min-height: 400px;">

    <div class="container">
        <div>

            <!-- 	<h3>开始测验</h3>
                <p>
                    选择您想要参加的测验，考核下自己吧
                </p> -->
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#">公务员申论</a></li>
                <li role="presentation"><a href="#">医药行业考试</a></li>
            </ul>

        </div>

    </div>

</div>
<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->
<script type="text/javascript">
    $(function () {
        bindQuestionKnowledage();
        var result = checkBrowser();
        if (!result) {
            alert("请至少更新浏览器版本至IE8或以上版本");
        }
    });

    function checkBrowser() {
        var browser = navigator.appName;
        var b_version = navigator.appVersion;
        var version = b_version.split(";");
        var trim_Version = version[1].replace(/[ ]/g, "");
        if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE7.0") {
            return false;
        } else if (browser == "Microsoft Internet Explorer"
                && trim_Version == "MSIE6.0") {
            return false;
        } else
            return true;
    }

    function bindQuestionKnowledage() {
        $(".knowledge-title").click(function () {
            var ul = $(this).parent().find(".question-list-knowledge");

            if (ul.is(":visible")) {
                $(this).find(".fa-chevron-down").hide();
                $(this).find(".fa-chevron-up").show();

                $(".question-list-knowledge").slideUp();

            } else {
                $(".fa-chevron-down").hide();
                $(".fa-chevron-up").show();

                $(this).find(".fa-chevron-up").hide();
                $(this).find(".fa-chevron-down").show();

                $(".question-list-knowledge").slideUp();
                ul.slideDown();

            }


        });
    }
</script>
