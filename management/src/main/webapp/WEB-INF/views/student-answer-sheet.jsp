<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="min-height:500px;">

        <div class="row">
            <%--<div class="col-xs-2" id="left-menu">
                <div id="sidebar" class="nav-collapse " style="overflow: hidden;" tabindex="5000">
                </div>

            </div>--%>
            <%@include file="common/left-menu.jsp" %>
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
                                        <span>试卷总分：</span><span id="exampaper-total-point"
                                                                style="margin-right:20px;font-weight:bolder;"></span>
                                        <span>考生得分：</span><span id="exampaper-raw-point"
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
<script type="text/javascript" src="resources/js/exampaper-mark.js"></script>
<script>
    $(function () {
        $(".raw-answer-point").attr("disabled", "disabled");
        $(".raw-answer-comment").attr("disabled", "disabled");
    });
</script>
