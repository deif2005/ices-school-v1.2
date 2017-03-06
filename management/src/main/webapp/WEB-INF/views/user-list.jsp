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
                    <h1><i class="fa fa-list-ul"></i> 学员管理 </h1>
                </div>

                <div id="question-list">
                    <div class="table-controller">

                        <div class="btn-group table-controller-item" style="float: left">
                            <button class="btn btn-default btn-sm" id="add-user-modal-btn">
                                <i class="fa fa-plus-square"></i> 添加用户
                            </button>

                        </div>
                        <div class="btn-group table-controller-item" style="float: left">
                            <button class="btn btn-default btn-sm" id="link-user-modal-btn">
                                <i class="fa fa-random"></i> 关联用户
                            </button>

                        </div>

                        <div class="table-search table-controller-item"
                             style="float: right; width: 200px;">
                            <div class="input-group search-form">
                                <input type="text" class="form-control" placeholder="搜索用户" value="${searchStr }"
                                       id="txt-search">
						<span class="input-group-btn">
							<button class="btn btn-sm btn-default" type="button"
                                    id="btn-search" data-id="${groupId }">
                                <i class="fa fa-search"></i>搜索
                            </button> </span>
                            </div>
                        </div>
                        <div class="btn-group table-controller-item" style="float: right">
                            <select  class="form-control" id="group" style="width: 150px;font-size:12px" onchange="search()">
                                <option value="0">全部</option>
                                <c:forEach items="${groupList }" var="item">
                                    <option value="${item.groupId }" <c:if test="${item.groupId==groupId}">selected="true"</c:if> >${item.groupName }</option>
                                </c:forEach>
                            </select>
                        </div>

                    </div>
                    <table class="table-striped table">
                        <thead>
                        <tr>
                            <td>ID</td>
                            <td>用户名</td>
                            <td>姓名和身份证</td>
                            <td>手机邮箱</td>
                            <td>部门单位</td>
                            <td>创建时间</td>
                            <td>状态</td>
                            <td>操作</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${userList }" var="item">
                            <tr>
                                <td><span class="r-id">${item.userId }</span></td>
                                <td><span class="r-name">${item.userName }</span>
                                    <div>
                                            <%-- <a href="javascript:parent.location.href='admin/training/student-practice-status/${item.userId}'">学习进度</a> --%>
                                        <a href="admin/training/student-practice-status/${item.userId}" target="_blank">学习进度</a>
                                    </div>

                                </td>
                                <td>
                                    <div class="r-truename">
                                            ${item.trueName }
                                    </div>
                                    <div class="r-national-id">
                                            ${item.nationalId }
                                    </div>
                                </td>
                                <td>
                                    <div class="r-phone">
                                            ${item.phoneNum }
                                    </div>
                                    <div class="r-email">
                                            ${item.email }
                                    </div>
                                </td>
                                <td>
                                    <div class="r-company" style="display: none;">
                                            ${item.company }
                                    </div>
                                    <div class="r-dept">
                                            ${item.department }
                                    </div>
                                </td>
                                <td>
								<span class="r-createtime">
									<fmt:formatDate
                                            value="${item.createTime }" pattern="yyyy-MM-dd HH:mm"/>
								</span>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${item.enabled }">
                                            <span>启用</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span>禁用</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                    <span class="r-update-btn btn-sm btn-success" data-id="21321"
                          data-depid="${item.depId }">修改</span><span class="disable-btn btn-sm btn-danger"
                                                                     data-id="${item.userId }"
                                                                     data-status="${!item.enabled }">
								<c:choose>
                                    <c:when test="${!item.enabled }">
                                        启用
                                    </c:when>
                                    <c:otherwise>
                                        禁用
                                    </c:otherwise>
                                </c:choose>
								</span>
                    <span class="r-reset-pwd-btn btn-sm btn-warning" data-id="21321"
                          data-depid="${item.depId }">密码</span>
                                    <c:choose>
                                        <c:when test="${ groupId == 0 }">
										<span class="link-user-r-btn btn-sm btn-info" title="关联用户">
													<i class="fa fa-random"></i>
										</span>
                                        </c:when>
                                        <c:otherwise>
										<span class="unlink-user-r-btn btn-sm btn-danger" title="移出分组"
                                              data-id="${item.userId }" data-group="${groupId }">
													<i class="fa fa-times-circle"></i>
									</span>
                                        </c:otherwise>
                                    </c:choose>

                                </td>
                            </tr>

                        </c:forEach>

                        </tbody>
                        <tfoot></tfoot>
                    </table>
                    <input id="authority-type" type="hidden" value="${authority }">
                </div>
                <div id="page-link-content" style="text-align: right;">
                    <ul class="pagination pagination-sm">
                        ${pageStr}
                    </ul>
                </div>


                    <div class="modal fade" id="add-user-modal" tabindex="-1" role="dialog"
                         aria-labelledby="myLargeModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h6 class="modal-title" id="myModalLabel">添加用户</h6>
                                </div>
                                <div class="modal-body">
                                    <form id="user-add-form" style="margin-top:40px;" action="secure/add-user">
                                        <div class="form-line form-group-id" style="display: none;">
                                            <span class="form-label"><span class="warning-label">*</span>id：</span>
                                            <input type="text" class="df-input-narrow" id="group-add-id" value=""
                                                   disabled="disabled">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-group" style="display: block;">
                                            <span class="form-label"><span class="warning-label">*</span>添加用户到：</span>
                                            <input type="text" class="df-input-narrow" id="group-add" value="默认分组"
                                                   disabled="disabled">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-username" style="display: block;">
                                            <span class="form-label"><span class="warning-label">*</span>用户名：</span>
                                            <input type="text" class="df-input-narrow" id="name-add" maxlength="20">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-password" style="display: block;">
                                            <span class="form-label"><span class="warning-label">*</span>密码：</span>
                                            <input type="text" class="df-input-narrow" id="password-add" maxlength="20">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-truename" style="display: block;">
                                            <span class="form-label"><span class="warning-label">*</span>真实姓名：</span>
                                            <input type="text" class="df-input-narrow" id="truename-add" maxlength="20">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-national-id" style="display: block;">
                                            <span class="form-label"><span class="warning-label">*</span>身份证号：</span>
                                            <input type="text" class="df-input-narrow" id="national-id-add"
                                                   maxlength="18">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-phone" style="display: block;">
                                            <span class="form-label"><span class="warning-label">*</span>手机：</span>
                                            <input type="text" class="df-input-narrow" id="phone-add" maxlength="20">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-email" style="display: block;">
                                            <span class="form-label"><span class="warning-label">*</span>邮箱：</span>
                                            <input type="text" class="df-input-narrow" id="email-add" maxlength="90">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-company" style="display: none;">
                                            <span class="form-label"><span class="warning-label"></span>单位：</span>
                                            <input type="text" class="df-input-narrow" id="company-add" maxlength="30">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-department" style="display: block;">
                                            <span class="form-label"><span class="warning-label"></span>部门单位：</span>
                                            <select id="department-input-select" class="df-input-narrow">
                                                <option value="-1">--请选择--</option>
                                                <c:forEach items="${depList }" var="item">
                                                    <option value="${item.depId }">${item.depName }</option>
                                                </c:forEach>
                                            </select>
                                            <span class="form-message"></span>
                                            <br>
                                        </div>

                                    </form>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                        关闭窗口
                                    </button>
                                    <button id="add-user-btn" data-action="secure/add-user-ROLE_STUDENT"
                                            data-url="secure/user/student-list" type="button" class="btn btn-primary">
                                        确定添加
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="reset-pwd-modal" tabindex="-1" role="dialog"
                         aria-labelledby="myLargeModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h6 class="modal-title">重置密码</h6>
                                </div>
                                <div class="modal-body">
                                    <form id="reset-pwd-form" style="margin-top:40px;" action="secure/reset-pwd-user">
                                        <div class="form-line form-user-name-reset">
                                            <span class="form-label"><span class="warning-label">*</span>用户名：</span>
                                            <input type="text" class="df-input-narrow" id="username-reset"
                                                   maxlength="20" disabled="disabled">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-user-pwd-reset">
                                            <span class="form-label"><span class="warning-label">*</span>新密码：</span>
                                            <input type="text" class="df-input-narrow" id="pwd-reset" maxlength="20">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                    </form>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                        关闭窗口
                                    </button>
                                    <button id="reset-pwd-btn" type="button" class="btn btn-primary">
                                        确定修改
                                    </button>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="update-user-modal" tabindex="-1" role="dialog"
                         aria-labelledby="myLargeModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h6 class="modal-title" id="myModalLabel">修改用户账号</h6>
                                </div>
                                <div class="modal-body">
                                    <form id="user-update-form" style="margin-top:40px;" action="secure/update-user">
                                        <div class="form-line form-user-id-u" style="display: none;">
                                            <span class="form-label"><span class="warning-label">*</span>用户名：</span>
                                            <input type="text" class="df-input-narrow" id="id-update" maxlength="20">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-username-u" style="display: block;">
                                            <span class="form-label"><span class="warning-label">*</span>用户名：</span>
                                            <input type="text" class="df-input-narrow" id="name-update"
                                                   disabled="disabled" maxlength="20">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-truename-u" style="display: block;">
                                            <span class="form-label"><span class="warning-label">*</span>真实姓名：</span>
                                            <input type="text" class="df-input-narrow" id="truename-update"
                                                   maxlength="20">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-national-id-u" style="display: block;">
                                            <span class="form-label"><span class="warning-label">*</span>身份证号：</span>
                                            <input type="text" class="df-input-narrow" id="national-id-update"
                                                   maxlength="18">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-phone-u" style="display: block;">
                                            <span class="form-label"><span class="warning-label">*</span>手机：</span>
                                            <input type="text" class="df-input-narrow" id="phone-update" maxlength="20">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-email-u" style="display: block;">
                                            <span class="form-label"><span class="warning-label">*</span>邮箱：</span>
                                            <input type="text" class="df-input-narrow" id="email-update" maxlength="90">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-company-u" style="display: none;">
                                            <span class="form-label"><span class="warning-label"></span>单位：</span>
                                            <input type="text" class="df-input-narrow" id="company-update"
                                                   maxlength="30">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-department-u" style="display: block;">
                                            <span class="form-label"><span class="warning-label"></span>部门：</span>
                                            <select id="department-input-select-u" class="df-input-narrow">
                                                <option value="-1">--请选择--</option>
                                                <c:forEach items="${depList }" var="item">
                                                    <option value="${item.depId }">${item.depName }</option>
                                                </c:forEach>
                                            </select>
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                        关闭窗口
                                    </button>
                                    <button id="update-teacher-btn" type="button" class="btn btn-primary">
                                        确定修改
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="link-user-modal" tabindex="-1" role="dialog"
                         aria-labelledby="myLargeModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h6 class="modal-title" id="myModalLabel">关联用户</h6>
                                </div>
                                <div class="modal-body">
                                    <form id="link-user-form">
                                        <div class="form-line form-link" style="display: block;">
                                            <span class="form-label"><span class="warning-label"></span>关联用户到：</span>
                                            <input type="text" class="df-input-narrow" id="group-add-link" value=""
                                                   disabled="disabled">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-user-id" style="display: block;">
                                            <span class="form-label"><span class="warning-label"></span>用户名：</span>
                                            <input type="text" class="df-input-narrow" id="name-add-link">
                                            <span class="form-message"></span>
                                            <br>
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
                    <div class="modal fade" id="link-user-modal-r" tabindex="-1" role="dialog"
                         aria-labelledby="myLargeModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h6 class="modal-title" id="myModalLabel">关联用户</h6>
                                </div>
                                <div class="modal-body">
                                    <form id="link-user-form-r">
                                        <div class="form-line form-link" style="display: block;">
                                            <span class="form-label"><span class="warning-label"></span>关联用户到：</span>
                                            <select id="group-add-link-r" class="df-input-narrow" style="width:150px;">
                                                <c:forEach items="${groupList }" var="item">
                                                    <option value="${item.groupId }">${item.groupName }</option>
                                                </c:forEach>
                                            </select>

                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                        <div class="form-line form-user-id" style="display: none;">
                                            <span class="form-label"><span class="warning-label"></span>用户ID：</span>
                                            <input type="text" class="df-input-narrow" id="name-add-link-r">
                                            <span class="form-message"></span>
                                            <br>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                        关闭窗口
                                    </button>
                                    <button id="link-user-btn-r" type="button" class="btn btn-primary">
                                        确定添加
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
<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->

<!-- Javascript files -->
<%--<script type="text/javascript" src="resources/js/group-manage.js"></script>--%>
<script type="text/javascript" src="resources/js/user-list.js"></script>
<script type="text/javascript" src="resources/js/add-user.js"></script>
<script type="text/javascript" src="resources/js/update-user.js"></script>
