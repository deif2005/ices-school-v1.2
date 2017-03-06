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
                    <h1><i class="fa fa-bar-chart-o"></i> 管理员列表 </h1>
                </div>
                <div class="page-content">
                    <div id="news-list">
                        <div class="table-controller">
                            <div class="btn-group table-controller-item" style="float:left">
                                <button class="btn btn-default btn-sm" id="add-news-modal-btn">
                                    <i class="fa fa-plus-square"></i> 发布公告
                                </button>
                            </div>
                        </div>
                        <table class="table-striped table">
                            <thead>
                            <tr>
                                <td>ID</td>
                                <td>标题</td>
                                <td>创建时间</td>
                                <td>创建人</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${newsList }" var="item">
                                <tr>
                                    <td><span class="r-id">${item.newsId }</span></td>
                                    <td><span class="r-name">${item.title }</span></td>
                                    <td>
													<span class="r-createtime">
														<fmt:formatDate value="${item.createTime }"
                                                                        pattern="yyyy-MM-dd HH:mm"/>
													</span>
                                    </td>
                                    <td>
                                        <div class="r-truename">
                                                ${item.creator }
                                        </div>
                                    </td>
                                    <td>
                                         <span class="r-update-btn btn-sm btn-success" data-id="${item.newsId }"
                                               data-depid="${item.newsId }">修改</span>

                                        <span class="delete-btn btn-sm btn-danger" data-id="${item.newsId }">
                                        <i class="ace-icon fa fa-trash-o"></i>
                                        </span>
                                    </td>
                                    <td><span class="r-content" style="display: none;">${item.content }</span></td>

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
</div>


<div class="modal fade" id="add-news-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h6 class="modal-title" id="myModalLabel">发布公告</h6>
            </div>
            <div class="modal-body">
                <form id="teacher-add-form" style="margin-top:40px;" action="admin/add-news">
                    <input style="display: none" id="newsId">
                    <div class="form-line form-news-title" style="display: block;">
                        <span class="form-label"><span class="warning-label">*</span>公告标题：</span>
                        <input type="text" class="df-input-narrow" id="name-add" maxlength="50">
                        <span class="form-message"></span>
                        <br>
                    </div>
                    <div class="form-line form-news-content" style="display: block;">
                        <span class="form-label"><span class="warning-label">*</span>公告内容：</span>
                        <textarea rows="12" cols="50" id="news-content"></textarea>
                        <span class="form-message"></span>
                        <br>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭窗口
                </button>
                <button id="add-news-btn" data-action="admin/add-news" data-group="0" type="button"
                        class="btn btn-primary">
                    确定添加
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="update-news-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h6 class="modal-title">修改公告</h6>
            </div>
            <div class="modal-body">
                <form id="new-update-form" style="margin-top:40px;" action="admin/add-news">
                    <div class="form-line" style="display: block;">
                        <span class="form-label"><span class="warning-label">*</span>公告标题：</span>
                        <input type="text" class="df-input-narrow" id="name-update" maxlength="50">
                        <span class="form-message" id="title-message"></span>
                        <br>
                    </div>
                    <div class="form-line" style="display: block;">
                        <span class="form-label"><span class="warning-label">*</span>公告内容：</span>
                        <textarea rows="12" cols="50" id="news-content-update"></textarea>
                        <span class="form-message" id="content-message"></span>
                        <br>
                    </div>
              </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭窗口
                </button>
                <button id="update-news-btn" data-action="/admin/system/updateNew" data-group="0" type="button"
                        class="btn btn-primary">
                    确定修改
                </button>
            </div>
        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>
<!-- Slider Ends -->

<!-- Javascript files -->
<script type="text/javascript" src="resources/js/new-list.js"></script>
<script type="text/javascript" src="resources/js/add-news.js"></script>
<script>
    $(function () {
        $("#add-news-modal-btn").click(function () {
            $(".form-news-title input").val("");
            $(".form-news-content textarea").val("");
            $("#add-news-modal").modal({
                backdrop: true,
                keyboard: true
            });

        });
    });
</script>
