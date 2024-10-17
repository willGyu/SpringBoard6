<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<h1>/board/listAll.jsp</h1>

<%-- 	${boardList } <hr> --%>
<%-- 	${requestScope } --%>
result : ${result }

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


<script type="text/javascript">
	/* JSP페이지의 실행순서 
	   JSP(JAVA) -> JSTL/EL -> HTML -> JavaScript / JQuery
	*/
 	/* JS에서 el표현식의 데이터를 사용가능 */
 	//var result = ${result }; => var result = INSERTOK;
 	var result = '${result }';
 	
 	if(result == "INSERTOK"){
	 	alert(" 정상적으로 글쓰기 동작 완료 ! ");
 	}

</script>



<%@ include file="../include/footer.jsp"%>

