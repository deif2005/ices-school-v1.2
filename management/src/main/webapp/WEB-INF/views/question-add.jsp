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
                    <h1><i class="fa fa-pencil-square-o"></i> 添加试题 </h1>
                </div>
                <div class="page-content">
                    <form id="question-add-form">
                        <div class="form-line question-type" id="question-type">
                            <span class="form-label"><span class="warning-label">*</span>试题类型：</span>
                            <select id="question-type-select" class="df-input-narrow">

                                <option value="1">单选题</option>

                                <option value="2">多选题</option>

                                <option value="3">判断题</option>

                                <option value="4">填空题</option>

                                <option value="5">简答题</option>

                                <option value="6">论述题</option>

                                <option value="7">分析题</option>

                            </select><span class="form-message"></span>
                        </div>
                        <div class="form-line question-knowledge">
                            <span class="form-label"><span class="warning-label">*</span>知识分类：</span>

                            <div>
                                <div class="clearfix">
                                    <div id="aq-course1" style="padding:0px;float:left; width:60%;">
                                        <select id="field-select" class="df-input-narrow" style="width:100%;">
                                            <option>请选择</option>
                                            <c:forEach items="${fieldList }" var="item">
                                                <option value="${item.fieldId }">${item.fieldName }</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <br/>

                                    <div id="aq-course2" style="padding:0px;width:60%; margin-top: 10px">
                                        <select id="point-from-select" class="df-input-narrow" size="4"
                                                style="width:100%;">
                                        </select>
                                    </div>
                                    <button id="add-point-btn" class="btn btn-primary btn-xs">选择知识分类</button>
                                </div>


                                <div id="kn-selected"
                                     style="padding-left:0px;text-align:left;margin-bottom:20px; margin-top: 20px">
                                    已选择:<br/>
                                    <select id="point-to-select" class="df-input-narrow" size="4" style="width:60%;">
                                    </select>
                                    <br/>
                                    <button id="del-point-btn" class="btn btn-danger btn-xs">删除知识分类</button>
                                    <button id="remove-all-point-btn" class="btn btn-warning btn-xs">清除列表</button>
                                    <p style="font-size:12px;color:#AAA;">您可以从上面选择4个知识分类</p>
                                </div>

                            </div>
                            <span class="form-message"></span>
                        </div>

                        <div class="form-line question-content" style="height: 200px">
                            <span class="form-label"><span class="warning-label">*</span>试题内容：</span>
                            <%--<textarea class="add-question-ta"></textarea>--%>
                            <%--<div class="col-md-8">--%>
                            <script type="text/plain" id="quesContent" style="height:100px; width:100%"></script>
                            <%--</div>--%>
                        </div>
                        <div class="form-line form-question-opt" style="display: block;">
                            <span class="form-label"><span class="warning-label">*</span>选项：</span>

                            <div class="add-opt-items">


                                <div class="add-opt-item new-add-opt-item"><label class="que-opt-no">A</label><br/>
                                    <script type="text/plain" class="form-question-opt-item" id="addOptItemA"
                                            style="height:50px;width:100%"></script>
                                </div>
                                <div class="add-opt-item new-add-opt-item"><label class="que-opt-no">B</label><br/>
                                    <script type="text/plain" class="form-question-opt-item" id="addOptItemB"
                                            style="height:50px;width:100%"></script>
                                </div>
                                <%--<div class="add-opt-item new-add-opt-item"><label class="que-opt-no">C</label>--%>
                                <%--<span><i class="small-icon ques-remove-opt fa fa-minus-square"title="删除此选项"></i></span>--%>
                                <%--<br/>--%>
                                <%--<script type="text/plain"  class="form-question-opt-item"  id="addOptItemC"  style="height:50px;"></script>--%>
                                <%--</div>--%>

                                <%--<span class="add-opt-item"><label class="que-opt-no">A</label>--%>
                                <%--<input type="text" class="df-input-narrow form-question-opt-item">--%>
                                <%--<span class="add-img add-opt-img">添加图片</span>--%>
                                <%--</span>--%>

                                <%--<span class="add-opt-item"><label class="que-opt-no">B</label>--%>
                                <%--<input type="text" class="df-input-narrow form-question-opt-item">--%>
                                <%--<span class="add-img add-opt-img">添加图片</span>--%>
                                <%--</span>--%>
                                <%--<span class="add-opt-item"><label class="que-opt-no">C</label>--%>
                                <%--<input type="text" class="df-input-narrow form-question-opt-item">--%>
                                <%--<span class="add-img add-opt-img">添加图片</span> <span><i--%>
                                <%--class="small-icon ques-remove-opt fa fa-minus-square"--%>
                                <%--title="删除此选项"></i></span>--%>
                                <%--</span>--%>
                                <%--<span class="add-opt-item"><label class="que-opt-no">D</label>--%>
                                <%--<input type="text" class="df-input-narrow form-question-opt-item">--%>
                                <%--<span class="add-img add-opt-img">添加图片</span> <span><i--%>
                                <%--class="small-icon ques-remove-opt fa fa-minus-square"--%>
                                <%--title="删除此选项"></i></span>--%>
                                <%--</span>--%>


                            </div>
                            <!--	<div class="small-icon" id="ques-add-opt" title="娣诲姞閫夐」"></div>-->
                            <span id="ques-add-opt"><i class="small-icon fa fa-plus-square" title="添加选项"></i></span>
                            <br>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line form-question-answer1 correct-answer" style="display: block;">
                            <span class="form-label"><span class="warning-label">*</span>正确答案：</span>
                            <select class="df-input-narrow">
                                <option value="A">A</option>
                                <option value="B">B</option>
                                <option value="C">C</option>
                                <option value="D">D</option>
                            </select><span class="form-message"></span>
                        </div>
                        <div class="form-line form-question-answer-muti correct-answer" style="display: none;">
                            <span class="form-label"><span class="warning-label">*</span>正确答案：</span>

									<span class="muti-opt-item">
										<input type="checkbox" value="A">
										<label class="que-opt-no">A</label>
										<br>
									</span>
									<span class="muti-opt-item">
										<input type="checkbox" value="B">
										<label class="que-opt-no">B</label>
										<br>
									</span>
									<span class="muti-opt-item">
										<input type="checkbox" value="C">
										<label class="que-opt-no">C</label>
										<br>
									</span>
									<span class="muti-opt-item">
										<input type="checkbox" value="D">
										<label class="que-opt-no">D</label>
										<br>
									</span>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line form-question-answer-boolean correct-answer" style="display: none;">
                            <span class="form-label"><span class="warning-label">*</span>正确答案：</span>
                            <select class="df-input-narrow">
                                <option value="T">正确</option>
                                <option value="F">错误</option>
                            </select><span class="form-message"></span>
                        </div>
                        <div class="form-line correct-answer form-question-answer-text" style="display: none;">
                            <span class="form-label form-question-answer-more"><span class="warning-label">*</span>参考答案：</span>
                            <textarea class="add-question-ta"></textarea> <span class="form-message"></span>
                            <br>

                        </div>
                        <div class="form-line form-question-reference" style="display: block;">
                            <span class="form-label"><span class="warning-label"></span>来源：</span>
                            <input type="text" class="df-input-narrow"><span class="form-message"></span>
                            <br>
                        </div>
                        <div class="form-line form-question-examingpoint" style="display: block;">
                            <span class="form-label"><span class="warning-label"></span>考点：</span>
                            <input type="text" class="df-input-narrow"><span class="form-message"></span>
                            <br>
                        </div>
                        <div class="form-line form-question-keyword" style="display: block;">
                            <span class="form-label"><span class="warning-label"></span>关键字：</span>
                            <input type="text" class="df-input-narrow"><span class="form-message"></span>
                            <br>
                        </div>
                        <div class="form-line form-question-analysis" style="display: block;">
                            <span class="form-label"><span class="warning-label"></span>题目解析：</span>
                            <%--<textarea class="add-question-ta"></textarea><span class="form-message"></span>--%>
                            <script type="text/plain" id="questionAnalysis" style="height:100px;"></script>
                            <span class="form-message"></span>
                            <br>

                        </div>

                        <div class="form-line">
                            <input id="btn-save" value="保存" type="submit" class="df-submit btn btn-info">

                        </div>
                    </form>

                </div>
                <div class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button>
                                <h4 class="modal-title">图片上传工具</h4>
                            </div>
                            <div class="modal-body">
                                <div id="add-question-img-dialog" title="图片上传工具">
                                    <form>
                                        <div class="form-line img-destination">
                                            <span class="form-label">添加至：</span>
                                            <label></label>
                                            <input type="hidden" value=""/>
                                        </div>
                                        <div class="form-line add-update-quetstionfile">
                                            <span class="form-label">上传图片：</span>

                                            <div id="div-file-list">
                                            </div>
                                            <div class="form-line" id="uploadify"></div>
                                            <span class="form-message">请上传png、jpg图片文件，且不能大于100KB。为了使得图片显示正常，请上传的图片长宽比例为2:1</span>
                                        </div>
                                    </form>

                                </div>
                            </div>

                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
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
<script type="text/javascript" src="resources/js/uploadify/jquery.uploadify3.1Fixed.js"></script>
<script type="text/javascript" src="resources/js/question-upload-img.js"></script>
<script type="text/javascript" src="resources/js/field-2-point.js"></script>
<script type="text/javascript" src="resources/js/question-add.js"></script>

<!-- Bootstrap JS -->
<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
