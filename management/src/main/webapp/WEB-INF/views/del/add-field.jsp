<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->
<table></table>
    <div class="container" style="min-height:500px;">

        <div class="row">
            <div class="col-xs-2" id="left-menu">
                <div id="sidebar" class="nav-collapse " style="overflow: hidden;" tabindex="5000">
                </div>

            </div>
            <div class="col-xs-10" id="right-content">
                <div class="page-header">
                    <h1><i class="fa fa-list-ul"></i> 添加题库 </h1>
                </div>
                <div class="page-content">

                    <form id="field-add-form" style="margin-top:40px;" action="admin/field-add">

                        <div class="form-line form-name" style="display: block;">
                            <span class="form-label"><span class="warning-label"></span>题库名：</span>
                            <input type="text" class="df-input-narrow" id="name"><span class="form-message"></span>
                            <br>
                        </div>
                        <div class="form-line form-memo" style="display: block;">
                            <span class="form-label"><span class="warning-label"></span>题库描述：</span>
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

<%@include file="common/footer.jsp" %>

<!-- Slider Ends -->

<!-- Javascript files -->
<script type="text/javascript" src="resources/chart/raphael-min.js"></script>
<script type="text/javascript" src="resources/chart/morris.js"></script>
<script type="text/javascript" src="resources/js/add-field.js"></script>
