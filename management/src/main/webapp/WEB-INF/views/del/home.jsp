<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="../common/header.jsp" %>
<%@include file="../common/banner.jsp" %>

<!-- Slider starts -->
<div class="full-slider">
    <!-- Slider (Flex Slider) -->

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="flexslider">
                    <div class="flex-caption">
                        <!-- Left column -->
                        <div class="col-l">
                            <p style="text-indent:2em;">
                                本考试系统可以快捷方便的创建试题和题库，发布试卷，组织考试，系统自动批改。高度的可配置性和灵活性使得它可以被应用于很多领域。</p>
                        </div>
                        <!-- Right column -->
                        <div class="col-r">

                            <!-- Use the class "flex-back" to add background inside flex slider -->

                            <!-- <img alt="" src="resources/images/ad.png"> -->
                            <p></p>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="content" style="margin-bottom: 100px;">

    <div class="container">
        <div>
            <h3>开始测验</h3>
            <p>
                选择您想要参加的测验，考核下自己吧
            </p>
            <div class="row">
                <div class="select-test col-xs-6">
                    <div style="height: 100px;">
                        <div class="select-test-icon">
                            <i class="fa fa-cloud-upload"></i>
                        </div>
                        <div class="select-test-content">
                            <h3 class="title">强化练习</h3>
                            <p>
                                自主选择具体考点，各个击破
                            </p>
                            <a class="btn btn-primary" data-toggle="modal" data-target=".levelup-practice-modal"><i
                                    class="fa fa-arrow-right"></i>参加练习</a>
                            <div class="modal fade levelup-practice-modal" tabindex="-1" role="dialog"
                                 aria-labelledby="myLargeModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-hidden="true">&times;</button>
                                            <h6 class="modal-title" id="myModalLabel">选择想要练习的知识分类</h6>
                                        </div>
                                        <div class="modal-body">
                                            <ul>
                                                <c:forEach items="${classifyMap}" var="item">
                                                    <li>
                                                        <div class="knowledge-title">
                                                            <i class="fa fa-chevron-up"> </i><i
                                                                class="fa fa-chevron-down" style="display:none;"> </i>
                                                            <span class="knowledge-title-name">${item.key}</span>
                                                        </div>

                                                        <ul class="question-list-knowledge" style="display:none;">
                                                            <c:forEach items="${item.value }" var="tp">
                                                                <li>${tp.questionTypeName } [共<span
                                                                        class="question-number">${tp.amount } </span>题]
                                                                    [已做<span
                                                                            class="question-number-2">${tp.rightTimes + tp.wrongTimes } </span>
                                                                    题]
                                                                    <a href="student/practice-improve/${tp.questionPointId }/${tp.questionTypeId }"
                                                                       class="btn btn-success btn-sm join-practice-btn">参加练习</a>
                                                                </li>
                                                            </c:forEach>
                                                        </ul>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--//content-->

                    </div>
                </div>
                <div class="select-test col-xs-6">
                    <div style="height: 100px;">
                        <div class="select-test-icon">
                            <i class="fa fa-eraser"></i>
                        </div>
                        <div class="select-test-content">
                            <h3 class="title">错题练习</h3>
                            <p>
                                收录做过的所有错题
                            </p>
                            <!-- <a class="btn btn-primary" href="student/practice-incorrect"><i class="fa fa-arrow-right"></i>参加练习</a> -->
                            <a class="btn btn-primary" data-toggle="modal" data-target=".incorrect-modal"><i
                                    class="fa fa-arrow-right"></i>参加练习</a>
                            <div class="modal fade incorrect-modal" tabindex="-1" role="dialog"
                                 aria-labelledby="myLargeModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-hidden="true">&times;</button>
                                            <h6 class="modal-title" id="myModalLabel">错题练习</h6>
                                        </div>
                                        <div class="modal-body">
                                            <ul>
                                                <c:forEach items="${wrongKnowledgeMap }" var="item">
                                                    <li><span class="point-name"> ${item.key }</span> [共<span
                                                            class="question-number-3"><c:forEach items="${item.value }"
                                                                                                 var="v">${v.value }</c:forEach> </span>题]
                                                        <a href="student/practice-incorrect/<c:forEach items="${item.value }" var="k">${k.key }</c:forEach>"
                                                           class="btn btn-success btn-sm join-practice-btn">参加练习</a>
                                                    </li>
                                                </c:forEach>

                                            </ul>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口
                                            </button>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--//content-->

                    </div>
                </div>
                <div class="select-test col-xs-6">
                    <div style="height: 100px;">
                        <div class="select-test-icon">
                            <i class="fa fa-superscript"></i>
                        </div>
                        <div class="select-test-content">
                            <h3 class="title">随机练习</h3>
                            <p>

                                根据你对考点的掌握程度智能出题，提升综合能力
                            </p>
                            <a class="btn btn-primary" href="student/practice-test"><i class="fa fa-arrow-right"></i>参加练习</a>
                        </div>
                        <!--//content-->

                    </div>
                </div>
                <div class="select-test col-xs-6">
                    <div style="height: 100px;">
                        <div class="select-test-icon">
                            <i class="fa fa-file-text"></i>
                        </div>
                        <div class="select-test-content">
                            <h3 class="title">模拟考试</h3>
                            <p>
                                根据最新大纲的考查要求为你自动生成的模拟卷
                            </p>
                            <a class="btn btn-primary" data-toggle="modal" data-target=".practice-exampaper-modal"><i
                                    class="fa fa-arrow-right"></i>参加测验</a>
                            <div class="modal fade practice-exampaper-modal" tabindex="-1" role="dialog"
                                 aria-labelledby="myLargeModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-hidden="true">&times;</button>
                                            <h6 class="modal-title" id="myModalLabel">选择试卷，参加考试</h6>
                                        </div>
                                        <div class="modal-body">
                                            <ul>
                                                <c:forEach items="${practicepaper}" var="item">
                                                    <li>
                                                        <a href="student/examing/${item.id}">${item.name}</a>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口
                                            </button>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--//content-->
                    </div>
                </div>
                <div class="select-test col-xs-6">
                    <div style="height: 100px;">
                        <div class="select-test-icon">
                            <i class="fa fa-book"></i>
                        </div>
                        <div class="select-test-content">
                            <h3 class="title">随机组卷</h3>
                            <p>
                                随机组成试卷参加考试
                            </p>
                            <a class="btn btn-primary" data-toggle="modal" data-target=".history-exampaper-modal"
                               disabled="disabled"><i class="fa fa-arrow-right"></i>即将开放</a>
                            <div class="modal fade history-exampaper-modal" tabindex="-1" role="dialog"
                                 aria-labelledby="myLargeModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-hidden="true">&times;</button>
                                            <h6 class="modal-title" id="myModalLabel">选择试卷，参加考试</h6>
                                        </div>
                                        <div class="modal-body">
                                            <ul>
                                                <c:forEach items="${historypaper}" var="item">
                                                    <li>
                                                        <a href="student/examing/${item.id}">${item.name}</a>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口
                                            </button>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--//content-->

                    </div>
                </div>
                <div class="select-test col-xs-6">
                    <div style="height: 100px;">
                        <div class="select-test-icon">
                            <i class="fa fa-rocket"></i>
                        </div>
                        <div class="select-test-content">
                            <h3 class="title">专家试卷</h3>
                            <p>
                                专家组卷，为你提供更权威的考题动向
                            </p>
                            <a class="btn btn-primary" data-toggle="modal" data-target=".expert-exampaper-modal"
                               disabled="disabled"><i class="fa fa-arrow-right"></i>即将开放</a>
                            <div class="modal fade expert-exampaper-modal" tabindex="-1" role="dialog"
                                 aria-labelledby="myLargeModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-hidden="true">&times;</button>
                                            <h6 class="modal-title" id="myModalLabel">选择试卷，参加考试</h6>
                                        </div>
                                        <div class="modal-body">
                                            <ul>
                                                <c:forEach items="${expertpaper}" var="item">
                                                    <li>
                                                        <a href="student/examing/${item.id}">${item.name}</a>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口
                                            </button>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--//content-->

                    </div>
                </div>

            </div>

        </div>

    </div>

</div>
<%@include file="../common/footer.jsp" %>

<!-- Slider Ends -->

<!-- Javascript files -->
<script>
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
