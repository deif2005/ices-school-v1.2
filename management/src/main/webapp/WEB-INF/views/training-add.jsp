<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="min-height:500px;">

        <div class="row">
            <%--<div class="col-xs-2" id="left-menu">
                &lt;%&ndash;<c:import url="/common-page/left-menu?topMenuId=${topMenuId}&leftMenuId=${leftMenuId}"&ndash;%&gt;
                          &lt;%&ndash;charEncoding="UTF-8"/>&ndash;%&gt;
                    <div id="sidebar" class="nav-collapse " style="overflow: hidden;" tabindex="5000">
                    </div>
            </div>--%>
            <%@include file="common/left-menu.jsp" %>
            <div class="col-xs-10" id="right-content">
                <div class="page-header">
                    <h1><i class="fa fa-file-text-o"></i> 发布培训 </h1>
                </div>
                <div class="page-content">
                    <form id="form-training-add">
                        <div class="form-line add-update-training-name">
                            <span class="form-label"><span class="warning-label">*</span>培训名称：</span>
                            <input id="exam-name" type="text" class="df-input-narrow">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line add-update-training-desc">
                            <span class="form-label"><span class="warning-label"></span>培训描述：</span>
                            <textarea id="exam-name" rows="8" style="width:600px;"></textarea>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line add-update-training-type" style="display:none;">
                            <span class="form-label"><span class="warning-label">*</span>私有培训：</span>
                            <input id="training-type" type="checkbox" class="df-input-narrow" checked="checked">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line add-update-training-field">
                            <span class="form-label"><span class="warning-label">*</span>培训专业：</span>
                            <select class="df-input-narrow">
                                <option value="-1">------请选择培训专业------</option>
                                <c:forEach items="${fieldList }" var="item">
                                    <option value="${item.fieldId }">${item.fieldName }</option>
                                </c:forEach>

                            </select>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-line form-training-duration ">
                            <span class="form-label"><span class="warning-label">*</span>失效日期：</span>
                            <input id="training-exp-date" type="text" class="df-input-narrow">
                            <span class="form-message"></span>
                        </div>

                        <div class="form-line">
                            <input value="确认发布" type="button" id="training-add-btn" class="df-submit btn-info btn">
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
<script type="text/javascript" src="resources/js/add-training.js"></script>

<script type="text/javascript">
    $(function () {
        $("#training-exp-date").datepicker();
        $("#training-exp-date").datepicker("option", "dateFormat", "yy-mm-dd");
    });
</script>
