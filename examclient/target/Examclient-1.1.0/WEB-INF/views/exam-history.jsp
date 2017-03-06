<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp"%>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="min-height:500px;">

        <div class="row">
            <div class="col-xs-2" id="left-menu">
                <div id="sidebar" class="nav-collapse " style="" tabindex="5000"></div>
            </div>
            <div class="col-xs-10" id="right-content">
                <div class="page-header">
                    <h1><i class="fa fa-history"></i> 考试历史</h1>
                </div>
                <div class="page-content row">
                    <div id="question-list">
                        <table class="table-striped table">
                            <thead>
                            <tr>
                                <td>试卷名称</td>
                                <td>状态</td>
                                <td>类型</td>
                                <td>交卷时间</td>
                                <td>及格分</td>
                                <td>得分</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${hisList }" var="item">
                                <tr>

                                    <td>
                                            ${item.examName }
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.approved == 0 }"><span
                                                    class="label-warning label-sm label">未审核</span></c:when>
                                            <c:when test="${item.approved == 1 }"><span
                                                    class="label-default label-sm label">已审核</span></c:when>
                                            <c:when test="${item.approved == 2 }"><span
                                                    class="label-info label-sm label">已交卷</span></c:when>
                                            <c:when test="${item.approved == 3 }"><span
                                                    class="label-success label-sm label">已阅卷</span></c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.examType == 1 }">私有考试</c:when>
                                            <c:when test="${item.examType == 2 }">公有考试</c:when>
                                            <c:when test="${item.examType == 3 }">模拟考试</c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${item.submitTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                            ${item.passPoint }
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.pointGet lt item.passPoint }">
                                                <font color="red">${item.pointGet }</font>
                                            </c:when>
                                            <c:otherwise>
                                                <font color="green">${item.pointGet }</font>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.approved == 3 or (item.examType == 3 and item.approved != 1)}">
                                                <a class="btn btn-success btn-sm"
                                                   href="student/usercenter/student-answer-sheet/${item.examId }" target="_blank"
                                                   title="预览">详细解答</a>
                                            </c:when>
                                        </c:choose>

                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                            <tfoot></tfoot>
                        </table>
                    </div>
                    <div id="page-link-content">
                        <ul class="pagination pagination-sm">${pageStr}</ul>
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>

<%@include file="common/footer.jsp" %>
<script type="text/javascript" src="resources/js/exam-finished.js"></script>
<!-- Slider Ends -->
