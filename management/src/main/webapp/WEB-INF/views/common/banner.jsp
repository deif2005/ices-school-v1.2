<%--
  Created by IntelliJ IDEA.
  User: Sugior
  Date: 2016/7/12
  Time: 0:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" %>
<header style="background-color:#19a97b; border:0px;height: 70px;">
    <span style="display:none;" id="rule-role-val"><%=list[1]%></span>

    <div class="container">
        <div class="row">
            <div class="col-xs-5">
                <div class="logo">
                    <img alt="" src="resources/images/White-Logo.png">
                    <%--<h1 class="h4">考试后台管理系统</h1>--%>
                </div>
            </div>
            <div class="col-xs-7" id="login-info">
                <c:choose>
                    <c:when test="${not empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}">
                        <%-- <div id="login-info-user">--%>
                        <ul class="rt_nav">
                            <li>
                                <a href="user-detail/${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}"
                                   id="system-info-account" target="_blank" class="admin_icon">
                                        ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}
                                </a>
                            </li>
                            <li>
                                <a href="j_spring_security_logout" class="quit_icon">安全退出</a>
                            </li>
                        </ul>
                        <%--</div>--%>
                    </c:when>
                    <c:otherwise>
                        <ul class="rt_nav">
                            <%--<li>
                                <a class="set_icon" href="user-register">注册</a>
                            </li>--%>
                            <li>
                                <a class="admin_icon" href="user-login-page">登录</a>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</header>


