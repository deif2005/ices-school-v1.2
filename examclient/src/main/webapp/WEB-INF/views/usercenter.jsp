<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
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
                    <h1><i class="fa fa-dashboard"></i> 用户中心</h1>
                </div>
                <div class="page-content row">
                    <div class="col-xs-4">
                        <h6>个人信息</h6>

                        <div>
                            <span>姓名：</span>
                            <span> ${username }</span>
                        </div>
                        <div>
                            <span>邮箱：</span>
                            <span> ${email } </span>
                        </div>
                        <%-- <div>
                            <span >专业：</span>
                            <span> ${field } </span>
                        </div>
                        <div>
                            <span >上次登录：</span>
                            <span> <fmt:formatDate value="${lastLoginTime }" pattern="yyyy-MM-dd"/> </span>
                        </div> --%>

                    </div>


                </div>
                <div class="page-content row">
                    <div class="col-xs-12" id="radar-div">
                        <h6>知识掌握情况</h6>
                        <select id="field-switch" class="form-control">
                            <c:forEach items="${fieldList }" var="item">
                                <option value="${item.fieldId }">${item.fieldName }</option>
                            </c:forEach>
                            <!-- <option value="4">公务员申论</option>
                            <option value="5">医药行业考试</option> -->
                        </select>

                        <div class="page-content row">

                            <div class="col-xs-8">
                                <div id="mychart" style="height:450px;"></div>
                                <p>此统计数据不包括简答、论述、分析等主观题</p>
                            </div>
                            <div class="col-xs-4" id="radar-legend">

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
<script type="text/javascript">

    $("#field-switch").val("${fieldId}");
    $("#field-switch").change(function () {
        window.location.href = "student/usercenter/" + $(this).val();
    });


    $(function () {
        var option = {

            tooltip: {
                trigger: 'axis'
            },
            legend: {
                orient: 'vertical',
                x: 'right',
                y: 'bottom',
                data: ['完成率', '正确率']
            },
            toolbox: {
                show: true,
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            polar: [
                {
                    indicator: ${labels}
                }
            ],
            calculable: true,
            series: [
                {
                    name: '预算 vs 开销（Budget vs spending）',
                    type: 'radar',
                    data: [
                        {
                            value: ${finishrate},
                            name: '完成率'
                        },
                        {
                            value: ${correctrate},
                            name: '正确率'
                        }
                    ]
                }
            ]
        };
        var myChart = echarts.init(document.getElementById('mychart'));
        myChart.setOption(option);
    });
</script>
