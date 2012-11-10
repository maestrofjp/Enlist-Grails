<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap"/>
		<title>Home</title>
	</head>

	<body>
		<div class="row-fluid">
			<aside id="application-status" class="span3">
				<div class="well sidebar-nav">
					<h3>Upcoming Events</h3>
						
					<ul class="nav nav-list">
						
					</ul>
				</div>
			</aside>

			<section id="main" class="span9">

				<div class="hero-unit">
					<h1>Welcome to Enlist</h1>

					<p>Organization information would go here</p>
				</div>
					
				<div class="row-fluid">
					
					<div class="span6">
						<h2>Featured Activities</h2>
						
						<ul class="nav nav-list">
						
						</ul>
					</div>
					
					<div class="span6">
						<h2>Controllers</h2>
						<ul class="nav nav-list">
							<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
								<li><g:link controller="${c.logicalPropertyName}">${c.naturalName}</g:link></li>
							</g:each>
						</ul>
					</div>

				</div>

			</section>
		</div>
	</body>
</html>
