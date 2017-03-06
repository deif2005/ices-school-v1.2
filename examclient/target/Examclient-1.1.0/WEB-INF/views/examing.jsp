<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp"%>
<%@include file="common/banner.jsp" %>

<div class="content" style="margin-bottom: 100px;min-height: 400px;">

    <div class="container">
        <div class="row">
            <div class="col-xs-2" style="padding-right: 0px;padding-bottom:15px;">
                <div class="def-bk" id="bk-exam-control">

                    <div class="def-bk-content" id="exam-control">

                        <div id="question-time" class="question-time-normal">
                            <div style="height:140px;text-align: center;">
                                <i id="time-icon" class="fa fa-clock-o"> </i>
                            </div>

                            <span style="margin-right:10px;color: #B8B8B8;">已用时</span>
                            <span id="exam-clock">&nbsp;</span>
                            <span id="exam-timestamp" style="display:none;">${duration }</span>
                            <div id="answer-save-info"></div>

                        </div>
                        <div id="question-submit">
                            <button class="btn-success btn" style="width:100%;">
                                我要交卷
                            </button>
                        </div>
                        <div id="exam-info" style="display:none;">
                            <span id="answer-hashcode"></span>
                        </div>
                    </div>

                </div>

            </div>
            <div class="col-xs-10" style="padding-right: 0px;">
                <div class="def-bk" id="bk-exampaper">

                    <div class="expand-bk-content" id="bk-conent-exampaper">
                        <div id="exampaper-header">
                            <div id="exampaper-title">
                                <i class="fa fa-send"></i>
                                <span id="exampaper-title-name"> 模拟试卷 </span>
                                <span style="display:none;" id="exampaper-id">1</span>
                            </div>
                            <div id="exampaper-desc-container">
                                <div id="exampaper-desc" class="exampaper-filter">

                                </div>
                            </div>

                        </div>
                        <input type="hidden" id="start-time" value="${startTime }">
                        <input type="hidden" id="hist-id" value="${examHistoryId }">
                        <input type="hidden" id="paper-id" value="${examPaperId }">
                        <input type="hidden" id="exam-id" value="${examId }">
                        <ul id="exampaper-body">
                            ${htmlStr }
                        </ul>
                        <div id="exampaper-footer">
                            <div id="question-navi">
                                <div id="question-navi-controller">
                                    <i class="fa fa-arrow-circle-down"></i>
                                    <span>答题卡1</span>
                                </div>
                                <div id="question-navi-content">
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>
<%@include file="common/footer.jsp" %>

<script src="resources/ueditor/ueditor.config.js"></script>
<script src="resources/ueditor/ueditor.all.js"></script>
<script src="resources/ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="resources/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
<script src="resources/ueditor/kityformula-plugin/getKfContent.js"></script>
<script src="resources/ueditor/kityformula-plugin/defaultFilterFix.js"></script>


<script type="text/javascript" src="resources/js/all.js?v=0712"></script>
<script type="text/javascript" src="resources/js/paper-examing.js"></script>
<!-- Slider Ends -->
