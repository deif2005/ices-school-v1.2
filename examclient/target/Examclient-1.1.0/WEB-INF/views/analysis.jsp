<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp"%>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="min-height:500px;">

        <div class="row">
            <div class="col-xs-2" id="left-menu">
                <div id="sidebar" class="nav-collapse " style="" tabindex="5000"></div>
            </div>
            <div class="col-xs-10" id="right-content">
                <div class="page-header">
                    <h1><i class="fa fa-bar-chart-o"></i> 统计分析</h1>
                </div>
                <div class="page-content row">
                    <div class="col-xs-12">
                        <select id="field-switch" class="form-control">
                            <c:forEach items="${fieldList }" var="item">
                                <option value="${item.fieldId }">${item.fieldName }</option>
                            </c:forEach>
                            <!-- <option value="4">公务员申论</option>
                            <option value="5">医药行业考试</option> -->
                        </select>
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
<!-- Slider Ends -->
<%@include file="common/footer.jsp" %>
<script type="text/javascript" src="resources/js/exam-finished.js"></script>
<script type="text/javascript">

    $("#field-switch").val("${fieldId}");
    $("#field-switch").change(function () {
        window.location.href = "student/usercenter/analysis/" + $(this).val();
    });


</script>
