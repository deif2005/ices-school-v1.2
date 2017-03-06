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
                    <h1><i class="fa fa-list-ul"></i> 模拟考试 </h1>
                </div>
                <div class="page-content">
                    <div id="question-list">
                        <table class="table-striped table">
                            <thead>
                            <tr>
                                <td>ID</td>
                                <td>考试名称</td>
                                <td>起始时间</td>
                                <td>截止时间</td>


                                <td>试卷</td>
                                <td>创建人</td>
                                <td>状态</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${examList }" var="item">
                                <tr>
                                    <td>
                                            ${item.examId }
                                    </td>
                                    <td>


                                        <span>${item.examName }</span><br>
                                        <a href="<%=list[1]%>/exam/exam-student-list/${item.examId }" target="_blank">学员名单</a>

                                    </td>
                                    <td><fmt:formatDate value="${item.effTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                    <td><fmt:formatDate value="${item.expTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                    <td>${item.examPaperName }</td>
                                    <td>${item.creatorId }</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.approved == 0 }">
                                                未审核
                                            </c:when>
                                            <c:when test="${item.approved == 1 }">
                                                审核通过
                                            </c:when>
                                            <c:otherwise>
                                                审核未通过
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.approved == 0 }">
                                                <button class="approved-btn" data-id="${item.examId }">通过</button>
                                                <button class="disapproved-btn" data-id="${item.examId }">不通过</button>
                                            </c:when>
                                            <c:when test="${item.approved == 2 }">
                                                <button class="delete-btn" data-id="${item.examId }">删除</button>
                                            </c:when>
                                        </c:choose>
                                    </td>

                                </tr>
                            </c:forEach>

                            </tbody>
                            <tfoot></tfoot>
                        </table>

                    </div>
                    <div class="page-link-content">
                        <ul class="pagination pagination-sm">${pageStr}</ul>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->

<!-- Javascript files -->
<script type="text/javascript" src="resources/js/exam-list.js"></script>
