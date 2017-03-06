<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp"%>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="min-height: 400px;margin-top: 50px;">
        <h2>欢迎注册考试</h2>
        <p class="big grey">
            您的账号为<span>${username}</span>
        </p>
        <a href="user-login-page" class="btn btn-success"><i class="fa fa-sign-in"></i> 登录系统</a>
        <hr>
        <div class="link-list">
            <h5>您可以选择访问其他页面</h5>
            <a href="#">主页</a><a href="#">试题练习</a><a href="#">练习历史</a><a href="#">联系我们</a><a href="#">FAQ</a>
        </div>
        <div class="row">

        </div>
    </div>
</div>

<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->
