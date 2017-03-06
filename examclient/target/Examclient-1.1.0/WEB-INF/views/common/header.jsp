<%@ page import="java.util.List" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
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
        }else if(list.length==2){
            request.setAttribute("topMenuId", list[1]);
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <title>考试客户端</title>
    <meta name="keywords" content="">
    <link rel="shortcut icon" href="resources/images/favicon.ico"/>
    <link href="resources/bootstrap/css/bootstrap-huan.css" rel="stylesheet">
    <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
    <link href="resources/css/exam.css" rel="stylesheet">
    <link href="resources/chart/morris.css" rel="stylesheet">
    <link href="resources/css/clientstyle.css" rel="stylesheet">
    <script type="text/javascript" src="resources/js/jquery/jquery-1.9.0.min.js"></script>
    <script type="text/javascript" src="resources/js/all.js"></script>
</head>
<body style="overflow: hidden">