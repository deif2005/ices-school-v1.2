<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <%
        String temp = "";
        if (list[1].equals("admin")) {
            temp = "admin";
        } else {
            temp = "teacher";
        }

    %>
    <div class="container" style="min-height:500px;">

        <div class="row">
            <%--<div class="col-xs-2" id="left-menu">
                <div id="sidebar" class="nav-collapse " style="overflow: hidden;" tabindex="5000">
                </div>
            </div>--%>
            <%@include file="common/left-menu.jsp" %>
            <div class="col-xs-10" id="right-content">
                <div class="page-header">
                    <h1><i class="fa fa-dashboard"></i> 管理首页 </h1>
                </div>
                <div class="page-content">
                    <div class="bs-example bs-example-bg-classes" data-example-id="contextual-backgrounds-helpers">
                        <div class="row" style="margin-top:20px;">
                            <div class="col-xs-7" style="width: 100%;">
                                <div style="height:80px;">
                                    <div class="infobox infobox-green infobox-small">
                                        <div class="infobox-progress">
                                            <i class="fa fa-cloud"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <div class="infobox-content">试题</div>
                                            <div class="infobox-content" id="question-num">-</div>
                                        </div>
                                    </div>
                                    <div class="infobox infobox-dark infobox-small">
                                        <div class="infobox-progress">
                                            <i class="fa fa-file-text-o"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <div class="infobox-content">试卷</div>
                                            <div class="infobox-content" id="questionpaper-num">-</div>
                                        </div>
                                    </div>
                                    <div class="infobox infobox-blue infobox-small">
                                        <div class="infobox-progress">
                                            <i class="fa fa-user"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <div class="infobox-content">学员</div>
                                            <div class="infobox-content" id="student-num">-</div>
                                        </div>
                                    </div>
                                </div>


                                <div class="widget-box transparent">
                                    <div class="widget-header widget-header-flat">
                                        <h4 class="widget-title lighter">
                                            <i class="ace-icon fa fa-star orange"></i>
                                            需要审核
                                        </h4>

                                        <div class="widget-toolbar no-border">
                                            <a href="<%=list[1]%>/exam/exam-list" target="_blank">查看更多</a>
                                        </div>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main no-padding">
                                            <table class="table-striped table" id="studentApprovedTable">
                                                <thead>
                                                <tr>
                                                    <td>学员ID</td>
                                                    <td>姓名</td>
                                                    <td>单位</td>

                                                    <td>
                                                        操作
                                                    </td>
                                                </tr>
                                                </thead>
                                                <tbody>

                                                </tbody>
                                                <tfoot>

                                                </tfoot>
                                            </table>


                                        </div>
                                    </div>
                                </div>
                                <div class="widget-box transparent">
                                    <div class="widget-header widget-header-flat">
                                        <h4 class="widget-title lighter">
                                            <i class="ace-icon fa fa-rss orange"></i>
                                            需要阅卷
                                        </h4>

                                        <div class="widget-toolbar no-border">
                                            <a href="<%=list[1]%>/exam/exam-list" target="_blank">查看更多</a>
                                        </div>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main no-padding">
                                            <table class="table-striped table" id="studentMarkTable">
                                                <thead>
                                                <tr>
                                                    <td>学员ID</td>
                                                    <td>姓名</td>
                                                    <td>单位</td>
                                                    <td>交卷时间</td>
                                                    <td>
                                                        操作
                                                    </td>
                                                </tr>
                                                </thead>
                                                <tbody>


                                                </tbody>
                                                <tfoot>

                                                </tfoot>
                                            </table>


                                        </div>
                                    </div>
                                </div>

                            </div>
                            <!-- <div class="col-xs-5">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-flat">
                                        <h4 class="widget-title lighter">
                                            <i class="ace-icon fa fa-signal orange"></i>
                                            Popular Domains
                                        </h4>

                                        <div class="widget-toolbar no-border">
                                            <select id="field-select">
                                                <c:forEach items="${fieldList }" var="item">
                                                    <option value="${item.fieldId}">${item.fieldName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main no-padding">
                                            <div id="mychart" style="height:400px;"></div>
                                        </div>
                                    </div>
                                </div>
                                <div id="calendar" style="margin-top:50px;">
                                </div>
                            </div>-->
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->

<!-- Javascript files -->
<!-- jQuery -->
<script type="text/javascript" src="resources/js/jquery/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="resources/js/all.js"></script>

<!-- Bootstrap JS -->
<script type="text/javascript" src="resources/js/echarts-all.js"></script>
<script type="text/javascript" src="resources/fullcalendar-2.1.1/lib/moment.min.js"></script>
<script type="text/javascript" src="resources/fullcalendar-2.1.1/fullcalendar.min.js"></script>
<script type="text/javascript" src="resources/fullcalendar-2.1.1/lang-all.js"></script>
<script type="text/javascript" src="resources/js/dashboard.js"></script>
