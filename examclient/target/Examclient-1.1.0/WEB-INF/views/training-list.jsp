<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>
<div class="container" style="min-height:500px;">

    <div class="row">
        <div class="col-xs-2" id="left-menu">
            <div id="sidebar" class="nav-collapse " style="" tabindex="5000"></div>
        </div>
        <div class="col-xs-10" id="right-content">
            <!-- Slider starts -->
            <div class="content" style="margin-bottom: 50px;min-height: 400px;">
                <div class="container" style="margin-top: 40px;">
                    <div class="row">
                        <div class="col-xs-12">
                            <div style="border-bottom: 1px solid #ddd;">
                                <h3 class="title"><i class="fa fa-book"></i>培训资料</h3>

                            </div>
                            <div id="training-list">
                                <table class="table-striped table">
                                    <thead>
                                    <tr>
                                        <td style="display:none;">ID</td>
                                        <td>培训名称</td>
                                        <td>描述</td>
                                        <!-- <td>创建人</td> -->
                                        <td>截止时间</td>
                                        <td>操作</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${trainingList }" var="item">
                                        <tr>
                                            <td style="display:none;">${item.trainingId }</td>
                                            <td>
                                                    ${item.trainingName }

                                            </td>
                                            <td style="width:50%;">
                                                <div style="font-size:12px;padding:10px 0px;">

                                                        ${item.trainingDesc }
                                                </div>
                                            </td>
                                            <td><fmt:formatDate value="${item.expTime}"
                                                                pattern="yyyy-MM-dd"/></td>

                                                <%-- <td>${item.trainingDesc }</td> --%>
                                                <%-- <td><fmt:formatDate value="${item.createTime}"
                                                        pattern="yyyy-MM-dd" /></td> --%>
                                                <%-- 	<td><fmt:formatDate value="${item.expTime}"
                                                            pattern="yyyy-MM-dd" /></td> --%>
                                            <td><a class="btn btn-success approved-btn" data-id="${item.trainingId }"
                                                   href="student/training/${item.trainingId }">参加培训</a></td>

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
    </div>
</div>
<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->
