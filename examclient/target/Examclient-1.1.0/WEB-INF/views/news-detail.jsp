<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<%@include file="common/banner.jsp" %>
<div class="container" style="min-height:500px;">
    <div class="row">
        <div class="col-xs-2" id="left-menu">
            <div id="sidebar" class="nav-collapse " style="" tabindex="5000"></div>
        </div>
        <div class="col-xs-10" id="right-content">
            <div class="content" style="padding:30px 0 0 0;min-height: 400px;">
                <div class="container">
                    <div id="content" class="clearfix">
                        <div class="def-bk">
                            <div class="def-bk-title">

                            </div>
                            <div class="def-bk-content" id="bk-conent-news-detail">
                                <div class="news-body">
                                    <h1>${news.title }</h1>

                                    <div class="news-body-info">
                            <span class="news-body-date"><fmt:formatDate value="${news.createTime}"
                                                                         pattern="yyyy-MM-dd"/></span>
                                        <span>发布人：</span><span class="news-body-creator">${news.creator }</span>
                                    </div>
                                    <p>
                                        ${news.content }
                                    </p>

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
