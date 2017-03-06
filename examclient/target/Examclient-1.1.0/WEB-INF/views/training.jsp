<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>

<!-- Slider starts -->

<div class="content" style="min-height: 400px;">

    <div class="training-top">
        <i class="fa fa-chevron-circle-left"></i>
        <!-- 课程主页 -->

        <span>${section.trainingName }</span> <span class="section-title">${section.sectionName }</span>
    </div>

    <div class="video-div" style="background-color: #000; height: 100%;">
        <input type="hidden" value="${duration }" id="training-duration">
        <input type="hidden" value="${sectionId}" id="section-id">
        <input type="hidden" value="${section.trainingId}" id="training-id">
        <video id="my_video" class="video-js vjs-default-skin" controls preload="none" width="100%" height="100%"
               style="width:100%;height:100%;"
               poster="resources/images/bg.png"
               data-setup="{}">
            <c:choose>
                <c:when test="${section.fileType eq '.mp4' }">
                    <source src="${section.filePath }" type='video/mp4'>
                </c:when>
                <c:when test="${section.fileType eq '.flv' }">
                    <source src="${section.filePath }" type='video/flv'>
                </c:when>
                <c:otherwise>
                    <source src="${section.filePath }">
                </c:otherwise>
            </c:choose>

        </video>

    </div>

    <div style="background-color: #000; height: 100%; display: none;">

    </div>
</div>
<div class="control-pane">
    <ul id="myTabs" class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a
                id="home-tab" role="tab" data-toggle="tab" aria-controls="home"
                aria-expanded="true"> <i class="fa fa-th-list"></i> 选择章节
        </a></li>
        <%-- <li role="presentation"><a href="#profile" role="tab"
                                    id="profile-tab" data-toggle="tab" aria-controls="profile"><i
                 class="fa fa-comments-o"></i> 评论</a></li>--%>
    </ul>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="home">
            <ul class="section-list">
                <c:forEach items="${sectionList }" var="item">
                    <c:choose>
                        <c:when test="${item.sectionId == sectionId }">
                            <li class="active">
                                <div>
                                    <i class="fa fa-video-camera"></i> <a>${item.sectionName }</a>
                                </div>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <div>
                                    <i class="fa fa-video-camera"></i> <a
                                        href="student/training/${item.trainingId }?sectionId=${item.sectionId}">${item.sectionName }</a>
                                </div>
                            </li>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </ul>
        </div>
        <%-- <div role="tabpanel" class="tab-pane" id="profile">
             <div class="expand-bk-content" id="bk-conent-comment"
                  style="margin-top: 10px; padding: 10px;">
                 <div id="comment-title" style="margin-bottom: 15px;">
                     <i class="fa fa-comments"></i> <span> 学员评论 </span>

                 </div>
                 <form class="comment-form">
                         <textarea rows="" cols="" style="width: 100%; height: 95px;"
                                   placeholder="随便说点什么吧..."></textarea>
                     <input class="btn btn-primary" type="submit" value="发表评论">
                 </form>
                 <div class="comment-total">
                     <span class="comment-total-num">1</span>条评论
                 </div>
                 <ul class="comment-list">

                 </ul>
             </div>
             <div id="show-more-div">
                 <input type="hidden" id="idx-hidden" value="2">
                 <input type="hidden" id="last-floor-hidden" value="0">
                 <button id="show-more-btn" disabled="disabled" style="margin:10px 0 10px 0;">没有更多评论了...</button>
             </div>
         </div>--%>
    </div>
</div>

