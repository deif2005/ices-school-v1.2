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
                    <h1><i class="fa fa-file-text-o"></i> 发布考试 </h1>
                </div>
                <div class="page-content">
                    <form id="form-examp-add">
                        <div class="form-line add-update-examname">
                            <span class="form-label"><span class="warning-label">*</span>考试名称：</span>
                            <input id="exam-name" type="text" class="df-input-narrow">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line add-update-exam-type">
                            <span class="form-label"><span class="warning-label">*</span>考试类型：</span>
                            <select class="df-input-narrow">
                                <option value="2">公开考试</option>
                                <option value="1">私有考试</option>
                            </select>
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
                        <div class="form-line add-update-group-list">
                            <span class="form-label"><span class="warning-label">*</span>参考人员：</span>
                            <fieldset>
                                <legend>
                                    请选择
                                </legend>
                                <c:forEach items="${groupList }" var="item">
                                    <label><input type="checkbox" value="${item.groupId }"/></label>
                                    <label>${item.groupName }</label>
                                    <br>
                                </c:forEach>
                            </fieldset>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line form-exam-duration">
                            <span class="form-label"><span class="warning-label">*</span>生效日期：</span>
                            <input id="exam-eff-date" type="text" class="df-input-narrow">
                            <select class="df-input-narrow" id="exam-eff-time">
                                <option value="8:00">8:00</option>
                                <option value="9:00">9:00</option>
                                <option value="10:00">10:00</option>
                                <option value="11:00">11:00</option>
                                <option value="12:00">12:00</option>
                                <option value="13:00">13:00</option>
                                <option value="14:00">14:00</option>
                                <option value="15:00">15:00</option>
                                <option value="16:00">16:00</option>
                                <option value="17:00">17:00</option>
                                <option value="18:00">18:00</option>
                                <option value="19:00">19:00</option>
                                <option value="20:00">20:00</option>
                                <option value="21:00">21:00</option>
                                <option value="22:00">22:00</option>
                            </select>
                        </div>
                        <div class="form-line form-exam-duration ">
                            <span class="form-label"><span class="warning-label">*</span>截止日期：</span>
                            <input id="exam-exp-date" type="text" class="df-input-narrow">
                            <select class="df-input-narrow" id="exam-exp-time">
                                <option value="8:00">8:00</option>
                                <option value="9:00">9:00</option>
                                <option value="10:00">10:00</option>
                                <option value="11:00">11:00</option>
                                <option value="12:00">12:00</option>
                                <option value="13:00">13:00</option>
                                <option value="14:00">14:00</option>
                                <option value="15:00">15:00</option>
                                <option value="16:00">16:00</option>
                                <option value="17:00">17:00</option>
                                <option value="18:00">18:00</option>
                                <option value="19:00">19:00</option>
                                <option value="20:00">20:00</option>
                                <option value="21:00">21:00</option>
                                <option value="22:00">22:00</option>
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
<script type="text/javascript" src="resources/js/add-exam.js"></script>

<script type="text/javascript">
    $(function () {
        $("#exam-eff-date, #exam-exp-date").datepicker();
        $("#exam-eff-date, #exam-exp-date").datepicker("option", "dateFormat", "yy-mm-dd");
    });
</script>
