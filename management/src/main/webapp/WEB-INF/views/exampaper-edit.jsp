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
                    <h1><i class="fa fa-file-text"></i> 修改试卷 </h1>
                </div>
                <div class="page-content">
                    <div class="def-bk" id="bk-exampaper">

                        <div class="expand-bk-content" id="bk-conent-exampaper">
                            <div id="exampaper-header">
                                <div id="exampaper-title">
                                    <i class="fa fa-send"></i>
                                    <span id="exampaper-title-name"> ${exampapername} </span>
                                    <span style="display:none;" id="exampaper-id">${exampaperid}</span>

                                </div>
                                <div id="exampaper-desc-container">
                                    <div id="exampaper-desc" class="exampaper-filter">


                                    </div>
                                    <div style="margin-top: 5px;">
                                        <span>试卷总分：</span><span id="exampaper-total-point"
                                                                style="margin-right:20px;"></span>
                                        <span id="add-more-qt-to-paper"><i class="small-icon fa fa-plus-square"
                                                                           title="添加选项"></i><span>增加更多题目</span></span>
                                    </div>
                                    <div style="float: right;position: relative;top: -30px;">
                                        <button class="btn btn-danger save-paper-btn"><i class="fa fa-save"></i>保存试卷
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <ul id="exampaper-body" style="padding:0px;">
                                ${htmlStr }
                            </ul>
                            <div id="exampaper-footer">
                                <div id="question-navi">
                                    <div id="question-navi-controller">
                                        <i class="fa fa-arrow-circle-down"></i>
                                        <span>答题卡</span>
                                    </div>
                                    <div id="question-navi-content">
                                    </div>
                                </div>


                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="question-selector-modal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">选择试题添加到试卷中</h4>
                </div>
                <div class="modal-body">
                    <iframe id="qt-selector-iframe"
                            src="<%=list[1]%>/question/question-list/filterdialog-0-0-0-0-1.html" width="100%"></iframe>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
                    <button id="add-list-to-exampaper" type="button" class="btn btn-primary">添加选中</button>
                </div>

            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <div class="modal fade" id="question-point-modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">修改分数</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-line qt-point-destination">
                            <span class="form-label">分数：</span>
                            <label></label>
                            <input type="text" value=""/>
                        </div>
                        <div class="form-line">
                            <button class="btn btn-sm btn-success" id="update-point-btn"><i class="fa fa-check"></i>仅修改第<span
                                    id="qt-point-target-index"></span>题
                            </button>
                            <button class="btn btn-sm btn-warning" id="update-point-type-btn"><i
                                    class="fa fa-random"></i>批量更新该题型
                            </button>
                        </div>
                    </form>

                </div>

            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>

</div>

<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->

<!-- Javascript files -->
<script type="text/javascript" src="resources/js/exampaper-edit.js"></script>
