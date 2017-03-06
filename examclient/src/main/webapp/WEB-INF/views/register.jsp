<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp"%>
<%@include file="common/banner.jsp" %>

<div class="content" style="margin-bottom: 100px;min-height: 400px;">

    <div class="container">
        <div class="row">

            <div class="col-md-12">
                <div class="lrform">
                    <h5>注册账号</h5>
                    <span class="form-message"></span>
                    <div class="form">
                        <!-- Register form (not working)-->
                        <form class="form-horizontal" id="form-create-account"
                              action="/student/user-register">
                            <div class="form-line form-username" style="display: block;">
                                <span class="form-label"><span class="warning-label">*</span>用户名：</span>
                                <input type="text" class="df-input-narrow" id="name-add"
                                       maxlength="20"> <span class="form-message"></span> <br>
                            </div>
                            <div class="form-line form-password" style="display: block;">
                                <span class="form-label"><span class="warning-label">*</span>密码：</span>
                                <input type="password" class="df-input-narrow" id="password-add"
                                       maxlength="20"> <span class="form-message"></span> <br>
                            </div>
                            <div class="form-line form-password-confirm" style="display: block;">
                                <span class="form-label"><span class="warning-label">*</span>重复密码：</span>
                                <input type="password" class="df-input-narrow" id="password-add"
                                       maxlength="20"> <span class="form-message"></span> <br>
                            </div>
                            <div class="form-line form-truename" style="display: block;">
                                <span class="form-label"><span class="warning-label">*</span>真实姓名：</span>
                                <input type="text" class="df-input-narrow" id="truename-add"
                                       maxlength="20"> <span class="form-message"></span> <br>
                            </div>
                            <div class="form-line form-national-id" style="display: block;">
                                <span class="form-label"><span class="warning-label">*</span>身份证号：</span>
                                <input type="text" class="df-input-narrow" id="national-id-add"
                                       maxlength="18"> <span class="form-message"></span> <br>
                            </div>
                            <div class="form-line form-phone" style="display: block;">
                                <span class="form-label"><span class="warning-label">*</span>手机：</span>
                                <input type="text" class="df-input-narrow" id="phone-add"
                                       maxlength="18"> <span class="form-message"></span> <br>
                            </div>
                            <div class="form-line form-email" style="display: block;">
                                <span class="form-label"><span class="warning-label">*</span>邮箱：</span>
                                <input type="text" class="df-input-narrow" id="email-add"
                                       maxlength="60"> <span class="form-message"></span> <br>
                            </div>
                            <div class="form-line form-company" style="display: none;">
                                <span class="form-label"><span class="warning-label"></span>单位：</span>
                                <input type="text" class="df-input-narrow" id="company-add"
                                       maxlength="60"> <span class="form-message"></span> <br>
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

                            <!-- Checkbox -->
                            <div class="form-group form-confirm">
                                <div class="col-md-9 col-md-offset-3">
                                    <label class="checkbox-inline"> <input type="checkbox"
                                                                           id="inlineCheckbox1" value="agree"> <a>
                                        同意考试条款</a>
                                    </label> <span class="form-message"></span>
                                </div>

                            </div>

                            <!-- Buttons -->
                            <div class="form-group">
                                <!-- Buttons -->
                                <div class="col-md-9 col-md-offset-3">
                                    <button type="button" class="btn" id="btn-reg">注册账号</button>
                                    <button type="reset" class="btn">重置</button>
                                </div>
                            </div>
                        </form>
                        已有账号? <a href="user-login-page">直接登录</a>
                    </div>
                </div>

            </div>
        </div>

    </div>

</div>

<%@include file="common/footer.jsp" %>
<script type="text/javascript" src="resources/js/all.js"></script>
<script type="text/javascript" src="resources/js/register.js"></script>
<!-- Slider Ends -->
