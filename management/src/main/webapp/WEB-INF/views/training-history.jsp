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
							<h1> <i class="fa fa-bar-chart-o"></i> 培训进度</h1>
						</div>
						<div class="page-content">
							<div class="row">
								<div class="col-xs-12">
									<input type="hidden" id="training-id-hidden" value="${trainingId }">
									<div id="exampaper-list">
									<div class="table-search table-controller-item"	style="float: left; width: 200px;margin-top:10px;">
										<div class="input-group search-form">
											<input type="text" class="form-control" placeholder="搜索学员" value="${searchStr }"
											id="txt-search">
											<span class="input-group-btn">
												<button class="btn btn-sm btn-default" type="button"
												id="btn-search" >
													<i class="fa fa-search"></i>搜索
												</button> </span>
										</div>
									</div>
									<table class=" table" id="training-table">
										<thead>
											<tr>
												<td>学员ID</td><td>学员姓名</td><td>部门</td><td>学习进度</td>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${userTrainingList }" var="item">
												<tr>
													<td>${item.userName }</td>
													<td>${item.trueName }</td>
													<td>${item.depName }</td>
													<td>
														<div>
															<c:forEach items="${processMap[item.userId] }" var="processItem">
																<c:choose>
																	<c:when test="${processItem.process == 1 }">
																		<a class="section-navi-item navi-item-success">${processItem.sectionName }</a>
																	</c:when>
																	<c:otherwise>
																		<a class="section-navi-item">${processItem.sectionName }</a>
																	</c:otherwise>
																</c:choose>
																
															</c:forEach>
															
														</div>
													</td>	
													
												</tr>
											</c:forEach>
											
										</tbody><tfoot></tfoot>
									</table>
								</div>
							</div>
							
							<div id="page-link-content">
							
								<ul class="pagination pagination-sm">
									${pageStr}
								</ul>
							</div>
							
							
							

						</div>
					</div>
				</div>
			</div>
			
		</div>
		<%@include file="common/footer.jsp"%>

		<!-- Slider Ends -->

		<!-- Javascript files -->
		<script type="text/javascript" src="resources/chart/raphael-min.js"></script>
		<script type="text/javascript" src="resources/js/all.js"></script>
		<script type="text/javascript" src="resources/js/training-process-list.js"></script>
