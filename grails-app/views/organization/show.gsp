
<%@ page import="enlist.grails.Organization" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'organization.label', default: 'Organization')}" />
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
				
					<g:if test="${organizationInstance?.name}">
						<dt><g:message code="organization.name.label" default="Name" /></dt>
						
							<dd><g:fieldValue bean="${organizationInstance}" field="name"/></dd>
						
					</g:if>
				
					<g:if test="${organizationInstance?.pointName}">
						<dt><g:message code="organization.pointName.label" default="Point Name" /></dt>
						
							<dd><g:fieldValue bean="${organizationInstance}" field="pointName"/></dd>
						
					</g:if>
				
					<g:if test="${organizationInstance?.pointValueDefault}">
						<dt><g:message code="organization.pointValueDefault.label" default="Point Value Default" /></dt>
						
							<dd><g:fieldValue bean="${organizationInstance}" field="pointValueDefault"/></dd>
						
					</g:if>
				
					<g:if test="${organizationInstance?.emailSend}">
						<dt><g:message code="organization.emailSend.label" default="Email Send" /></dt>
						
							<dd><g:formatBoolean boolean="${organizationInstance?.emailSend}" /></dd>
						
					</g:if>
				
					<g:if test="${organizationInstance?.emailSender}">
						<dt><g:message code="organization.emailSender.label" default="Email Sender" /></dt>
						
							<dd><g:fieldValue bean="${organizationInstance}" field="emailSender"/></dd>
						
					</g:if>
				
					<g:if test="${organizationInstance?.address}">
						<dt><g:message code="organization.address.label" default="Address" /></dt>
						
							<dd><g:link controller="address" action="show" id="${organizationInstance?.address?.id}">${organizationInstance?.address?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${organizationInstance?.sendEmail}">
						<dt><g:message code="organization.sendEmail.label" default="Send Email" /></dt>
						
							<dd><g:formatBoolean boolean="${organizationInstance?.sendEmail}" /></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${organizationInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${organizationInstance?.id}">
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
