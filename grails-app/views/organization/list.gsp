
<%@ page import="enlist.grails.Organization" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'organization.label', default: 'Organization')}" />
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
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
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
						
							<g:sortableColumn property="name" title="${message(code: 'organization.name.label', default: 'Name')}" />
						
							<g:sortableColumn property="pointName" title="${message(code: 'organization.pointName.label', default: 'Point Name')}" />
						
							<g:sortableColumn property="pointValueDefault" title="${message(code: 'organization.pointValueDefault.label', default: 'Point Value Default')}" />
						
							<g:sortableColumn property="emailSend" title="${message(code: 'organization.emailSend.label', default: 'Email Send')}" />
						
							<g:sortableColumn property="emailSender" title="${message(code: 'organization.emailSender.label', default: 'Email Sender')}" />
						
							<th class="header"><g:message code="organization.address.label" default="Address" /></th>
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${organizationInstanceList}" var="organizationInstance">
						<tr>
						
							<td>${fieldValue(bean: organizationInstance, field: "name")}</td>
						
							<td>${fieldValue(bean: organizationInstance, field: "pointName")}</td>
						
							<td>${fieldValue(bean: organizationInstance, field: "pointValueDefault")}</td>
						
							<td><g:formatBoolean boolean="${organizationInstance.emailSend}" /></td>
						
							<td>${fieldValue(bean: organizationInstance, field: "emailSender")}</td>
						
							<td>${fieldValue(bean: organizationInstance, field: "address")}</td>
						
							<td class="link">
								<g:link action="show" id="${organizationInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${organizationInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
