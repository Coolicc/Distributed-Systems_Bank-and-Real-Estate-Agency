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
			
	<c:if test="${!empty user }">
		<a style="padding-left: 53px;" href="/RealEstateRest/addRealEstateServlet"><button class="btn btn-primary btn-lg">Add Real Estate</button></a>
	</c:if>
	<c:if test="${empty userRE }">
	<div class="row" style="padding-top: 30px;">
		<form action="/RealEstateRest/searchRealEstateNameServlet" method="get">
			<div style="padding-left: 70px;" class="form-group">
				<label style="color: #000;">Search real estates by name:</label><br>
				<input type="text" value="${search }" style="color: #000;"  name="search">
			</div>
			<div style="padding-left: 70px;" class="form-group">
				<input type="hidden" name="page" value="1">
				<input  type="submit" value="Search" class="btn btn-primary btn-lg">
			</div>
		</form>
	</div>
	</c:if>
	<div class="gallery">
			<c:forEach items="${realEstatePage }" var="realEstate">
				<div class="row" style="padding-bottom: 10px;">
					<div class="col-md-4">
					<img height="400" width="400" src="/RealEstateRest/img/${realEstate.realEstateID}.jpg"/>
					</div>
					<div class="col-md-4">
						<div class="text-center"><h3>${realEstate.name }</h3></div>
						<div class="text-center"><h5>${realEstate.price } $</h5></div>
						<form action="/RealEstateRest/realEstateServlet" method="get">
							<input type="hidden" name="realEstateID" value="${realEstate.realEstateID }">
							<input style="margin-left: 168px;" type="submit" value="More Info" class="btn btn-primary btn-lg">
						</form>
					</div>
				</div>
			</c:forEach>
	</div>
	<div class="row">
		<c:if test="${empty userRE}">
			<c:if test="${page > 1 }">
				<a style="margin-left: 65px;" href="/RealEstateRest/searchRealEstateNameServlet?search=${search}&page=${prevPage}"><button class="btn btn-primary btn-lg">Previous page</button></a>
			</c:if>
			<c:if test="${page < maxPage }">
				<a style="margin-left: 250px;" href="/RealEstateRest/searchRealEstateNameServlet?search=${search}&page=${nextPage}"><button class="btn btn-primary btn-lg">Next page</button></a>
			</c:if>
		</c:if>
		<c:if test="${!empty userRE}">
			<c:if test="${page > 1 }">
				<a style="margin-left: 65px;" href="/RealEstateRest/userRealEstatesServlet?userID=${user.userID }&search=${search}&page=${prevPage}"><button class="btn btn-primary btn-lg">Previous page</button></a>
			</c:if>
			<c:if test="${page < maxPage }">
				<a style="margin-left: 250px;" href="/RealEstateRest/userRealEstatesServlet?userID=${user.userID }&search=${search}&page=${nextPage}"><button class="btn btn-primary btn-lg">Next page</button></a>
			</c:if>
		</c:if>
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