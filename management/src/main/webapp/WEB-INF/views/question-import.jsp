<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="min-height:500px;">

        <div class="row">
            <%--<div class="col-xs-2" id="left-menu">
                <div id="sidebar" class="nav-collapse " style="overflow: hidden;" tabindex="5000">
                </div>
            </div>--%>
            <%@include file="common/left-menu.jsp" %>
            <div class="col-xs-10" id="right-content">
                <div class="page-header">
                    <h1><i class="fa fa-cloud-upload"></i> 导入试题 </h1>
                </div>
                <div class="page-content" style="padding-top:20px;">
                    <form id="from-question-import" action="secure/question-import">
                        <div class="form-line upload-question-group">
                            <span class="form-label">选择题库：</span>
                            <select class="df-input-narrow">
                                <option value="0">-- 请选择 --</option>
                                <c:forEach items="${fieldList }" var="item">
                                    <option value="${item.fieldId }">${item.fieldName }</option>
                                </c:forEach>
                            </select>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line template-download">
                            <span class="form-label">下载模板：</span>
                            <a href="resources/template/question.xlsx"
                               style="color:rgb(22,22,22);text-decoration: underline;">点击下载</a>
                        </div>
                        <div class="form-line control-group">
                            <span class="form-label"><span class="warning-label">*</span>上传文件：</span>
                            <div class="controls file-form-line">
                                <div>
                                    <div id="div-file-list"></div>
                                    <!-- 用来作为文件队列区域 -->
                                    <div id="fileQueue"></div>
                                    <div id="uploadify"></div>
                                </div>
                                <span class="help-inline form-message"></span>
                            </div>
                        </div>
                        <div class="form-line">
                            <input value="提交" type="submit" class="df-submit btn btn-info">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->

<!-- Javascript files -->
<script type="text/javascript" src="resources/js/uploadify/jquery.uploadify3.1Fixed.js"></script>
<script type="text/javascript" src="resources/js/question-import.js"></script>

<!-- Bootstrap JS -->
<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
