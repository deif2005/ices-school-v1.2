<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="min-height:500px;">

        <div class="row">
            <%-- <div class="col-xs-2" id="left-menu">
                 <div id="sidebar" class="nav-collapse " style="overflow: hidden;" tabindex="5000">
                 </div>
             </div>--%>
            <%@include file="common/left-menu.jsp" %>
            <div class="col-xs-10" id="right-content">
                <div class="page-header">
                    <h1><i class="fa fa-list-ul"></i> 试卷管理 </h1>
                </div>
                <div class="page-content">
                    <div id="question-filter">
                        <dl id="question-filter-pagetype">
                            <dt>
                                分类：
                            </dt>
                            <dd>
										<span data-id="0" <c:if test="${paperType == '0' }">
                                            class="label label-info"
                                        </c:if>>全部</span>
                                <c:forEach items="${fieldList }" var="item">
											<span data-id="${item.fieldId }" <c:if test="${paperType == item.fieldId }">
                                                class="label label-info"
                                            </c:if>
                                                    >${item.fieldName }</span>
                                </c:forEach>
                            </dd>
                        </dl>

                    </div>
                    <div class="table-search table-controller-item"
                         style="float: left; width: 200px;">
                        <div class="input-group search-form">
                            <input type="text" class="form-control" placeholder="搜索试卷" value="${searchStr }"
                                   id="txt-search">
									<span class="input-group-btn">
										<button class="btn btn-sm btn-default" type="button"
                                                id="btn-search">
                                            <i class="fa fa-search"></i>搜索
                                        </button> </span>
                        </div>
                    </div>
                    <div id="question-list">
                        <table class="table-striped table">
                            <thead>
                            <tr>
                                <td></td>
                                <td>ID</td>
                                <td>试卷名称</td>
                                <td>时长</td>
                                <td>类别</td>
                                <td>创建人</td>
                                <td>状态</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${paper }" var="item">
                                <tr>
                                    <td>
                                        <input type="checkbox" value="${item.id }">
                                    </td>
                                    <td>${item.id }</td>
                                    <td><a href="<%=list[1]%>/exampaper/exampaper-preview/${item.id }" target="_blank"
                                           title="预览" class="td-paper-name">${item.name }</a></td>
                                    <td><span class="td-paper-duration">${item.duration}</span>分钟</td>
                                    <td>
                                        <c:if test="${item.paperType == '1' }">
														<span class="td-paper-type" data-id="${item.paperType}">
															随机组卷
														</span>
                                        </c:if>
                                        <c:if test="${item.paperType == '2' }">
														<span class="td-paper-type" data-id="${item.paperType}">
														模拟考试
														</span>
                                        </c:if>
                                        <c:if test="${item.paperType == '3' }">
														<span class="td-paper-type" data-id="${item.paperType}">
														专家试卷
														</span>
                                        </c:if>
                                    </td>
                                    <td>${item.creator }</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.answerSheet == null }">

                                                <span class="label-default label-sm label">未完成</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="label-success label-sm label">已完成</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.status == 0 }">
                                                <a href="<%=list[1]%>/exampaper/exampaper-edit/${item.id}"
                                                   class="btn-sm btn-info" title="修改内容"><i
                                                        class="ace-icon fa fa-pencil bigger-120"></i></a>
                                                <!-- <a class="change-property simple-btn" >修改属性</a>
                                                <a class="publish-paper simple-btn">上线</a> -->
                                                <a class="delete-paper btn-sm btn-danger" title="删除"><i
                                                        class="ace-icon fa fa-trash-o"></i></a>
                                            </c:when>
                                            <%-- <c:when test="${item.status == 1 }">
                                                 <a class="offline-paper simple-btn">下线</a>
                                             </c:when>--%>
                                        </c:choose>
                                        <a href="admin/exampaper/create-doc-${item.id}"
                                           class="export-paper btn-sm btn-success" data-id="${item.id}" title="导出"><i
                                                class="fa fa-download"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                            <tfoot></tfoot>
                        </table>
                        <div class="modal fade" id="change-property-modal" tabindex="-1" role="dialog"
                             aria-labelledby="myLargeModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-hidden="true">&times;</button>
                                        <h6 class="modal-title" id="myModalLabel">修改试卷属性</h6>
                                    </div>
                                    <div class="modal-body">
                                        <form id="question-add-form">
                                            <span id="add-update-exampaperid" style="display:none;"></span>

                                            <div class="form-line add-update-exampapername">
                                                <span class="form-label"><span
                                                        class="warning-label">*</span>试卷名称：</span>
                                                <input type="text" class="df-input-narrow">
                                                <span class="form-message"></span>
                                            </div>
                                            <div class="form-line add-update-duration">
                                                <span class="form-label"><span
                                                        class="warning-label">*</span>时长（分钟）：</span>
                                                <input type="text" class="df-input-narrow">
                                                <span class="form-message"></span>
                                            </div>
                                            <div class="form-line exampaper-type" id="exampaper-type">
                                                <span class="form-label"><span class="warning-label">*</span>分类：</span>
                                                <select id="exampaper-type-select" class="df-input-narrow">

                                                    <option value="1">随机组卷</option>
                                                    <option value="2">模拟考试</option>
                                                    <option value="3">专家试卷</option>
                                                </select><span class="form-message"></span>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
                                        <button id="update-exampaper-btn" type="button" class="btn btn-primary">确定修改
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="page-link-content">
                        <ul class="pagination pagination-sm">${pageStr}</ul>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->

<!-- Javascript files -->
<script type="text/javascript" src="resources/js/exampaper-list.js?v=1"></script>
