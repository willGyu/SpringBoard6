<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<h1>/board/listAll.jsp</h1>

<%-- 	${boardList } <hr> --%>
<%-- 	${requestScope } --%>
<div class="box">
	<div class="box-header with-border">
		<h3 class="box-title">게시판 리스트(목록)</h3>
	</div>

	<div class="box-body">
		<table class="table table-bordered">
			<tbody>
				<tr>
					<th style="width: 10px">bno</th>
					<th>title</th>
					<th>writer</th>
					<th style="width: 40px">viewcnt</th>
				</tr>
				<c:forEach var="vo" items="${boardList }">
					<tr>
						<td>${vo.bno }</td>
						<td>${vo.title }</td>
						<td>${vo.writer }</td>
						<td><span class="badge bg-orange">${vo.viewcnt }</span></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="box-footer clearfix">
		<ul class="pagination pagination-sm no-margin pull-right">
			<li><a href="#">«</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">»</a></li>
		</ul>
	</div>
</div>



<%@ include file="../include/footer.jsp"%>

