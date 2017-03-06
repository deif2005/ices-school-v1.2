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
                    <h1><i class="fa fa-history"></i> 培训历史</h1>
                </div>
                <div class="page-content row">
                    <div id="question-list">
                        <table class="table-striped table">
                            <thead>
                            <tr>
                                <td>培训名称</td>
                                <td>学习进度</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${trainingList }" var="item">
                                <tr>
                                    <td>${item.trainingName }</td>
                                    <td>
                                        <div>
                                            <c:forEach items="${processMap[item.trainingId] }" var="processItem">
                                                <c:choose>
                                                    <c:when test="${processItem.process == 1 }">
                                                        <a class="section-navi-item navi-item-success"
                                                           href="student/training/${item.trainingId }?sectionId=${processItem.sectionId}">${processItem.sectionName }</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a class="section-navi-item"
                                                           href="student/training/${item.trainingId }?sectionId=${processItem.sectionId}">${processItem.sectionName }</a>
                                                    </c:otherwise>
                                                </c:choose>

                                            </c:forEach>

                                        </div>
                                    </td>
                                    <td><a class="btn btn-success approved-btn" data-id=""
                                           href="student/training/${item.trainingId }">参加培训</a></td>
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

<!-- Slider Ends -->
