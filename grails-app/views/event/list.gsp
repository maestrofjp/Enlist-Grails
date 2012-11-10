
<%@ page import="enlist.grails.Event" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li class="active">
							<g:link class="list" action="list">
								<i class="icon-list icon-white"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<enlist:isAdmin>
							<li>
								<g:link class="create" action="create">
									<i class="icon-plus"></i>
									<g:message code="default.create.label" args="[entityName]" />
								</g:link>
							</li>
						</enlist:isAdmin>
					</ul>
				</div>
			</div>

			<div class="span9">
				
				<div class="page-header">
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
						
							<g:sortableColumn property="name" title="${message(code: 'event.name.label', default: 'Name')}" />
						
							<g:sortableColumn property="location" title="${message(code: 'event.location.label', default: 'Location')}" />
						
							<g:sortableColumn property="end" title="${message(code: 'event.end.label', default: 'End')}" />
						
							<g:sortableColumn property="start" title="${message(code: 'event.start.label', default: 'Start')}" />
						
							<th class="header"><g:message code="event.status.label" default="Status" /></th>
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${eventInstanceList}" var="eventInstance">
						<tr>
						
							<td>${fieldValue(bean: eventInstance, field: "name")}</td>
						
							<td>${fieldValue(bean: eventInstance, field: "location")}</td>
						
							<td><g:formatDate date="${eventInstance.end}" /></td>
						
							<td><g:formatDate date="${eventInstance.start}" /></td>
						
							<td>${fieldValue(bean: eventInstance, field: "status")}</td>
						
							<td class="link">
								<g:link action="show" id="${eventInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${eventInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
