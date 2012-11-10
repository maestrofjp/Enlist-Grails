
<%@ page import="enlist.grails.Event" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">

			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link class="list" action="list">
								<i class="icon-list"></i>
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
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>

					<g:if test="${eventInstance?.name}">
						<dt><g:message code="event.name.label" default="Name" /></dt>

							<dd><g:fieldValue bean="${eventInstance}" field="name"/></dd>

					</g:if>

					<g:if test="${eventInstance?.location}">
						<dt><g:message code="event.location.label" default="Location" /></dt>

							<dd><g:fieldValue bean="${eventInstance}" field="location"/></dd>

					</g:if>

					<g:if test="${eventInstance?.end}">
						<dt><g:message code="event.end.label" default="End" /></dt>

							<dd><g:formatDate date="${eventInstance?.end}" /></dd>

					</g:if>

					<g:if test="${eventInstance?.start}">
						<dt><g:message code="event.start.label" default="Start" /></dt>

							<dd><g:formatDate date="${eventInstance?.start}" /></dd>

					</g:if>

					<g:if test="${eventInstance?.status}">
						<dt><g:message code="event.status.label" default="Status" /></dt>

							<dd><g:link controller="status" action="show" id="${eventInstance?.status?.id}">${eventInstance?.status?.encodeAsHTML()}</g:link></dd>

					</g:if>

				</dl>

				<g:form>
					<g:hiddenField name="id" value="${eventInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${eventInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
