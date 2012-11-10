
<%@ page import="enlist.grails.Activity" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'activity.label', default: 'Activity')}" />
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
						
							<g:sortableColumn property="description" title="${message(code: 'activity.description.label', default: 'Description')}" />
						
							<g:sortableColumn property="endDate" title="${message(code: 'activity.endDate.label', default: 'End Date')}" />
						
							<th class="header"><g:message code="activity.event.label" default="Event" /></th>
						
							<g:sortableColumn property="location" title="${message(code: 'activity.location.label', default: 'Location')}" />
						
							<g:sortableColumn property="numPeopleNeeded" title="${message(code: 'activity.numPeopleNeeded.label', default: 'Num People Needed')}" />
						
							<g:sortableColumn property="points" title="${message(code: 'activity.points.label', default: 'Points')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${activityInstanceList}" var="activityInstance">
						<tr>
						
							<td>${fieldValue(bean: activityInstance, field: "description")}</td>
						
							<td><g:formatDate date="${activityInstance.endDate}" /></td>
						
							<td>${fieldValue(bean: activityInstance, field: "event")}</td>
						
							<td>${fieldValue(bean: activityInstance, field: "location")}</td>
						
							<td>${fieldValue(bean: activityInstance, field: "numPeopleNeeded")}</td>
						
							<td>${fieldValue(bean: activityInstance, field: "points")}</td>
						
							<td class="link">
								<g:link action="show" id="${activityInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${activityInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
