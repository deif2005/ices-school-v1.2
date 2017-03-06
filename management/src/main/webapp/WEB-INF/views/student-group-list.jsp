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
                    <h1><i class="fa fa-bar-chart-o"></i> 学生分组管理 </h1>
                </div>
                <div class="page-content">
                    <div id="dep-list">
                        <div class="table-controller">
                            <div class="btn-group table-controller-item" style="float:left">
                                <button class="btn btn-default btn-sm" id="add-group-modal-btn">
                                    <i class="fa fa-plus-square"></i> 添加分组
                                </button>
                            </div>
                        </div>
                        <table class="table-striped table">
                            <thead>
                            <tr>
                                <td>ID</td>
                                <td>分组名</td>
                                <td>创建时间</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${groupList }" var="item">
                                <tr>
                                    <td><span class="r-id">${item.groupId }</span></td>
                                    <td><span class="r-name">${item.groupName }</span></td>
                                    <td><fmt:formatDate value="${item.createTime }"
                                                        pattern="yyyy-MM-dd HH:mm"/></td>
                                    <td>
                                        <span class="r-update-btn btn-sm btn-success"
                                              data-id="${item.groupId }">修改</span>
                                        <span class="delete-btn btn-sm btn-danger" data-id="${item.groupId }"><i
                                                class="ace-icon fa fa-trash-o"></i></span>
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

<div class="modal fade" id="add-group-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h6 class="modal-title" id="myModalLabel">添加分组</h6>
            </div>
            <div class="modal-body">
                <form id="dep-add-form" style="margin-top:40px;" action="admin/common/dep-add">
                    <div class="form-line form-dep-name" style="display: block;">
                        <span class="form-label"><span class="warning-label">*</span>名称：</span>
                        <input type="text" class="df-input-narrow" id="groupName">
                        <span class="form-message"></span>
                        <br>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭窗口
                </button>
                <button id="add-group-btn" type="button" class="btn btn-primary">
                    确定添加
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="update-group-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h6 class="modal-title" id="myModalLabelupdate">修改分组</h6>
            </div>
            <div class="modal-body">
                <div class="form-line form-dep-name" style="display: block;">
                    <span class="form-label"><span class="warning-label">*</span>名称：</span>
                    <input type="hidden" id="groupId">
                    <input type="text" class="df-input-narrow" id="groupNameEdit">
                    <span class="form-message"></span>
                    <br>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭窗口
                </button>
                <button id="edit-group-btn" type="button" class="btn btn-primary">
                    确定修改
                </button>
            </div>
        </div>
    </div>
</div>

<%@include file="common/footer.jsp" %>
<!-- Slider Ends -->

<!-- Javascript files -->
<script type="text/javascript" src="resources/js/student-group-list.js"></script>
<script>
    $(function () {
        $("#add-group-modal-btn").click(function () {
            $(".form-message").empty();
            $("#groupName").val("");
            $("#add-group-modal").modal({
                backdrop: true,
                keyboard: true
            });

        });
    });
    $(function () {
        $(".r-update-btn").click(function () {
            $(".form-message").empty();
            $("#groupNameEdit").val($(this).parent().parent().find(".r-name").text().trim());
            $("#groupId").val($(this).parent().parent().find(".r-id").text().trim());
            $("#update-group-modal").modal({
                backdrop: true,
                keyboard: true
            });

        });
    });
</script>
