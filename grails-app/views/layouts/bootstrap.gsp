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

                    <enlist:adminNav />

		            <div class="pull-right">
		            	<ul class="nav">
			                <sec:ifLoggedIn>
			                	<li class="dropdown">
			                		<a href="#" class="dropdown-toggle" data-toggle="dropdown"><b class="caret"></b> <i class="icon-cog"></i>Settings</a>
			                		
			                		<ul class="dropdown-menu">
			                			<li><g:link controller="quartz" action="list">Quartz Jobs</g:link>
			                		</ul>
			                	</li>
			                	<li><g:link controller="logout" action="index"><i class="icon-user"></i> My Profile</g:link></li>
			                	<li><g:link controller="logout" action="index"><i class="icon-remove-circle"></i> Logout</g:link></li>
			                </sec:ifLoggedIn>
			                <sec:ifNotLoggedIn>
			                	<li><g:link controller="logout" action="index"><i class="icon-star"></i> Register</g:link></li>
			                   <li<%= request.forwardURI == "${createLink(uri: '/login/auth')}" ? ' class="active"' : '' %>><g:link controller='login' action='auth'><i class="icon-circle-arrow-right"></i> Login</g:link></li>
			                </sec:ifNotLoggedIn>
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