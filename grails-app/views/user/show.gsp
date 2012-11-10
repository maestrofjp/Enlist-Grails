
<%@ page import="enlist.grails.User" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
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
				
					<g:if test="${userInstance?.firstName}">
						<dt><g:message code="user.firstName.label" default="First Name" /></dt>
						
							<dd><g:fieldValue bean="${userInstance}" field="firstName"/></dd>
						
					</g:if>
				
					<g:if test="${userInstance?.lastName}">
						<dt><g:message code="user.lastName.label" default="Last Name" /></dt>
						
							<dd><g:fieldValue bean="${userInstance}" field="lastName"/></dd>
						
					</g:if>
				
					<g:if test="${userInstance?.email}">
						<dt><g:message code="user.email.label" default="Email" /></dt>
						
							<dd><g:fieldValue bean="${userInstance}" field="email"/></dd>
						
					</g:if>
				
					<g:if test="${userInstance?.address}">
						<dt><g:message code="user.address.label" default="Address" /></dt>
						
							<dd><g:link controller="address" action="show" id="${userInstance?.address?.id}">${userInstance?.address?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${userInstance?.chapter}">
						<dt><g:message code="user.chapter.label" default="Chapter" /></dt>
						
							<dd><g:link controller="chapter" action="show" id="${userInstance?.chapter?.id}">${userInstance?.chapter?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${userInstance?.phone}">
						<dt><g:message code="user.phone.label" default="Phone" /></dt>
						
							<dd><g:fieldValue bean="${userInstance}" field="phone"/></dd>
						
					</g:if>
				
					<g:if test="${userInstance?.status}">
						<dt><g:message code="user.status.label" default="Status" /></dt>
						
							<dd><g:link controller="status" action="show" id="${userInstance?.status?.id}">${userInstance?.status?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${userInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${userInstance?.id}">
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
