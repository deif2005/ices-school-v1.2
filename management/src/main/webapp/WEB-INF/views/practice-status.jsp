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
            <div class="col-xs-9">
                <div class="page-header">
                    <h1><i class="fa fa-bar-chart-o"></i> 学习进度</h1>
                </div>
                <div class="page-content" style="border-bottom:0px;">
                    <!-- 在这里添加一个专业的选择按钮  -->
                    <select id="field-switch" class="form-control">
                        <c:forEach items="${fieldList }" var="item">

                            <option value="${item.fieldId }">${item.fieldName }</option>


                        </c:forEach>
                        <!-- <option value="4">公务员申论</option>
                        <option value="5">医药行业考试</option> -->
                    </select>
                    <div class="col-xs-12">
                        <div id="question-list">

                            <c:forEach items="${kparl }" var="item">
                                <table class="table-striped table">
                                    <thead>
                                    <tr>
                                        <td colspan="4">
                                            <h6>${item.knowledgePointName }</h6>
                                            <span style="color:#428bca;">学习进度：<fmt:formatNumber
                                                    value="${item.finishRate }" type="percent"/></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>题型</td>
                                        <td>未做</td>
                                        <td>做对题目</td>
                                        <td>做错题目</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${item.typeAnalysis }" var="i">
                                        <tr>
                                            <td>${i.questionTypeName }</td>
                                            <td><span class="span-info">${i.restAmount }</span></td>
                                            <td><span class="span-success">${i.rightAmount }</span></td>
                                            <td><span class="span-danger">${i.wrongAmount }</span></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot></tfoot>
                                </table>
                            </c:forEach>

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
        $("#training-table tr").click(function () {
            console.log("load section.......");

            $('#section-content').load('admin/training/section-list/' + $($(this).find("td")[1]).find("a").data("id"));

            $(".add-section-btn").show();

            $("#training-name").val($($(this).find("td")[1]).find("a").text());
            $("#training-add-id").val($($(this).find("td")[1]).find("a").data("id"));
            console.log($("#training-add-id").val());
        });


        $(".add-section-btn").click(function () {
            $("#add-section-modal").modal({
                backdrop: true,
                keyboard: true
            });

        });

        $("#field-switch").val("${fieldId}");
        $("#field-switch").change(function () {
            //alert(123);
            window.location.href = util.getCurrentRole() + "/training/student-practice-status/" + $(this).val() + "/30";
        });
    });
</script>
