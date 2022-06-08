<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="<c:url value='/css/bootstrap.min.css' />"  rel="stylesheet"></link>
	<link href="<c:url value='/css/font-awesome.min.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/css/animate.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/css/animate.min.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/css/style.css' />" rel="stylesheet"></link>
</head>
 
<body>
		<header id="header">
        <nav class="navbar navbar-default navbar-static-top" role="banner">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                   <div class="navbar-brand">
						<a href="/RealEstateRest/indexServlet"><h1>Real Estate Agency</h1></a>
					</div>
                </div>				
                <div class="navbar-collapse collapse">							
					<div class="menu">
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation"><a href="/RealEstateRest/searchRealEstateNameServlet">Real Estates</a></li>
							<c:if test="${!empty user }">
								<li role="presentation"><a href="/RealEstateRest/userServlet?userID=${user.userID }">Account</a></li>
								<li role="presentation"><a href="/RealEstateRest/userRealEstatesServlet?userID=${user.userID }">My Real Estates</a></li>
								<li role="presentation"><a href="/RealEstateRest/addRealEstateServlet">Add Real Estate</a></li>
								<li role="presentation"><a href="/RealEstateRest/logoutServlet">Logout</a></li>
							</c:if>
							<c:if test="${empty user}">
								<li role="presentation"><a href="/RealEstateRest/loginServlet">Log In</a></li>
								<li role="presentation"><a href="/RealEstateRest/registerServlet">Register</a></li>
							</c:if>					
						</ul>
					</div>
				</div>		
            </div><!--/.container-->
        </nav><!--/nav-->		
    </header><!--/header-->	
			
	<div class="gallery">
	<table style="color: #000;">
		<tr>
			<td rowspan="10"><img height="370" width="265" src="/RealEstateRest/img/${userPage.username}.jpg"/></td>
			<td colspan="10">
				<div class="text-center">
					<h3>${userPage.username }</h3>
				</div>
			</td>
		</tr>
		<tr>
			<th style="padding-left: 15px; width: 150px;">Name:</th>
			<td style="padding-right: 300px; padding-left: 15px;">${userPage.name }</td>
		</tr>
		<tr>
			<th style="padding-left: 15px;">Email:</th>
			<td style="padding-right: 300px; padding-left: 15px;">${userPage.email }</td>
		</tr>
		<tr>
			<th style="padding-left: 15px;">Rating:</th>
			<td style="padding-right: 300px; padding-left: 15px;">${userPage.rating }</td>
		</tr>
	</table>
	
	<section>
		<c:if test="${!empty user }">
			<h4>Comments</h4>
			<h5>Post a comment</h5>
			<form action="/RealEstateRest/commentServlet" method="post">
				<input type="hidden" name="userID" value="${userPage.userID }">
				Rating: <select name="rating" style="color: black">
							<option value="5">5</option>
							<option value="4">4</option>
							<option value="3">3</option>
							<option value="2">2</option>
							<option value="1">1</option>
						</select><br>
				Comment: <input type="text" name="text" style="color: black"><br>
				<input type="submit" value="Post" name="submit" class="btn btn-primary btn-lg" required="required">
			</form>
		</c:if>
		<c:forEach items="${comments }" var="comment">
			<div>
				<a href="/RealEstateRest/userServlet?userID=${comment.posterID }"><h5>${comment.posterName}</h5></a>
				<p>${comment.text }</p>
				<p>Rating: ${comment.rating }</p>><br>
			</div>
		</c:forEach>
	</section>
</div>
		
	<footer>
		<div class="container">
			<div class="col-md-4 wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="300ms">
				<h4>About Us</h4>
				<p>Real Estate Agency is a real estate agency where you can buy real estates directly with your bank account.</p>						
				<div class="contact-info">
					<ul>
						<li><i class="fa fa-home fa"></i>Trg Dositeja Obradovica 3, 21000 Novi Sad</li>
						<li><i class="fa fa-phone fa"></i>065/905-8057</li>
						<li><i class="fa fa-envelope fa"></i> info@REA.com</li>
					</ul>
				</div>
			</div>
			
		</div>	
	</footer>
	
	<div class="sub-footer">
		<div class="container">
			<div class="social-icon">
				<div class="col-md-4">
					<ul class="social-network">
						<li><a href="#" class="fb tool-tip" title="Facebook"><i class="fa fa-facebook"></i></a></li>
						<li><a href="#" class="twitter tool-tip" title="Twitter"><i class="fa fa-twitter"></i></a></li>
						<li><a href="#" class="gplus tool-tip" title="Google Plus"><i class="fa fa-google-plus"></i></a></li>
						<li><a href="#" class="linkedin tool-tip" title="Linkedin"><i class="fa fa-linkedin"></i></a></li>
						<li><a href="#" class="ytube tool-tip" title="You Tube"><i class="fa fa-youtube-play"></i></a></li>
					</ul>	
				</div>
			</div>
			
			<div class="col-md-4 col-md-offset-4">
				<div class="copyright">
					&copy; Day 2015 by <a target="_blank" href="http://bootstraptaste.com/" title="Free Twitter Bootstrap WordPress Themes and HTML templates">Bootstrap Themes</a>.All Rights Reserved.
				</div>
                <!-- 
                    All links in the footer should remain intact. 
                    Licenseing information is available at: http://bootstraptaste.com/license/
                    You can buy this theme without footer links online at: http://bootstraptaste.com/buy/?theme=Day
                -->
			</div>						
		</div>				
	</div>
		
		<script src="/js/jquery.js"></script>
		<script src="/js/bootstrap.min.js"></script>	
		<script src="/js/wow.min.js"></script>
		<script>
			wow = new WOW({}).init();
		</script>
</body>
</html>