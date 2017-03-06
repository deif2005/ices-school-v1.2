<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>
<div class="content" style="margin-bottom: 100px;min-height: 400px;">
    <div class="container" id="admin-login-bg2">
        <div class="row" id="admin-login-bg">
            <div class="col-md-5 col-md-offset-7">
                <div class="lrform">
                    <h5>登录考试客户端</h5>
                    <hr>
                    <div class="form">
                        <!-- Login form (not working)-->
                        <form class="form-horizontal" action="j_spring_security_check" method="post">
                            <!-- Username -->
                            <div class="form-group">
                               <%-- <label class="control-label col-md-3" for="username">用户名</label>
--%>
                                <div class="col-md-10 col-md-offset-1">
                                    <input type="text" class="form-control" name="j_username"  placeholder="帐号">
                                </div>
                            </div>
                            <!-- Password -->
                            <div class="form-group"><%--
                                <label class="control-label col-md-3" for="password">密码</label>--%>
                                <div class="col-md-10  col-md-offset-1">
                                    <input type="password" class="form-control" name="j_password" placeholder="密码">
                                </div>
                            </div>
                            <!-- Buttons -->
                            <div class="form-group">
                                <!-- Buttons -->
                                <div class="col-md-10 col-md-offset-1">
                                    <button type="submit" class="btn btn-primary col-md-5 ">
                                        登录
                                    </button>
                                    <button type="reset" class="btn btn-primary col-md-5 col-md-offset-2">
                                        重置
                                    </button>
                                    <span class="form-message">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</span>
                                </div>
                            </div>
                        </form>
                        没有账号? <a href="user-register">注册</a>
                    </div>
                </div>

            </div>
        </div>

    </div>

</div>
<%@include file="common/footer.jsp" %>
<script type="text/javascript">
    $(function () {
        sessionStorage.clear();
    })
</script>
<!-- Slider Ends -->
