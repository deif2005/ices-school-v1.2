<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="height:500px;">

        <div class="row" >
            <%--<div class="col-xs-2" id="left-menu">
             &lt;%&ndash;   <c:import url="/common-page/left-menu?topMenuId=${topMenuId}&leftMenuId=${leftMenuId}"
                          charEncoding="UTF-8"/>&ndash;%&gt;
                 <div id="sidebar" class="nav-collapse " style="overflow: hidden;" tabindex="5000">
                     </div>

            </div>--%>
            <%@include file="common/left-menu.jsp" %>
            <div class="col-xs-10" id="right-content">
                <div class="page-header">
                    <h1><i class="fa fa-bar-chart-o"></i> 培训管理 </h1>
                </div>
                <div class="page-content">
                    <div class="row">
                        <div class="col-xs-12">

                            <table class="table-striped table" id="training-table">
                                <thead>
                                <tr>
                                    <td>ID</td>
                                    <td>培训名</td>
                                    <td>创建时间</td>
                                    <td>创建人</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${trainingList }" var="item">
                                    <tr>
                                        <td>${item.trainingId }</td>
                                        <td>
                                            <a class="training-sections"
                                               data-id="${item.trainingId }"
                                               href="<%=list[1]%>/training/section-list/${item.trainingId }"
                                               target="_blank">${item.trainingName }</a>
                                        </td>
                                        <td><fmt:formatDate value="${item.createTime }"
                                                            pattern="yyyy-MM-dd HH:mm"/></td>
                                        <td>${item.creatorName }</td>
                                        <td>
                                            <a href="<%=list[1]%>/training/section-list/${item.trainingId }"
                                               class="btn-sm btn-info">内容</a>
                                            <a href="<%=list[1]%>/training/student-training-history/${item.trainingId }"
                                               class="btn-sm btn-info">进度</a>
                                            <a class="delete-paper btn-sm btn-danger"
                                               onclick="delTraining(${item.trainingId })">删除</a>
                                        </td>
                                    </tr>

                                </c:forEach>

                                </tbody>
                                <tfoot></tfoot>
                            </table>
                        </div>

                    </div>
                </div>
                <div id="page-link-content">
                    <ul class="pagination pagination-sm">
                        ${pageStr}
                    </ul>
                </div>

            </div>
        </div>
    </div>
</div>

</div>
<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->

<!-- Javascript files -->

<script type="text/javascript" src="resources/js/training-list.js"></script>

