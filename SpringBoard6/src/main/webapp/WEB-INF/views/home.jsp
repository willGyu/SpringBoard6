<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="include/header.jsp" %>

<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

 <input type="button" value=" 버튼 " class="btn-lg btn-success">
 
 <button type="button" class="btn btn-block btn-danger btn-lg">Danger</button>
 
 <a class="btn btn-block btn-social btn-github">
	<i class="fa fa-github"></i> Sign in with GitHub
 </a>
 
 <div class="col-lg-3 col-xs-6">

<div class="small-box bg-green">
<div class="inner">
<h3>53<sup style="font-size: 20px">%</sup></h3>
<p>Bounce Rate</p>
</div>
<div class="icon">
<i class="ion ion-stats-bars"></i>
</div>
<a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
</div>
</div>

<%@ include file="include/footer.jsp" %>
