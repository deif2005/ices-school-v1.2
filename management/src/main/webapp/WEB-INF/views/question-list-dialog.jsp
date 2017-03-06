<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>

<!-- Slider starts -->

<div>
    <!-- Slider (Flex Slider) -->

    <div class="container" style="min-height:500px;width:840px;">

        <div class="row">

            <div class="col-xs-12">

                <div class="page-content">

                    <div id="question-filter">

                        <dl id="question-filter-field">
                            <dt>
                                专业：
                            </dt>
                            <dd>
                                <c:choose>
                                    <c:when test="${questionFilter.fieldId == 0 }">
                                        <span data-id="0" class="label label-info">全部</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span data-id="0">全部</span>
                                    </c:otherwise>
                                </c:choose>

                                <c:forEach items="${fieldList}" var="field">
                                    <c:choose>
                                        <c:when test="${questionFilter.fieldId == field.fieldId }">
                                            <span class="label label-info"
                                                  data-id="${field.fieldId}">${field.fieldName}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span data-id="${field.fieldId}">${field.fieldName}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </dd>
                        </dl>
                        <dl id="question-filter-knowledge">
                            <dt>
                                知识类：
                            </dt>
                            <dd>
                                <c:choose>
                                    <c:when test="${questionFilter.knowledge == 0 }">
                                        <span data-id="0" class="label label-info">全部</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span data-id="0">全部</span>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach items="${knowledgeList}" var="knowledge">
                                    <c:choose>
                                        <c:when test="${questionFilter.knowledge == knowledge.pointId }">
                                            <span data-id="${knowledge.pointId}"
                                                  class="label label-info">${knowledge.pointName}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span data-id="${knowledge.pointId}">${knowledge.pointName}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </dd>
                        </dl>

                        <dl id="question-filter-qt">
                            <dt>
                                试题类型：
                            </dt>
                            <dd>
                                <c:choose>
                                    <c:when test="${questionFilter.questionType == 0 }">
                                        <span data-id="0" class="label label-info">全部</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span data-id="0">全部</span>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach items="${questionTypeList}" var="questionType">
                                    <c:choose>
                                        <c:when test="${questionFilter.questionType == questionType.id }">
                                            <span data-id="${questionType.id}"
                                                  class="label label-info">${questionType.name}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span data-id="${questionType.id}">${questionType.name}</span>
                                        </c:otherwise>
                                    </c:choose>


                                </c:forEach>

                            </dd>
                        </dl>
                    </div>
                    <div id="question-list">
                        <input id="field-id-hidden" value="${fieldId }" type="hidden">
                        <input id="knowledge-hidden" value="${knowledge }" type="hidden">
                        <input id="question-type-hidden" value="${questionType }" type="hidden">
                        <input id="search-param-hidden" value="${searchParam }" type="hidden">
                        <table class="table-striped table">
                            <thead>
                            <tr>
                                <td></td>
                                <td>ID</td>
                                <td class="question-name-td">试题名称</td>
                                <td>试题类型</td>
                                <td>专业</td>
                                <td>知识类</td>
                                <td>创建人</td>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${questionList }" var="items">
                                <tr>
                                    <td>
                                        <input type="checkbox" value="${items.id }">
                                    </td>
                                    <td class="question-id">${items.id }</td>
                                    <td>
                                        <a href="<%=list[1]%>/question/question-preview/${items.id }" target="_blank"
                                           title="预览">${items.name }</a>
                                        <span class="examing-point">${items.examingPoint} </span>
                                    </td>

                                    <td>${items.questionTypeName }</td>
                                    <td>${items.fieldName }</td>
                                    <td>${items.pointName }</td>
                                    <td>${items.creator }</td>
                                </tr>
                            </c:forEach>


                            </tbody>
                            <tfoot></tfoot>
                        </table>
                    </div>
                    <div class="page-link-content">
                        <ul class="pagination pagination-sm">${pageStr}</ul>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>


<!-- Slider Ends -->

<!-- Javascript files -->
<script type="text/javascript" src="resources/js/jquery/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="resources/js/all.js"></script>
<script type="text/javascript" src="resources/js/question-list4dialog.js?v=1"></script>

