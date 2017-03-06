<%--<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<table class=" table">
    <!-- <h4>
        对应章节
    </h4> -->
    <thead>
    <tr>
        <td>章节内容</td>
    </tr>
    </thead>
    <!-- <thead>
        <tr>
            <td></td>
            <td style="display:none;">ID</td>
            <td>培训名</td>
            <td>创建时间</td>
            <td>创建人</td>
            <td>操作</td>
        </tr>
    </thead> -->
    <tbody>
    <c:forEach items="${sectionList }" var="item">
        <tr>
            <td><a class="training-sections" data-id="${item.sectionId }">${item.sectionName }</a></td>
            <td>
                <a class="delete-paper btn-sm btn-danger " onclick="delTrainingSection(${item.sectionId },${item.userId })">删除</a>
            </td>
        </tr>
    </c:forEach>


    </tbody>
    <tfoot>
    <!-- <tr>
        <td colspan="2">
        <button class="btn btn-sm btn-info add-section-btn">新增章节</button>
        </td>
    </tr> -->
    </tfoot>
</table>--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="min-height:500px;">

        <div class="row">
            <%--<div class="col-xs-2" id="left-menu">
                <c:import url="/common-page/left-menu?topMenuId=${topMenuId}&leftMenuId=${leftMenuId}"
                          charEncoding="UTF-8"/>

            </div>--%>
            <%@include file="common/left-menu.jsp" %>
            <div class="col-xs-10" id="right-content">
                <div class="page-header">
                    <h1><i class="fa fa-bar-chart-o"></i> 章节管理 </h1>
                </div>
                <div class="page-content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="btn-group table-controller-item" style="float: left">
                                <button class="btn btn-sm btn-info add-section-btn">新增章节</button>

                            </div>
                            <table class="table-striped table" id="training-table">
                                <thead>
                                <tr>
                                    <td style="display:none;">ID</td>
                                    <td>章节名称</td>
                                    <td>章节描述</td>
                                    <td>培训名称</td>
                                    <td>创建时间</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${sectionList }" var="item">
                                    <tr>
                                        <td style="display:none;">${item.sectionId }</td>
                                        <td><a class="training-sections"
                                               data-id="${item.sectionId }">${item.sectionName }</a></td>
                                        <td>${item.sectionDesc }</td>
                                        <td>${item.trainingName }</td>
                                        <td><fmt:formatDate
                                                value="${item.createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
                                        <td>
                                            <a class="delete-paper btn-sm btn-danger "
                                               onclick="delTrainingSection(${item.sectionId },${item.userId })">删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>


                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div id="page-link-content">
                    <ul class="pagination pagination-sm">
                        ${pageStr}
                    </ul>
                </div>
                <div class="modal fade" id="add-section-modal" tabindex="-1" role="dialog"
                     aria-labelledby="myLargeModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h6 class="modal-title" id="myModalLabel">添加章节</h6>
                            </div>
                            <div class="modal-body">
                                <form id="section-add-form" style="margin-top:40px;" action="secure/add-user">
                                    <div class="form-line form-group-id" style="display: none;">
                                        <span class="form-label"><span class="warning-label">*</span>id：</span>
                                        <input type="text" class="df-input-narrow" id="training-add-id"
                                               value="${trainingId}"
                                               disabled="disabled">
                                        <span class="form-message"></span>
                                        <br>
                                    </div>
                                    <%--  <div class="form-line form-group" style="display: block;">
                                          <span class="form-label"><span class="warning-label">*</span>培训名称：</span>
                                          <input type="text" class="df-input-narrow" id="training-name" value=""
                                                 disabled="disabled">
                                          <span class="form-message"></span>
                                          <br>
                                      </div>--%>
                                    <div class="form-line form-group add-section-name" style="display: block;">
                                        <span class="form-label"><span class="warning-label">*</span>章节名称：</span>
                                        <input type="text" class="df-input-narrow" id="section-name" value="">
                                        <span class="form-message"></span>
                                        <br>
                                    </div>
                                    <div class="form-line form-group add-section-desc" style="display: block;">
                                        <span class="form-label"><span class="warning-label"></span>章节描述：</span>
                                        <textarea class="df-input-narrow" id="section-desc"></textarea>
                                        <span class="form-message"></span>
                                        <br>
                                    </div>
                                    <div class="form-line add-section-file">
                                        <span class="form-label">上传附件：</span>
                                        <input type="file" id="uploadify1" onchange="uploadFile(this)">
                                        <input type="hidden" id="fileName">
                                        <input type="hidden" id="fileType">
                                        <input type="hidden" id="filePath">
                                        <br/>
                                        <span style="color: #c00;font-size: 12px" id="form-message"><span
                                                style="color: #979797">请上传mp4 flv pdf文件</span></span>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                    关闭窗口
                                </button>
                                <button id="add-section-btn" type="button" class="btn btn-primary">
                                    确定添加
                                </button>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>

</div>
<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->

<!-- Javascript files -->
<script type="text/javascript" src="resources/js/uploadify/jquery.uploadify3.1Fixed.js"></script>
<script type="text/javascript" src="resources/js/training-list.js"></script>
<script type="text/javascript" src="resources/js/training-file-upload.js"></script>
<script type="text/javascript" src="resources/js/add-training-section.js"></script>
<script>

    $(function () {
        $(".add-section-btn").click(function () {
            //情空
            $(".form-message").empty();
            $("#section-name").val("");
            $(".add-section-desc textarea").val("");
            $('#form-message').html('<span style="color: #979797">请上传mp4 flv pdf文件</span>')
            $("#add-section-modal").modal({
                backdrop: true,
                keyboard: true
            });

        });
    });
</script>

