<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="min-height: 800px;">

        <div class="row">
            <%--<div class="col-xs-2" id="left-menu">
                <div id="sidebar" class="nav-collapse " style="overflow: hidden;" tabindex="5000">
                </div>
            </div>--%>
            <%@include file="common/left-menu.jsp" %>
            <div class="col-xs-10" id="right-content">
                <div class="page-header">
                    <h1>
                        <i class="fa fa-file-text"></i> 试题预览
                    </h1>
                </div>
                <div class="page-content">
                    <div class="def-bk" id="bk-exampaper">

                        <div class="expand-bk-content" id="bk-conent-exampaper">
                            <ul id="exampaper-body" style="padding: 0px;">
                                ${strHtml }
                                <div class="answer-desc-detail">
                                    <label class="label label-primary"><i
                                            class="fa fa-check-square-o"></i><span> 添加人</span></label>
                                    <div class="answer-desc-content">${question.creator }</div>
                                </div>
                            </ul>
                            <div id="exampaper-footer"
                                 style="height: 187px; text-align: center; margin-top: 40px;">
                                <c:choose>
                                    <c:when
                                            test="${question.creator == sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}">
                                        <button class="btn btn-danger" id="delete-question-btn">
                                            <i class="fa fa-trash-o"></i> 删除该题
                                        </button>
                                        <button class="btn btn-info"
                                                onclick="javascript:window.close();">
                                            <i class="fa fa-times"></i> 关闭页面
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn btn-danger" id="delete-question-btn"
                                                disabled="disabled" title="您只能删除你自己添加的题">
                                            <i class="fa fa-trash-o"></i> 删除该题
                                        </button>
                                        <button class="btn btn-info"
                                                onclick="javascript:window.close();">
                                            <i class="fa fa-times"></i> 关闭页面
                                        </button>
                                        <p>您只能删除你自己添加的题</p>
                                    </c:otherwise>
                                </c:choose>

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
<script type="text/javascript"
        src="resources/js/uploadify/jquery.uploadify3.1Fixed.js"></script>
<script type="text/javascript"
        src="resources/js/question-upload-img.js"></script>
<script type="text/javascript" src="resources/js/field-2-point.js"></script>
<%--<script type="text/javascript" src="resources/js/question-add.js"></script>--%>

<!-- Bootstrap JS -->
<script type="text/javascript"
        src="resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#delete-question-btn").click(
                function () {
                    var result = confirm("确定删除吗？删除后将不可恢复");
                    if (result == true) {
                        jQuery.ajax({
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json'
                            },
                            type: "GET",
                            url: 'secure/question/delete-question/'
                            + $(".question-id").text(),
                            success: function (message, tst, jqXHR) {
                                if (!util.checkSessionOut(jqXHR))
                                    return false;
                                if (message.result == "success") {
                                    util.success("删除成功！", function () {
                                        window.opener.location
                                                .reload(false);
                                        window.close();
                                    });
                                } else {
                                    util.error("操作失败请稍后尝试");
                                }
                            },
                            error: function (jqXHR, textStatus) {
                                util.error("操作失败请稍后尝试");
                            }
                        });
                    }

                });
    });

</script>
