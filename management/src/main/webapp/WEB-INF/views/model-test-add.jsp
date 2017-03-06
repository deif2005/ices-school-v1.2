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
                    <h1><i class="fa fa-file-text-o"></i> 发布模拟考试 </h1>
                </div>
                <div class="page-content">
                    <form id="form-examp-add">
                        <div class="form-line add-update-examname">
                            <span class="form-label"><span class="warning-label">*</span>考试名称：</span>
                            <input id="exam-name" type="text" class="df-input-narrow">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line add-update-exam-paper">
                            <span class="form-label"><span class="warning-label">*</span>选择试卷：</span>
                            <select class="df-input-narrow">
                                <option value="-1" selected="selected">-----------请选择试卷-----------</option>
                                <c:forEach items="${examPaperList }" var="item">
                                    <option value="${item.id }">${item.name }</option>
                                </c:forEach>

                            </select>
                            <span class="form-message"></span>
                        </div>


                        <div class="form-line">
                            <input value="确认发布" type="button" id="exam-add-btn" class="df-submit btn btn-info">
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

<script type="text/javascript" src="resources/js/add-model-test.js"></script>
