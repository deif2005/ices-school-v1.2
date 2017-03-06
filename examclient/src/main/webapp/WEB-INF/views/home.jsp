<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<div class="container" style="min-height:500px;">

    <div class="row">
        <div class="col-xs-2" id="left-menu">
            <div id="sidebar" class="nav-collapse " style="" tabindex="5000"></div>
        </div>
        <div class="col-xs-10" id="right-content">
            <div class="content" style="padding:30px 0 0 0;">
                <div class="container">
                    <div class="row">
                        <a class="select-test col-xs-4 home-link-anchor" href="student/practice-list">
                            <div class="select-test-icon">
                                <i class="fa fa-book"></i>
                            </div>
                            <h3> 试题练习 </h3>

                            <p>
                                您可以免费获取对应专业的培训视频或者文档资料，<br>通过在线练习可以考察您的知识掌握程度。
                            </p>
                        </a>

                        <a class="select-test col-xs-4 home-link-anchor" href="student/exam">
                            <div class="select-test-icon">
                                <i class="fa fa-paper-plane"></i>
                            </div>
                            <h3> 在线考试 </h3>

                            <p>
                                参加正式或者模拟考试，您的教师可以发布正式的考试，
                                <br>您也可以主动申请这些考试
                            </p>
                        </a>

                        <a class="select-test col-xs-4 home-link-anchor" href="quick-start">
                            <div class="select-test-icon">
                                <i class="fa fa-plane"></i>
                            </div>
                            <h3> 快速入口 </h3>

                            <p>
                                通过已有的准考证快速参加考试，具体的准考证请从相关教师处获取，
                                或者在个人中心的考试信息中查找
                            </p>
                        </a>
                    </div>
                </div>
            </div>
            <div class="content" style="padding:30px 0 30px 0;background-color: rgb(246, 249, 250);">
                <div class="container">
                    <div style="margin-bottom: 10px;">
                        <h3>专业领域</h3>

                        <p>
                            我们提供多种专业课程供你学习
                        </p>
                    </div>
                    <div class="row">
                        <a class="field-category-div col-xs-3 home-link-anchor">
                            <div class="field-category-title" style="background-color: #E97051;">
                                <div class="field-category-inner">
                                    <i class="fa fa-medkit"></i>
                                </div>
                                <div class="field-category-text ">
                                    <p>
                                        医药行业考试
                                    </p>
                                </div>
                            </div>
                            <div class="field-category-footer">
                                <div class="field-category-footer-sub">
                                    <p class="field-student-subtitle">
                                        医药行业考试
                                    </p>
                                </div>
                                <div class="field-category-footer-sub">
                                    <i class="fa fa-users"></i>
                                    <span class="field-student-num">412</span>

                                    名学员
                                </div>

                            </div>
                        </a>
                        <a class="field-category-div col-xs-3 home-link-anchor">
                            <div class="field-category-title" style="background-color: #5C78B9;">
                                <div class="field-category-inner">
                                    <i class="fa fa-trophy"></i>
                                </div>
                                <div class="field-category-text ">
                                    <p>
                                        公务员申论
                                    </p>
                                </div>
                            </div>
                            <div class="field-category-footer">
                                <div class="field-category-footer-sub">
                                    <p class="field-student-subtitle">
                                        公务员申论
                                    </p>

                                </div>
                                <div class="field-category-footer-sub">
                                    <i class="fa fa-users"></i>
                                    <span class="field-student-num">2143</span>
                                    名学员
                                </div>

                            </div>
                        </a>
                        <a class="field-category-div col-xs-3 home-link-anchor">
                            <div class="field-category-title" style="background-color: #5BA276;">
                                <div class="field-category-inner">
                                    <i class="fa fa-car"></i>
                                </div>
                                <div class="field-category-text ">
                                    <p>
                                        驾校考试科目一
                                    </p>
                                </div>
                            </div>
                            <div class="field-category-footer">
                                <div class="field-category-footer-sub">
                                    <p class="field-student-subtitle">
                                        驾校考试科目一
                                    </p>

                                </div>
                                <div class="field-category-footer-sub">
                                    <i class="fa fa-users"></i>
                                    <span class="field-student-num">1213</span>
                                    名学员
                                </div>

                            </div>
                        </a>

                    </div>
                </div>
            </div>
            <div class="content" style="padding:30px 0 0 0;">
                <c:if test="${fn:length(newsList) > 0 }">
                <div class="container">
                    <div>
                        <h3>最新公告</h3>
                    </div>
                    <div style="margin-top: 20px;">
                        <ul class="news-list">
                            <c:choose>
                                <c:when test="${fn:length(newsList) eq 1 }">
                                    <li class="news-list-item clearfix">
                                        <a class="home-link-anchor" href="news/${newsList[0].newsId }" target="_blank">
                                            <div class="news-list-thumbnail">
                                                <img src="http://www.examstack.com/resources/images/index/index3.jpg">
                                            </div>
                                            <div class="news-list-content">
                                                <div class="news-list-title">
                                                        ${newsList[0].title }
                                                </div>
                                                <div class="news-list-creater">
                                                    <i class="fa fa-user"></i><span>${newsList[0].creator }</span>
                                                    <i class="fa fa-clock-o"></i><span><fmt:formatDate
                                                        value="${newsList[0].createTime }"
                                                        pattern="yyyy-MM-dd HH:mm"/></span>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="news-list-item clearfix">
                                        <a class="home-link-anchor" href="news/${newsList[0].newsId }" target="_blank">
                                            <div class="news-list-thumbnail">
                                                <img src="http://www.examstack.com/resources/images/index/index3.jpg">
                                            </div>
                                            <div class="news-list-content">
                                                <div class="news-list-title"
                                                ">
                                                    ${newsList[0].title }
                                            </div>
                                            <div class="news-list-creater">
                                                <i class="fa fa-user"></i><span>${newsList[0].creator }</span>
                                                <i class="fa fa-clock-o"></i><span><fmt:formatDate
                                                    value="${newsList[0].createTime }"
                                                    pattern="yyyy-MM-dd HH:mm"/></span>
                                            </div>

                                        </a>
                                    </li>
                                    <li class="news-list-item clearfix">
                                        <a class="home-link-anchor" href="news/${newsList[1].newsId }">
                                            <div class="news-list-thumbnail">
                                                <img src="http://www.examstack.com/resources/images/index/index2.jpg">
                                            </div>
                                            <div class="news-list-content">
                                                <div class="news-list-title">
                                                        ${newsList[1].title }
                                                </div>
                                                <div class="news-list-creater">
                                                    <i class="fa fa-user"></i><span>${newsList[1].creator }</span>
                                                    <i class="fa fa-clock-o"></i><span><fmt:formatDate
                                                        value="${newsList[1].createTime }"
                                                        pattern="yyyy-MM-dd HH:mm"/></span>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>


                        </ul>
                    </div>
                    </c:if>
                </div>
                <!-- Slider Ends -->
                <%@include file="common/footer.jsp" %>
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
            </div>
        </div>
    </div>