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
                    <h1><i class="fa fa-list-ul"></i> 学员名单 </h1>

                </div>
                <div class="page-content">
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
                            <div class="btn-group table-controller-item" style="float: left">
                                <button class="btn btn-default btn-sm" id="link-user-r-btn">
                                    <i class="fa fa-random"></i> 关联用户
                                </button>
                            </div>
                            <div class="btn-group table-controller-item" style="float: left">
                                <button class="btn btn-default btn-sm" id="link-user-group-r-btn">
                                    <i class="fa fa-random"></i> 关联用户组
                                </button>
                            </div>
                        </div>
                        <table class="table-striped table">
                            <thead>
                            <tr>
                                <td>学员姓名</td>
                                <!-- <td>学员姓名</td> -->
                                <!-- <td style="width: 150px;">准考证号</td> -->
                                <td>单位部门</td>
                                <td>总分</td>
                                <td style="width: 90px;">得分
                                    <a class="fa fa-sort-numeric-asc"
                                       href="<%=list[1]%>/exam/exam-student-list/${examId }?order=asc&limit=${limit}&searchStr=${searchStr}"></a>
                                    <a class="fa fa-sort-numeric-desc"
                                       href="<%=list[1]%>/exam/exam-student-list/${examId }?order=desc&limit=${limit}&searchStr=${searchStr}"></a>
                                </td>
                                <td>是否通过</td>
                                <td>考试时间</td>
                                <td>交卷时间</td>
                                <td style="width: 100px;">审核状态</td>
                                <!-- <td>审核时间</td> -->
                                <td>
                                    操作
                                </td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${histList }" var="item">
                                <tr>
                                    <td>${item.userName } ${item.trueName }
                                        <br>
                                        准考证:${item.seriNo }
                                        <br>
                                        身份证:${item.nationalId }
                                    </td>
                                        <%-- <td>${item.trueName }</td>
                                        <td>${item.seriNo }</td> --%>
                                    <td>${item.depName }</td>
                                    <td>

                                            ${item.point }
                                        <br>
                                        <c:if test="${item.approved == 2 }">
                                            <a  href="<%=list[1]%>/exam/mark-exampaper/${item.histId}">阅卷</a>
                                        </c:if>
                                        <c:if test="${item.approved == 3 }">
                                            <a  href="<%=list[1]%>/exam/mark-exampaper/${item.histId}">重复阅卷</a>
                                        </c:if>
                                    </td>


                                    <td>
                                        <c:choose>
                                            <c:when test="${item.pointGet < item.passPoint }">
															<span style="color:#d9534f">
                                                                    ${item.pointGet }
                                                            </span>

                                            </c:when>
                                            <c:otherwise>
																<span style="color:#5cb85c">
                                                                        ${item.pointGet }
                                                                </span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.pointGet < item.passPoint }">
															<span style="color:#d9534f">
																未通过
															</span>

                                            </c:when>
                                            <c:otherwise>
															<span style="color:#5cb85c">
																通过
															</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                    <td><fmt:formatDate value="${item.submitTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.approved == 0 }">
                                                未审核
                                            </c:when>
                                            <c:when test="${item.approved == 1 }">
                                                通过
                                            </c:when>
                                            <c:when test="${item.approved == 2 }">
                                                已交卷
                                            </c:when>
                                            <c:when test="${item.approved == 3 }">
                                                已阅卷
                                            </c:when>
                                        </c:choose>
                                    </td>
                                        <%-- <td><fmt:formatDate value="${item.verifyTime}" pattern="yyyy-MM-dd HH:mm"/></td> --%>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.approved == 0 }">
                                                <button class="approved-btn btn btn-success" data-id="${item.histId }">
                                                    通过
                                                </button>
                                                <button class="disapproved-btn btn btn-warning"
                                                        data-id="${item.histId }">拒绝
                                                </button>
                                            </c:when>
                                        </c:choose>
                                        <c:if test="${item.approved == 3 }">
                                            <a target="_blank"
                                               href="<%=list[1]%>/exam/student-answer-sheet/${item.histId}">查看答卷</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>

                            </tfoot>
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
<!-- 关联用户modal -->
<div class="modal fade" id="link-user-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h6 class="modal-title" id="myModalLabel">关联用户</h6>
            </div>
            <div class="modal-body">
                <form id="link-user-form">
                    <div class="form-line form-user-id" style="display: block;">
                        <span class="form-label"><span class="warning-label"></span>用户姓名：</span>
                        <input type="text" class="df-input-narrow" id="name-add-link">
                        <span class="form-message"></span> <br>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭窗口
                </button>
                <button id="link-user-btn" type="button" class="btn btn-primary">
                    确定添加
                </button>
            </div>
        </div>
    </div>
</div>
<!-- 管理用户组modal -->
<div class="modal fade" id="link-user-group-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h6 class="modal-title" id="myModalLabel">关联用户组</h6>
            </div>
            <div class="modal-body">
                <form id="link-user-group-form">
                    <div class="form-line form-user-id" style="display: block;">
                        <fieldset>
                            <c:forEach items="${groupList }" var="item">
                                <label><input type="checkbox" value="${item.groupId }"/></label>
                                <label>${item.groupName }</label>
                                <br>
                            </c:forEach>
                        </fieldset>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭窗口
                </button>
                <button id="link-user-group-btn" type="button" class="btn btn-primary">
                    确定添加
                </button>
            </div>
        </div>
    </div>
</div>

<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->

<!-- Javascript files -->
<script type="text/javascript" src="resources/js/exam-user-list.js"></script>
<script type="text/javascript">
    $(function () {
        $("#link-user-r-btn").click(function () {
            //	$("#link-user-btn").data("id",$(this).data("id"));
            $("#link-user-modal").modal({
                backdrop: true,
                keyboard: true
            });
        });
        $("#link-user-group-r-btn").click(function () {
            //	$("#link-user-btn").data("id",$(this).data("id"));
            $("#link-user-group-modal").modal({
                backdrop: true,
                keyboard: true
            });
        });
    });
</script>
