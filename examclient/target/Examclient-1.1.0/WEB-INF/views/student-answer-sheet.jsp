<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="min-height:400px;">

        <div class="row">
            <div class="col-xs-2 answersheet">
                <div class="nav-collapse " style="" tabindex="5000">
                    <ul class="sidebar-menu">
                        <li class="sub-menu "><a href="student/usercenter/finish-exam/${examId }" class=""> <i class="fa fa-bar-chart-o"></i><span>分析报告</span><span class=""></span></a> <ul class="sub"></ul></li>
                        <li class="sub-menu active"><a href="student/usercenter/student-answer-sheet/${examId }" class=""> <i class="fa fa-file-text"></i><span>详细解答</span><span class=""></span></a> <ul class="sub"></ul></li>
                        </ul>
                </div>
              <%--  <ul class="nav default-sidenav">
                    <li>
                        <a href="student/usercenter/finish-exam/${examId }"> <i class="fa fa-bar-chart-o"></i> 分析报告 </a>

                    </li>
                    <li class="active">
                        <a href="student/usercenter/student-answer-sheet/${examId }"> <i class="fa fa-file-text"></i> 详细解答 </a>
                    </li>
                </ul>--%>
            </div>
            <div class="col-xs-10" id="right-content">
                <div class="page-header">
                    <h1><i class="fa fa-file-text"></i> 学员试卷 </h1>
                </div>
                <div class="page-content">
                    <div class="def-bk" id="bk-exampaper">

                        <div class="expand-bk-content" id="bk-conent-exampaper">
                            <div id="exampaper-header">
                                <div id="exampaper-title">
                                    <i class="fa fa-send"></i>
                                    <span id="exampaper-title-name">${exampapername} </span>
                                    <span style="display:none;" id="exampaper-id">${exampaperid}</span>

                                </div>
                                <div id="exampaper-desc-container">
                                    <div id="exampaper-desc" class="exampaper-filter">


                                    </div>
                                    <div style="margin-top: 5px;">
                                        <span>试卷总分：</span> ${exampaperTotalScore}<span id="exampaper-total-point"
                                                                style="margin-right:20px;font-weight:bolder;"></span>
                                        <span>考生得分：</span>${examTotalScore}<span id="exampaper-raw-point"
                                                                style="color: #5cb85c;margin-right:20px;font-weight:bolder;"></span>
                                        <!-- <span id="add-more-qt-to-paper"><i class="small-icon fa fa-plus-square" title="添加选项"></i><span>增加更多题目</span></span> -->
                                    </div>
                                </div>


                            </div>
                            <input type="hidden" id="hist-id" value="${examHistoryId }">
                            <input type="hidden" id="paper-id" value="${examPaperId }">
                            <input type="hidden" id="exam-id" value="${examId }">
                            <ul id="exampaper-body" style="padding:0px;">
                                ${htmlStr }
                            </ul>
                            <div id="exampaper-footer">
                                <div id="question-navi">
                                    <div id="question-navi-controller">
                                        <i class="fa fa-arrow-circle-down"></i>
                                        <span>答题卡</span>
                                    </div>
                                    <div id="question-navi-content">
                                    </div>
                                </div>
                                <!-- <div style="padding-left:30px;">
                                    <button class="btn btn-info"><i class="fa fa-save"></i>完成阅卷</button>
                                </div>
                                 -->
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

<!-- Javascript files -->
<script type="text/javascript">
    $(function () {
        $(".raw-answer-point").attr("disabled", "disabled");
        $(".raw-answer-comment").attr("disabled", "disabled");
    });
</script>
