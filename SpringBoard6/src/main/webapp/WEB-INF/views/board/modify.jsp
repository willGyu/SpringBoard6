<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>


<h1>/board/modify.jsp</h1>

${boardVO }

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">게시판 글 수정하기</h3>
	</div>
	
	<!-- bno 정보를 전달하는 폼태그 -->
	<form role="form" action="" method="post">

	<div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">번  호</label>
			<input type="text" class="form-control" 
			         name="bno" value="${boardVO.bno }" readonly>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">제 목</label>
			<input type="text"
				name="title" class="form-control" id="exampleInputEmail1"
				value="${boardVO.title }" >
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">작성자</label>
			<input type="text"
				name="writer" class="form-control" id="exampleInputEmail1"
				value="${boardVO.writer }" >
		</div>

		<div class="form-group">
			<label>내 용</label>
			<textarea class="form-control" name="content" rows="3" 
				    >${boardVO.content }</textarea>
		</div>


	</div>

	<div class="box-footer">
		<button type="submit" class="btn btn-danger">수정</button>
		<button type="reset" class="btn btn-warning">취소</button>
	</div>
	
	</form>
</div>

<%@ include file="../include/footer.jsp"%>