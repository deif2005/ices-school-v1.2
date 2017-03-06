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
                    <h1><i class="fa fa-file-text"></i> 人工阅卷 </h1>
                </div>
                <div class="page-content">
                    <div class="def-bk" id="bk-exampaper">

                        <div class="expand-bk-content" id="bk-conent-exampaper">
                            <div id="exampaper-header">
                                <div id="exampaper-title">
                                    <i class="fa fa-send"></i>
                                    <span id="exampaper-title-name">${exampapername} </span>
                                    <span style="display:none;" id="exampaper-id">${exampaperid}</span>

                                </div>
                                <div id="exampaper-desc-container">
                                    <div id="exampaper-desc" class="exampaper-filter">


                                    </div>
                                    <div style="margin-top: 5px;">
                                        <span>试卷总分：</span><span id="exampaper-total-point"
                                                                style="margin-right:20px;font-weight:bolder;"></span>
                                        <span>考生得分：</span><span id="exampaper-raw-point"
                                                                style="color: #5cb85c;margin-right:20px;font-weight:bolder;"></span>
                                        <!-- <span id="add-more-qt-to-paper"><i class="small-icon fa fa-plus-square" title="添加选项"></i><span>增加更多题目</span></span> -->
                                    </div>
                                </div>


                            </div>
                            <input type="hidden" id="hist-id" value="${examHistoryId }">
                            <input type="hidden" id="paper-id" value="${examPaperId }">
                            <input type="hidden" id="exam-id" value="${examId }">
                            <ul id="exampaper-body" style="padding:0px;">
                                ${htmlStr }
                                <%--
                                 <c:forEach items="${questionList}" var="item">
                                    <c:choose>
                                        <c:when test="${item.questionTypeId==1}">
                                            <li class="question qt-singlechoice"></c:when>
                                        <c:when test="${item.questionTypeId==2}">
                                            <li class="question qt-multiplechoice">
                                        </c:when>
                                        <c:when test="${item.questionTypeId==3}">
                                            <li class="question qt-trueorfalse">
                                        </c:when>
                                        <c:when test="${item.questionTypeId==4}">
                                            <li class="question qt-fillblank">
                                        </c:when>
                                        <c:when test="${item.questionTypeId==5}">
                                            <li class="question qt-shortanswer">
                                        </c:when>
                                        <c:when test="${item.questionTypeId==6}">
                                             <li class="question qt-essay">
                                        </c:when>
                                        <c:when test="${item.questionTypeId==7}">
                                              <li class="question qt-analytical">
                                        </c:when>
                                    </c:choose>

                                        <div class="question-title">
                                            <div class="question-title-icon"></div>
                                            <span class="question-no">").append("</span><span class="question-type" style="display: none;">

                                             <c:choose>
                                                 <c:when test="${item.questionTypeId==1}">
                                                      singlechoice</span>
                                                      <span class="knowledge-point-id" style="display: none;">${item.knowledgePointId}</span>
                                                       <span class="question-type-id" style="display: none;">${item.questionTypeId}</span>
                                                       <span>[单选题]</span>
                                                       <span class="question-point-content">
                                                          <span>(</span><span  class="question-point">${item.questionPoint}</span><span>分)</span>
                                                       </span>
                                                       <span class="question-id" style="display:none;">${item.questionId}</span>
                                                     </div>
                                                     <form class="question-body">
                                                         <p  class="question-body-text">${item.questionContent.title}
                                                            <c:if test="${item.questionContent.titleImg!=null} && ${item.questionContent.titleImg!=''}">
                                                            <img class="question-content-img question-img"
                                                                 src="${baseUrl}${item.questionContent.titleImg()}"/>
                                                            </c:if>
                                                         </p>
                                                       <c:forEach items="${item.questionContent.choiceList}" var="list">
                                                           <ul class="question-opt-list">
                                                              <li class="question-list-item">
                                                                  <input type="radio" value="${list.key}" name="question-radio1" class="question-input">
                                                                  <span class="question-li-text">
                                                                      ${list.key}: ${list.value}
                                                                  </span>
                                                              </li>
                                                            </ul>
                                                       </c:forEach>
                                                   </form>
                                                 </c:when>
                                                 <c:when test="${item.questionTypeId==2}">
                                                 </c:when>
                                                  <c:when test="${item.questionTypeId==3}">
                                                 </c:when>
                                                  <c:when test="${item.questionTypeId==4}">
                                                 </c:when>
                                                  <c:when test="${item.questionTypeId==5}">
                                                 </c:when>
                                                  <c:when test="${item.questionTypeId==6}">
                                                 </c:when>
                                                  <c:when test="${item.questionTypeId==7}">
                                                 </c:when>
                                           </c:choose>

                                <div class="answer-desc">
                                    <div class="answer-desc-summary">
                                        <span>正确答案：</span>
                                        <c:if test="${item.questionTypeId != 3}"><span class="answer_value">${item.answer}</span><br></c:if>
                                    </div>
                                    <div class="answer-desc-detail">
                                        <label class="label label-info">
                                            <i class="fa fa-paw"></i><span> 来源</span>
                                        </label>

                                        <div class="answer-desc-content">
                                            <p>${item.referenceName}</p></div>
                                    </div>
                                    <div class="answer-desc-detail">
                                        <label class="label label-warning">
                                            <i class="fa fa-flag"></i><span> 解析</span>
                                        </label>

                                        <div class="answer-desc-content">
                                            <p>${item.analysis}</p></div>
                                    </div>
                                    <div class="answer-desc-detail">
                                        <label class="label label-success">
                                            <i class="fa fa-bookmark"></i><span> 考点</span>
                                        </label>

                                        <div class="answer-desc-content">
                                            <p>${item.pointName}
                                            </p></div>
                                    </div>
                                </div>
                                </li>
                                </c:forEach>
                                </ul>--%>


                                <div id="exampaper-footer">
                                    <div id="question-navi">
                                        <div id="question-navi-controller">
                                            <i class="fa fa-arrow-circle-down"></i>
                                            <span>答题卡</span>
                                        </div>
                                        <div id="question-navi-content">
                                        </div>
                                    </div>
                                    <div style="padding-left:30px;">
                                        <button class="btn btn-info"><i class="fa fa-save"></i>完成阅卷</button>
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
<script type="text/javascript" src="resources/js/exampaper-mark.js"></script>
