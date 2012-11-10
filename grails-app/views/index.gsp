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

                    <g:if test="${upcomingEvents}">
                        <ul class="nav nav-list">
                            <g:each in="${upcomingEvents}">
                                <li><g:link controller="event" action="show" id="${it.id}">${it.name} (<g:formatDate format="MM-dd-yyyy" date="${it.start}" />)</g:link></li>
                            </g:each>
                        </ul>
                    </g:if>
                    <g:else>
                        <p><g:message code="event.noneUpcoming" />/p>
                    </g:else>
                </div>
			</aside>

			<section id="main" class="span9">

                <g:if test="${flash.message}">
                    <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
                </g:if>

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
