<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- <%@taglib uri="spring.tld" prefix="spring"%> --%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String servletPath = (String) request.getAttribute("javax.servlet.forward.servlet_path");
    String[] list = servletPath.split("\\/");
    if (list.length > 1) {
        request.setAttribute("role", list[1]);
        if (list.length > 2) {
            request.setAttribute("topMenuId", list[2]);
            if (list.length > 3)
                request.setAttribute("leftMenuId", list[3]);
            else request.setAttribute("leftMenuId", "");
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>考试管理系统</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="keywords" content="">
    <link rel="shortcut icon" href="<%=basePath%>resources/images/favicon.ico"/>
    <link href="resources/bootstrap/css/bootstrap-huan.css" rel="stylesheet">
    <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" type="text/css"/>
    <link href="resources/css/style.css" rel="stylesheet">
    <link href="resources/css/exam.css" rel="stylesheet">
    <link href="resources/chart/morris.css" rel="stylesheet">
    <link href="resources/css/question-add.css" rel="stylesheet">
    <link href="resources/css/examstyle.css" rel="stylesheet">
</head>
<body style="overflow: hidden;">