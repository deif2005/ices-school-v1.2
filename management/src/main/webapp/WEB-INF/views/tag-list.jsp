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
                    <h1><i class="fa fa-bar-chart-o"></i> 标签管理 </h1>
                </div>
                <div class="page-content">
                    <div id="tag-list">
                        <div class="table-controller">
                            <div class="btn-group table-controller-item" style="float:left">
                                <button class="btn btn-default btn-sm" id="add-tag-modal-btn">
                                    <i class="fa fa-plus-square"></i> 添加标签
                                </button>
                            </div>
                        </div>
                        <table class="table-striped table">
                            <thead>
                            <tr>
                                <td></td>
                                <td>ID</td>
                                <td>标签名</td>
                                <td>描述</td>
                                <td>创建时间</td>
                                <td>创建人</td>
                                <td>私有</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${tagList }" var="item">
                                <tr>
                                    <td>
                                            <%-- <c:if test="${item.removeable }">
                                                <input type="checkbox" value="${item.tagId }">
                                            </c:if> --%>
                                    </td>
                                    <td>${item.tagId }</td>
                                    <td>${item.tagName }</td>
                                    <td>${item.memo }</td>
                                    <td><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
                                    <td>${item.creatorName }</td>
                                    <td>${item.privatee }</td>
                                    <td>
                                        <span class="delete-btn btn-sm btn-danger" data-id="${item.tagId }"><i
                                                class="ace-icon fa fa-trash-o"></i></span>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                            <tfoot></tfoot>
                        </table>
                    </div>
                    <div id="page-link-content">

                        <ul class="pagination pagination-sm">
                            ${pageStr}
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="add-tag-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h6 class="modal-title" id="myModalLabel">添加标签</h6>
                </div>
                <div class="modal-body">
                    <form id="tag-add-form" style="margin-top:40px;" action="admin/common/tag-add">
                        <div class="form-line form-tag-name" style="display: block;">
                            <span class="form-label"><span class="warning-label">*</span>名称：</span>
                            <input type="text" class="df-input-narrow" id="name">
                            <span class="form-message"></span>
                            <br>
                        </div>
                        <div class="form-line form-tag-desc" style="display: block;">
                            <span class="form-label"><span class="warning-label">*</span>描述：</span>
                            <input type="text" class="df-input-narrow" id="memo">
                            <span class="form-message"></span>
                            <br>
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        关闭窗口
                    </button>
                    <button id="add-tag-btn" type="button" class="btn btn-primary">
                        确定添加
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->

<!-- Javascript files -->
<script type="text/javascript" src="resources/js/tag-list.js"></script>
<script type="text/javascript" src="resources/js/add-tag.js"></script>
<script>
    $(function () {
        $("#add-tag-modal-btn").click(function () {
            $("#add-tag-modal").modal({
                backdrop: true,
                keyboard: true
            });

        });
    });
</script>
