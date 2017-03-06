<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>
<style type="text/css">
    .df-input-narrow{width: 300px;align:center}
    span.form-label{float:none}
</style>
<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="min-height: 400px;">

        <div class="row">
            <div class="col-xs-2" id="left-menu">
                <div id="sidebar" class="nav-collapse " style="" tabindex="5000"></div>
            </div>

            <div class="col-xs-10" id="right-content">
                <div class="page-header">
                    <h1>
                        <i class="fa fa-cogs"></i> 基本资料
                    </h1>
                </div>
                <div class="page-content row">
                    <form class="form-horizontal" id="form-change-password"
                          action="student/updateUser" style="margin-top: 40px;" method="post">

                        <div class="form-line form-user-id-u" style="display: none;">
                            <span class="form-label"><span class="warning-label">*</span>用户ID：</span>
                            <input type="text" class="df-input-narrow" id="id-update"
                                   maxlength="20" name="userId"
                                   value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userid}">
                            <span class="form-message"></span> <br>
                        </div>
                        <div class="form-line form-username-u" style="display: block;" align="center">
                            <span class="form-label" style="float: none"><span class="warning-label">*</span>用户名&nbsp&nbsp：</span>
                            <input type="text" class="df-input-narrow" id="name-update"
                                   disabled="disabled" maxlength="20"
                                   value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}"> <span
                                class="form-message"></span> <br>
                        </div>
                        <div class="form-line form-truename-u" style="display: block;" align="center">
                            <span class="form-label"><span class="warning-label">*</span>真实姓名：</span>
                            <input type="text" class="df-input-narrow" id="truename-update"
                                   maxlength="20" name="trueName"
                                   value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.trueName}">
                            <span class="form-message"></span> <br>
                        </div>
                        <div class="form-line form-national-id-u" style="display: block;" align="center">
                            <span class="form-label"><span class="warning-label">*</span>身份证号：</span>
                            <input type="text" class="df-input-narrow" name="nationalId"
                                   id="national-id-update" maxlength="18"
                                   value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.nationalId}"> <span
                                class="form-message"></span> <br>
                        </div>
                        <div class="form-line form-phone-u" style="display: block;" align="center">
                            <span class="form-label"><span class="warning-label">*</span>手机号码：</span>
                            <input type="text" class="df-input-narrow" id="phone-update"
                                   maxlength="20" name="phoneNum"
                                   value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.phoneNum}">
                            <span class="form-message"></span> <br>
                        </div>
                        <div class="form-line form-email-u" style="display: block;" align="center">
                            <span class="form-label"><span class="warning-label">*</span>邮箱地址：</span>
                            <input type="text" class="df-input-narrow" id="email-update"
                                   maxlength="20" name="email"
                                   value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.email}"> <span
                                class="form-message"></span> <br>
                        </div>
                        <div class="form-line form-company-u" style="display: none;" align="center">
                            <span class="form-label"><span class="warning-label"></span>单位：</span>
                            <input type="text" class="df-input-narrow" id="company-update"
                                   maxlength="20"> <span class="form-message"></span> <br>
                        </div>
                        <div class="form-line form-department-u" style="display: block;" align="center">
                            <span class="form-label"><span class="warning-label"></span>部门单位：</span>
                            <select id="department-input-select-u" class="df-input-narrow" name="depId">
                                <option value="-1">--请选择--</option>
                                <c:forEach items="${depList }" var="item">
                                    <option value="${item.depId }"
                                            <c:if test="${item.depId == sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.depId }">
                                                selected="selected"
                                            </c:if>
                                            >${item.depName }</option>
                                </c:forEach>
                            </select> <span class="form-message"></span> <br>
                        </div>

                        <!-- Buttons -->
                        <div class="form-group">
                            <!-- Buttons -->
                            <div class="col-md-8 col-md-offset-2" align="center">
                                <button class="btn btn-info" id="update-student-btn">确认修改</button>

                            </div>
                        </div>
                    </form>


                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->
