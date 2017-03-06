<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<div class="content" style="margin-bottom: 100px;min-height: 400px;">

    <div class="container" id="admin-login-bg2">
        <div class="row" id="admin-login-bg">
            <div class="col-md-5 col-md-offset-7">
                <div class="lrform">
                    <h5>快速考试入口</h5>
                    <hr>
                    <div class="form">
                        <!-- Login form (not working)-->
                        <form class="form-horizontal" action="j_spring_security_check" method="post">
                            <!-- Username -->
                            <div class="form-group">
                                <%--<label class="control-label col-md-3" for="username">准考证号</label>--%>

                                <div class="col-md-10 col-md-offset-1">
                                    <input type="text" class="form-control" name="j_seri_no" placeholder="准考证号">
                                    <input type="hidden" value="1" name="j_flag">
                                </div>
                            </div>

                            <!-- Buttons -->
                            <div class="form-group">
                                <!-- Buttons -->
                                <div class="col-md-10 col-md-offset-1">
                                    <button type="submit" class="btn btn-primary col-md-12">
                                        开始考试
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
<!--%@include file="common/footer.jsp" %-->

<!-- Slider Ends -->
