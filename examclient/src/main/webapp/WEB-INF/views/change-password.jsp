<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="min-height:400px;">

        <div class="row">
            <div class="col-xs-2" id="left-menu">
                <div id="sidebar" class="nav-collapse " style="" tabindex="5000"></div>
            </div>
            <div class="col-xs-10" id="right-content">
                <div class="page-header">
                    <h1><i class="fa fa-wrench"></i> 修改密码 </h1>
                </div>
                <div class="page-content row">
                    <form class="form-horizontal" id="form-change-password" onsubmit="return checkPassword();" action="student/changePwd"
                          style="margin-top:40px;" method="post">

                        <!-- password -->
                        <div class="form-group form-password">
                            <label class="control-label col-md-2" for="password">新密码</label>
                            <div class="col-md-5">
                                <input type="password" class="form-control" id="password" name="password">
                                <span class="form-message"></span>
                            </div>
                        </div>

                        <!-- password-confirm -->
                        <div class="form-group form-password-confirm">
                            <label class="control-label col-md-2" for="password-confirm">确认新密码</label>
                            <div class="col-md-5">
                                <input type="password" class="form-control" id="password-confirm">
                                <span class="form-message"></span>
                            </div>
                        </div>

                        <!-- Buttons -->
                        <div class="form-group">
                            <!-- Buttons -->
                            <div class="col-md-5 col-md-offset-2">
                                <button type="submit" class="btn btn-info" id="btn-reg">
                                    确认修改
                                </button>

                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="common/footer.jsp" %>
<script type="text/javascript">
    function checkPassword(){
        var password=document.getElementById("password").value;
        var repassword=document.getElementById("password-confirm").value;
        if(password==""){
            alert("请输入密码");
            return false;
        }
        if(repassword=="") {
            alert("请输入确认密码");
            return false;
        }
        if(password!=repassword){
            alert("两次密码不一致");
            return false;
        }
        return true;
    }
</script>
<!-- Slider Ends -->
