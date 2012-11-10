<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
		<meta name="description" content="">
		<meta name="author" content="">

		<meta name="viewport" content="initial-scale = 1.0">

		<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

		<r:require modules="scaffolding"/>

		<!-- Le fav and touch icons -->
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'Enlist_Avatar.ico')}" type="image/x-icon">

		<g:layoutHead/>
		<r:layoutResources/>
		
		<!-- Resources for JQuery timepicker and datepicker -->
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'bootstrap-datepicker.css')}" />
		<script src="${resource(dir: 'js', file: 'bootstrap-datepicker.js')}"></script>
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'bootstrap-timepicker.css')}" />
		<script src="${resource(dir: 'js', file: 'bootstrap-timepicker.js')}"></script>
	</head>

	<body>

		<nav class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container-fluid">
					
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</a>
					
					<a class="brand" href="${createLink(uri: '/')}"><g:img dir="images" file="Enlist_Logo_Small.png" style="margin-top:-11px;" /></a>

					<enlist:nav />

		            <div class="pull-right">
		            	<ul class="nav">
			                <sec:ifLoggedIn>
			                	<%-- TODO: Only show if certain roles --%>
			                	<li class="divider-vertical"></li>
			                	<li class="dropdown">
			                		<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-cog"></i> App Settings<b class="caret"></b></a>
			                		<ul class="dropdown-menu">
			                			<li><g:link controller="quartz" action="list">Quartz Jobs</g:link>
			                		</ul>
			                	</li>
			                	
			                	<li class="divider-vertical"></li>
			                	<li class="dropdown">
			                		<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-user"></i> <sec:loggedInUserInfo field="username"/><b class="caret"></b></a>
			                		<ul class="dropdown-menu">
			                			<li><g:link controller="logout" action="index"><i class="icon-book"></i> My Profile</g:link></li>
			                			<li class="divider"></li>
			                			<li><g:link controller="logout" action="index"><i class="icon-remove-circle"></i> Logout</g:link></li>
			                		</ul>
			                	</li>
			                </sec:ifLoggedIn>
			                <sec:ifNotLoggedIn>
			                	<li class="divider-vertical"></li>
								<li><g:link controller="register" action="create"><i class="icon-star"></i> Register</g:link></li>
								
								<li class="divider-vertical"></li>
								<li<%= request.forwardURI == "${createLink(uri: '/login/auth')}" ? ' class="active"' : '' %>><g:link controller='login' action='auth'><i class="icon-circle-arrow-right"></i> Login</g:link></li>
			                </sec:ifNotLoggedIn>
			                
			                <li class="divider-vertical"></li>
		                </ul>
		            </div>
				</div>
			</div>
		</nav>

		<div class="container-fluid">
			<g:layoutBody/>

			<hr>

			<footer>
				<p>Enlist - &copy; 2012 GreatBizTools, LLC All rights reserved.</p>
			</footer>
		</div>

		<r:layoutResources/>

	</body>
</html>