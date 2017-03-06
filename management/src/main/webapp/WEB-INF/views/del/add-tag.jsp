<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="../common/header.jsp" %>
<%@include file="../common/banner.jsp" %>
<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="min-height:500px;">

        <div class="row">
            <div class="col-xs-3">
                <ul class="nav default-sidenav">

                    <li>
                        <a href="admin/field-list-1"> <i class="fa fa-book"></i> 题库列表 </a>
                    </li>

                    <li>
                        <a href="admin/add-field"> <i class="fa fa-qrcode"></i> 添加题库 </a>
                    </li>

                    <li>
                        <a href="admin/point-list-1-1"> <i class="fa fa-sitemap"></i> 知识分类列表 </a>
                    </li>

                    <li>
                        <a href="admin/add-point"> <i class="fa fa-pencil"></i> 添加知识分类 </a>
                    </li>
                    <li>
                        <a href="teacher/tag-list-1"> <i class="fa fa-tag"></i> 标签列表 </a>
                    </li>

                    <li class="active">
                        <a> <i class="fa fa-plus"></i> 添加标签 </a>
                    </li>
                </ul>

            </div>
            <div class="col-xs-9">
                <div class="page-header">
                    <h1><i class="fa fa-list-ul"></i> 添加标签 </h1>
                </div>
                <div class="page-content">

                    <form id="tag-add-form" style="margin-top:40px;" action="teacher/tag-add">

                        <div class="form-line form-name" style="display: block;">
                            <span class="form-label"><span class="warning-label"></span>标签名：</span>
                            <input type="text" class="df-input-narrow" id="name">
                            私有：
                            <input type="checkbox" class="df-input-narrow" id="is_private" checked="checked">
                            <span class="form-message"></span>
                            <br>
                        </div>
                        <div class="form-line form-memo" style="display: block;">
                            <span class="form-label"><span class="warning-label"></span>标签描述：</span>
                            <input type="text" class="df-input-narrow" id="memo"><span class="form-message"></span>
                            <br>
                        </div>


                        <div class="form-line">
                            <input id="btn-save" value="确认添加" type="submit" class="df-submit btn btn-info">
                        </div>
                    </form>


                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../common/footer.jsp" %>

<!-- Slider Ends -->

<!-- Javascript files -->
<script type="text/javascript" src="resources/chart/raphael-min.js"></script>
<script type="text/javascript" src="resources/chart/morris.js"></script>
<script type="text/javascript" src="resources/js/add-tag.js"></script>
