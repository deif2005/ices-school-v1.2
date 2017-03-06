<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="min-height:500px;">

        <div class="row">
            <%@include file="common/left-menu.jsp" %>
            <div class="col-xs-10" id="right-content-outer">
                <div class="page-content col-xs-2">
                    <input type="hidden" value="${examId }" id="exam-id-hidden">
                    <div id="exampaper-list">
                        <div class="table-search table-controller-item" style="float: left; margin-top:10px;">
                            <div class="input-group search-form" style="float: left;margin-right:20px;">
                                <input type="text" class="form-control" placeholder="搜索学员" value="${searchStr }"
                                       id="txt-search">
										<span class="input-group-btn">
											<button class="btn btn-sm btn-default" type="button"
                                                    id="btn-search">
                                                <i class="fa fa-search"></i>搜索
                                            </button> </span>
                            </div>
                        </div>
                        <table class="table-striped table">
                            <thead>
                            <tr>
                                <td>学员姓名</td>
                            </tr>
                            </thead>
                            <tbody id="tabContent">
                            <c:forEach items="${histList }" var="item">
                                <tr data-id="${item.histId}"
                                    <c:if test="${item.trueName==studentName}">class="active"</c:if>>
                                    <td>${item.trueName }
                                        <br>
                                        身份证:${item.nationalId }
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                    <div id="page-link-content">
                        <ul class="pagination pagination-sm">${pageStr}</ul>
                    </div>
                </div>
                <div class="col-xs-10"
                     <c:if test="${fn:length(histList)==0}">style="display: none"</c:if> >
                    <%--<iframe width="100%" id="exampaper-iframe" style="height: 1000px;" frameborder="0"  marginwidth="0" marginheight="0"></iframe>--%>
                    <div class="container" style="min-height:500px;">
                        <div class="row">
                            <div class="col-xs-12" id="right-content">
                                <div class="page-content">
                                    <div class="def-bk" id="bk-exampaper">
                                        <div class="expand-bk-content" id="bk-conent-exampaper">
                                            <div id="exampaper-header">
                                                <div id="exampaper-title">
                                                    <i class="fa fa-send"></i>
                                                    <span id="exampaper-title-name">${exampapername} </span>
                                                    <span style="float: right">姓名：${studentName} </span>
                                                    <input type="hidden" id="backType" value="inner">
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


                                                <div id="exampaper-footer">
                                                    <div id="question-navi">
                                                        <div id="question-navi-controller">
                                                            <i class="fa fa-arrow-circle-down"></i>
                                                            <span>答题卡</span>
                                                        </div>
                                                        <div id="question-navi-content">
                                                        </div>
                                                    </div>
                                                    <div style="padding-left:30px;">
                                                        <button class="btn btn-info"><i class="fa fa-save"></i>完成阅卷
                                                        </button>
                                                    </div>
                                                </div>
                                        </div>

                                    </div>
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
<script type="text/javascript" src="resources/js/exampaper-mark.js"></script>
<!-- Slider Ends -->

<!-- Javascript files -->
<script type="text/javascript">
    $(function () {
        $("#tabContent tr").click(function () {
            var id = $(this).data("id");
            var paramStr = "";
            var searchStr = $("#txt-search").val();
            if (searchStr != "") {
                var paramStr = "?searchStr=" + searchStr;
            }
            document.location.href = document.getElementsByTagName('base')[0].href
                    + util.getCurrentRole() + "/exam/student-exampaper/" + $("#exam-id-hidden").val() + "/" + id + paramStr;
        });

        $("#btn-search").click(function () {
            var paramStr = "";
            var searchStr = $("#txt-search").val();
            if (searchStr != "") {
                var paramStr = "?searchStr=" + searchStr;
            }
            document.location.href = document.getElementsByTagName('base')[0].href
                    + util.getCurrentRole() + "/exam/student-exampaper/" + $("#exam-id-hidden").val() + "/0" + paramStr;
        });

    })

</script>
