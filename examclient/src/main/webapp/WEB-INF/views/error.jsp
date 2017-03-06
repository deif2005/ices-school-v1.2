<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>
<!-- Slider starts -->

<div class="full-slider">
    <!-- Slider (Flex Slider) -->

    <div class="container">
        ${errorMsg }
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
