<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->
<style type="text/css">
    .question-number, .question-number-2 {
        width: 100px
    }
</style>
<div class="container" style="min-height:500px;">

    <div class="row">
        <div class="col-xs-2" id="left-menu">
            <div id="sidebar" class="nav-collapse " style="" tabindex="5000"></div>
        </div>
        <div class="col-xs-10" id="right-content">
            <div class="content" style="margin-bottom: 50px; min-height: 400px;">

                <div class="container" style="margin-top: 40px;">

                    <div class="row">
                        <div class="col-xs-6">
                            <div style="border-bottom: 1px solid #ddd;">
                                <h3 class="title"><i class="fa fa-cloud-upload"></i> 最近发布的考试</h3>

                            </div>

                            <div class="question-list">

                                <table class="table-striped table">
                                    <thead>

                                    <tr>
                                        <td>考试名称</td>
                                        <td>开始日期</td>
                                        <td>截止日期</td>
                                        <td></td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${examListToApply }" var="item">
                                        <tr>
                                            <td>${item.examName }</td>
                                            <td><span class="span-info question-number"><fmt:formatDate value="${item.effTime}"
                                                                                                        pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                            </td>
                                            <td><span class="span-success question-number-2"><fmt:formatDate value="${item.expTime}"
                                                                                                             pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${userId == 0 }">
                                                        <a class="btn btn-success btn-sm" href="user-login-page">申请</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button class="btn btn-success btn-sm join-practice-btn apply-exam-btn"
                                                                data-id="${item.examId }">申请
                                                        </button>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot></tfoot>
                                </table>


                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div style="border-bottom: 1px solid #ddd;">
                                <h3 class="title"><i class="fa fa-paper-plane-o"></i> 我的考试</h3>

                            </div>
                            <div class="question-list">

                                <table class="table-striped table">
                                    <thead>

                                    <tr>
                                        <td>考试名称</td>
                                        <td>开始日期</td>
                                        <td>截止日期</td>
                                        <td></td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${examListToStart }" var="item">
                                        <tr>
                                            <td>${item.examName }</td>
                                            <td><span class="span-info question-number"><fmt:formatDate value="${item.effTime}"
                                                                                                        pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                            </td>
                                            <td><span class="span-success question-number-2"><fmt:formatDate value="${item.expTime}"
                                                                                                             pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${item.approved == 1 }">
                                                        <a href="student/exam-start/${item.examId }"
                                                           class="btn btn-success btn-sm join-practice-btn">参加考试</a>
                                                    </c:when>
                                                    <c:when test="${item.approved == 3 }">
                                                        <a href="student/usercenter/student-answer-sheet/${item.examId }"
                                                           class="btn btn-success btn-sm join-practice-btn">查看详情</a>
                                                    </c:when>
                                                    <c:when test="${item.approved == 2 }">
                                                        <a class="btn btn-warning btn-sm">已交卷</a>
                                                    </c:when>
                                                    <c:when test="${item.approved == 0 }">
                                                        <a class="btn btn-warning btn-sm">待审核</a>
                                                    </c:when>
                                                </c:choose>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot></tfoot>
                                </table>

                            </div>
                            <div style="border-bottom: 1px solid #ddd;">
                                <h3 class="title"><i class="fa fa-paper-plane-o"></i> 模拟考试</h3>

                            </div>
                            <div class="question-list">

                                <table class="table-striped table">
                                    <thead>

                                    <tr>
                                        <td>考试名称</td>
                                        <td>开始日期</td>
                                        <td>截止日期</td>
                                        <td></td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${modelTestToStart }" var="item">
                                        <tr>
                                            <td>${item.examName }</td>
                                            <td><span class="span-info question-number"><fmt:formatDate value="${item.effTime}"
                                                                                                        pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                            </td>
                                            <td><span class="span-success question-number-2"><fmt:formatDate value="${item.expTime}"
                                                                                                             pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                            </td>
                                            <td>
                                                <a href="student/exam/model-test-start/${item.examId }"
                                                   class="btn btn-success btn-sm join-practice-btn">参加考试</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot></tfoot>
                                </table>

                            </div>
                        </div>

                    </div>


                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->
<script type="text/javascript">
    $(function () {
        $(".apply-exam-btn").click(function () {
            var examId = $(this).data("id");

            jQuery.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                type: "GET",
                url: "student/exam/send-apply/" + examId,
                success: function (message, tst, jqXHR) {
                    if (message.result == "success") {
                        window.location.reload();
                    } else {
                        alert(message.result);
                    }
                }
            });
        });
    });

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
        } else if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE6.0") {
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
