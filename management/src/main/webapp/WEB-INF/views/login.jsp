<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<div class="content" style="margin-bottom: 100px;background: rgb(77,191,217);margin-top:0;">

    <div class="container" id="admin-login-bg2">
        <div class="row" id="admin-login-bg">
            <div class="col-md-5 col-md-offset-7">
                <div class="lrform">
                    <h4 style="align:center">登录考试管理系统</h4>
                    <hr>
                    <div class="form">
                        <!-- Login form (not working)-->
                        <form class="form-horizontal" action="j_spring_security_check" method="post">
                            <!-- Username -->
                            <div class="form-group">
                                <div class="col-md-10 col-md-offset-1">
                                    <input type="text" class="form-control" name="j_username" placeholder="帐号">
                                </div>
                            </div>
                            <!-- Password -->
                            <div class="form-group">
                                <div class="col-md-10 col-md-offset-1">
                                    <input type="password" class="form-control" name="j_password" placeholder="密码">
                                </div>
                            </div>
                            <!-- Buttons -->
                            <div class="form-group">
                                <!-- Buttons -->
                                <div class="col-md-10 col-md-offset-1">
                                    <button type="submit" class="btn btn-primary col-md-5" >
                                        登  录
                                    </button>
                                    <button type="reset" class="btn btn-primary col-md-5 col-md-offset-2">
                                        重  置
                                    </button>
                                </div>
                                <div class="col-md-9 col-md-offset-3">
                                    <span class="form-message">${result}</span>
                                </div>
                            </div>
                        </form>
                        <i class="fa fa-info" style="align:center"></i>
                        通过教师/管理员账号登录系统
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

<!-- Javascript files -->
