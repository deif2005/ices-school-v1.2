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
                    <h1><i class="fa fa-file-text-o"></i> 创建新试卷 </h1>
                </div>
                <div class="page-content">
                    <form id="form-exampaper-add">
                        <div class="form-line add-update-exampapername">
                            <span class="form-label"><span class="warning-label">*</span>试卷名称：</span>
                            <input type="text" class="df-input-narrow">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line add-update-exampaper-type">
                            <span class="form-label"><span class="warning-label">*</span>试卷类型：</span>
                            <select class="df-input-narrow" id="exampaperType">
                                <option value="-1">------请选择试卷类型（专业）------</option>
                                <c:forEach items="${fieldList }" var="item">
                                    <option value="${item.fieldId }">${item.fieldName }</option>
                                </c:forEach>

                            </select>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line add-update-exampaper-creat-type">
                            <span class="form-label"><span class="warning-label">*</span>组卷方式：</span>
                            <select class="df-input-narrow">
                                <option value="2">自动组卷</option>
                                <option value="1">手动组卷</option>
                                <option value="3">word试卷</option>
                            </select>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line" id="fileDiv" style="display: none">
                            <input type="file" accept="application/msword" onchange="uploadFile(this)"
                                   id="wordFile"><span id="form-message">请选择doc格式文件</span>
                            <input type="hidden" id="fileName">
                        </div>
                        <div class="form-line add-update-pass-point">
                            <span class="form-label"><span class="warning-label">*</span>及格分数：</span>
                            <input type="text" class="df-input-narrow">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line add-update-duration">
                            <span class="form-label"><span class="warning-label">*</span>时长(分钟)：</span>
                            <input type="text" class="df-input-narrow">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line add-update-types" style="display: block;">
                            <span class="form-label"><span class="warning-label">*</span>题型：</span>
                            <fieldset>
                                <legend>
                                    选择题型分布
                                </legend>
										<span class="add-ques-type">
											<label>单选题</label>
											<input type="hidden" class="ques-id" value="1">
											<input type="text" class="df-input-narrow add-ques-amount">
											<span>道</span>
											<label>每道题</label>
											<input type="text" class="df-input-narrow add-ques-score">
											<span>分</span>
										</span>
                                <br>
										<span class="add-ques-type">
											<label>多选题</label>
											<input type="hidden" class="ques-id" value="2">
											<input type="text" class="df-input-narrow add-ques-amount">
											<span>道</span>
											<label>每道题</label>
											<input type="text" class="df-input-narrow add-ques-score">
											<span>分</span>
										</span>
                                <br>
										<span class="add-ques-type">
											<label>判断题</label>
											<input type="hidden" class="ques-id" value="3">
											<input type="text" class="df-input-narrow add-ques-amount">
											<span>道</span>
											<label>每道题</label>
											<input type="text" class="df-input-narrow add-ques-score">
											<span>分</span>
										</span>
                                <br>
										<span class="add-ques-type">
											<label>填空题</label>
											<input type="hidden" class="ques-id" value="4">
											<input type="text" class="df-input-narrow add-ques-amount">
											<span>道</span>
											<label>每道题</label>
											<input type="text" class="df-input-narrow add-ques-score">
											<span>分</span>
										</span>
                                <br>
										<span class="add-ques-type">
											<label>简答题</label>
											<input type="hidden" class="ques-id" value="5">
											<input type="text" class="df-input-narrow add-ques-amount">
											<span>道</span>
											<label>每道题</label>
											<input type="text" class="df-input-narrow add-ques-score">
											<span>分</span>
										</span>
                                <br>
										<span class="add-ques-type">
											<label>论述题</label>
											<input type="hidden" class="ques-id" value="6">
											<input type="text" class="df-input-narrow add-ques-amount">
											<span>道</span>
											<label>每道题</label>
											<input type="text" class="df-input-narrow add-ques-score">
											<span>分</span>
										</span>
                                <br>
										<span class="add-ques-type">
											<label>分析题</label>
											<input type="hidden" class="ques-id" value="7">
											<input type="text" class="df-input-narrow add-ques-amount">
											<span>道</span>
											<label>每道题</label>
											<input type="text" class="df-input-narrow add-ques-score">
											<span>分</span>
										</span>
                                <br>
                            </fieldset>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line add-total-point">
                            <span class="form-label"><span class="warning-label">*</span>总分：</span>
                            <input id="total-point" type="text" class="df-input-narrow" disabled="disbaled">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line add-update-exampaper-scope" style="display: block;">
                            <span class="form-label"><span class="warning-label">*</span>知识分类：</span>
                            <fieldset>
                                <legend>
                                    知识分类范围
                                </legend>
                                <div>
                                    <div class="clearfix">
                                        <div id="aq-course1" style="padding:0px;width:60%;">
                                            <select id="field-select" class="df-input-narrow"
                                                    style="width:100%;">
                                                <option>请选择</option>
                                                <c:forEach items="${fieldList }" var="item">
                                                    <option value="${item.fieldId }">${item.fieldName }</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div id="aq-course2" style="padding:0px; width:60%; margin-top: 10px">
                                            <select id="point-from-select" class="df-input-narrow" size="4"
                                                    style="width:100%;">
                                            </select>
                                        </div>
                                    </div>

                                    <button id="add-point-btn" class="btn btn-primary btn-xs">选择知识分类</button>

                                    <div id="kn-selected"
                                         style="padding-left:0px;text-align:left;margin-bottom:20px; margin-top: 20px">
                                        已选择:<br/>
                                        <select id="point-to-select" class="df-input-narrow" size="4"
                                                style="width:60%;">
                                        </select>
                                        <br>
                                        <button id="del-point-btn" class="btn btn-danger btn-xs">删除知识分类</button>
                                        <button id="remove-all-point-btn" class="btn btn-warning btn-xs">清除列表</button>
                                        <p style="font-size:12px;color:#AAA;">您可以从上面选择4个知识分类</p>
                                    </div>
                                </div>
                            </fieldset>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line">
                            <input value="下一步" type="submit" class="df-submit btn btn-info">
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->
<script src="resources/ueditor/ueditor.config.js"></script>
<script src="resources/ueditor/ueditor.all.js"></script>
<script src="resources/ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="resources/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
<script src="resources/ueditor/kityformula-plugin/getKfContent.js"></script>
<script src="resources/ueditor/kityformula-plugin/defaultFilterFix.js"></script>

<!-- Javascript files -->
<script type="text/javascript" src="resources/js/field-2-point.js"></script>
<script type="text/javascript" src="resources/js/exampaper-add.js"></script>
