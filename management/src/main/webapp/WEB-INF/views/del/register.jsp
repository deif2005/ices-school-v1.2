<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="../common/header.jsp" %>
<%@include file="../common/banner.jsp" %>

<div class="content" style="margin-bottom: 100px;">

    <div class="container">
        <div class="row">

            <div class="col-md-12">
                <div class="lrform">
                    <h5>注册账号</h5>
                    <span class="form-message"></span>
                    <div class="form">
                        <!-- Register form (not working)-->
                        <form class="form-horizontal" id="form-create-account"
                              action="user-reg">
                            <!-- Name -->
                            <div class="form-group form-username">
                                <label class="control-label col-md-3" for="name">账号</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="name">
                                    <span class="form-message"></span>
                                </div>

                            </div>
                            <!-- Email -->
                            <div class="form-group form-email">
                                <label class="control-label col-md-3" for="email">email</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="email">
                                    <span class="form-message"></span>
                                </div>

                            </div>
                            <!-- password -->
                            <div class="form-group form-password">
                                <label class="control-label col-md-3" for="password">密码</label>
                                <div class="col-md-9">
                                    <input type="password" class="form-control" id="password">
                                    <span class="form-message"></span>
                                </div>

                            </div>
                            <!-- password-confirm -->
                            <div class="form-group form-password-confirm">
                                <label class="control-label col-md-3" for="password-confirm">确认密码</label>
                                <div class="col-md-9">
                                    <input type="password" class="form-control" id="password-confirm">
                                    <span class="form-message"></span>
                                </div>

                            </div>

                            <div class="form-group form-job-type">
                                <label class="control-label col-md-3">选择专业</label>
                                <div class="col-md-9">
                                    <select class="form-control" id="job-type-input">
                                        <option value="-1">--请选择--</option>
                                        <c:forEach items="${fieldList }" var="item">
                                            <option value="${item.fieldId }">${item.fieldName }</option>
                                        </c:forEach>
                                    </select>
                                    <span class="form-message"></span>
                                </div>

                            </div>

                            <!-- Checkbox -->
                            <div class="form-group form-confirm">
                                <div class="col-md-9 col-md-offset-3">
                                    <label class="checkbox-inline">
                                        <input type="checkbox"
                                               id="inlineCheckbox1" value="agree">
                                        <a> 同意考试条款</a> </label>
                                    <span class="form-message"></span>
                                </div>

                            </div>

                            <!-- Buttons -->
                            <div class="form-group">
                                <!-- Buttons -->
                                <div class="col-md-9 col-md-offset-3">
                                    <button type="submit" class="btn" id="btn-reg">
                                        注册账号
                                    </button>
                                    <button type="reset" class="btn">
                                        重置
                                    </button>
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
<%@include file="../common/footer.jsp" %>

<!-- Slider Ends -->
<script type="text/javascript" src="resources/js/register.js"></script>
