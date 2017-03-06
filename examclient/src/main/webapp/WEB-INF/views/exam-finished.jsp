<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp"%>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="min-height:400px;">

        <div class="row">
            <div class="col-xs-2 answersheet">
                <div class="nav-collapse " style="" tabindex="5000">
                    <ul class="sidebar-menu">
                        <li class="sub-menu active"><a href="student/usercenter/finish-exam/${examId }" class=""> <i class="fa fa-bar-chart-o"></i><span>分析报告</span><span class=""></span></a> <ul class="sub"></ul></li>
                        <li class="sub-menu"><a href="student/usercenter/student-answer-sheet/${examId }" class=""> <i class="fa fa-file-text"></i><span>详细解答</span><span class=""></span></a> <ul class="sub"></ul></li>
                    </ul>
                </div>
               <%-- <ul class="nav default-sidenav">
                    <li class="active">
                        <a href="student/usercenter/finish-exam/${examId }"> <i class="fa fa-bar-chart-o"></i> 分析报告 </a>

                    </li>
                    <li>
                        <a href="student/usercenter/student-answer-sheet/${examId }"> <i class="fa fa-file-text"></i> 详细解答 </a>
                    </li>
                </ul>--%>

            </div>
            <div class="col-xs-10" id="right-content" style="margin-left: 0px">
                <div class="page-header">
                    <h1><i class="fa fa-bar-chart-o"></i> 分析报告 </h1>
                </div>
                <div class="page-content row">

                    <div id="graph-base" class="col-xs-8" style="height:200px;width: 200px;">

                    </div>
                    <div class="col-xs-7" style="margin-top: 24px;">

                        <div class="form-line add-role">
                            <span class="form-label">考试名称：</span>
                            <span> 练习考试 </span>
                        </div>
                        <div class="form-line add-role">
                            <span class="form-label">交卷时间：</span>
									<span>
										<fmt:formatDate value="${create_time }" type="time" timeStyle="full"
                                                        pattern="yyyy-MM-dd HH:mm"/>
									</span>
                        </div>
                        <div class="form-line add-role">
                            <span class="form-label">总题目：</span>
                            <span class="label label-info"> ${total } </span>
                        </div>
                        <div class="form-line exam-report-correct">
                            <span class="form-label">正确题目：</span>
                            <span class="label label-success"> ${right } </span>
                        </div>
                        <div class="form-line exam-report-error">
                            <span class="form-label">错误题目：</span>
                            <span class="label label-danger"> ${wrong } </span>
                        </div>

                    </div>

                </div>
                <div class="page-content row">
                    <table class="table table-bordered" style="margin-top: 25px;margin-left: 10px;">
                        <thead>
                        <tr>
                            <th>考点</th>
                            <th>答题情况</th>
                            <th>正确率</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${reportResultMap }" var="result">
                            <tr>
                                <td>${result.value.pointName }</td>
                                <td>${result.value.rightAmount } / ${result.value.amount }</td>
                                <td>
                                    <fmt:formatNumber value="${result.value.rightAmount/result.value.amount }"
                                                      type="number" pattern="0.00%"/>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
                <div class="page-content row">
                    <div id="question-navi" style="margin: 24px 10px;width: 100%;">

                        <div id="question-navi-content" style="padding: 15px 12px;">
                            <c:forEach items="${idList }" var="item" varStatus="s">
                                <c:forEach items="${answer }" var="answerItem">
                                    <c:if test="${item == answerItem.key }">
                                        <c:if test="${answerItem.value }">
                                            <a class="question-navi-item">${s.index + 1 }</a>
                                        </c:if>
                                        <c:if test="${!answerItem.value }">
                                            <a class="question-navi-item navi-item-error">${s.index + 1 }</a>
                                        </c:if>
                                    </c:if>
                                </c:forEach>

                            </c:forEach>

                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>

<%@include file="common/footer.jsp" %>
<script type="text/javascript" src="resources/js/exam-finished.js"></script>
<!-- Slider Ends -->
