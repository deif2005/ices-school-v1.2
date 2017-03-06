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
                    <h1><i class="fa fa-bar-chart-o"></i> 知识分类管理 </h1>
                </div>
                <div class="page-content">
                    <div id="question-filter">
                        <dl id="question-filter-field">
                            <dt>
                                题库：
                            </dt>
                            <dd>
                                <c:choose>
                                    <c:when test="${fieldId == 0 }">
                                        <span data-id="0" class="label label-info">全部</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span data-id="0">全部</span>
                                    </c:otherwise>
                                </c:choose>


                                <c:forEach items="${fieldList }" var="item">

                                    <c:choose>
                                        <c:when test="${fieldId == item.fieldId }">
                                            <span data-id="${item.fieldId}"
                                                  class="label label-info">${item.fieldName}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span data-id="${item.fieldId }">${item.fieldName }</span>
                                        </c:otherwise>
                                    </c:choose>

                                </c:forEach>
                            </dd>
                        </dl>
                        <%-- <div class="page-link-content">
                            <ul class="pagination pagination-sm">${pageStr}</ul>
                        </div> --%>
                    </div>


                    <div id="field-list">
                        <div class="table-controller">
                            <div class="btn-group table-controller-item" style="float:left">
                                <button class="btn btn-default btn-sm" id="add-knowledge-modal-btn">
                                    <i class="fa fa-plus-square"></i> 添加知识分类
                                </button>
                            </div>
                        </div>
                        <table class="table-striped table">
                            <thead>
                            <tr>
                                <td>ID</td>
                                <td>知识分类名</td>
                                <td>属于题库</td>
                                <td>描述</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pointList }" var="item">
                                <tr>

                                    <td>${item.pointId }</td>
                                    <td>${item.pointName }</td>
                                    <td>${item.fieldName }</td>
                                    <td>${item.memo }</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.removeable }">
                                                <span class="delete-btn btn-sm btn-danger" data-id="${item.pointId }"><i
                                                        class="ace-icon fa fa-trash-o"></i></span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="btn-sm" data-id="${item.pointId }" disabled="disabled"
                                                      title="无法删除"><i class="ace-icon fa fa-ban"></i></span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                            <tfoot></tfoot>
                        </table>
                    </div>
                    <div id="page-link-content">
                        <ul class="pagination pagination-sm">${pageStr}</ul>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="add-knowledge-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h6 class="modal-title" id="myModalLabel">添加知识分类</h6>
            </div>
            <div class="modal-body">
                <form id="knowledge-add-form" style="margin-top:40px;" action="admin/common/point-add">
                    <div class="form-line form-field" style="display: block;">
                        <span class="form-label"><span class="warning-label">*</span>默认题库：</span>
                        <select id="job-type-input-select" class="df-input-narrow">
                            <!-- <option value="-1">--请选择--</option> -->
                            <c:forEach items="${fieldList }" var="item">
                                <option value="${item.fieldId }">${item.fieldName }</option>
                            </c:forEach>
                        </select>
                        <span class="form-message"></span>
                        <br>
                    </div>
                    <div class="form-line form-knowledge-name" style="display: block;">
                        <span class="form-label"><span class="warning-label">*</span>名称：</span>
                        <input type="text" class="df-input-narrow" id="name">
                        <span class="form-message"></span>
                        <br>
                    </div>
                    <div class="form-line form-knowledge-desc" style="display: block;">
                        <span class="form-label"><span class="warning-label">*</span>描述：</span>
                        <input type="text" class="df-input-narrow" id="password">
                        <span class="form-message"></span>
                        <br>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭窗口
                </button>
                <button id="add-knowledge-btn" type="button" class="btn btn-primary">
                    确定添加
                </button>
            </div>
        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->

<!-- Javascript files -->
<script type="text/javascript" src="resources/js/point-list.js"></script>
<script type="text/javascript" src="resources/js/add-point.js"></script>
<script>
    $(function () {
        $("#add-knowledge-modal-btn").click(function () {
            $("#add-knowledge-modal").modal({
                backdrop: true,
                keyboard: true
            });

            $("#job-type-input-select").val($("#question-filter-field .label-info").data("id"));


        });
    });
</script>
