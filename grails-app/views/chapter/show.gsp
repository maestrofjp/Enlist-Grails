
<%@ page import="enlist.grails.Chapter" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'chapter.label', default: 'Chapter')}" />
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
				
					<g:if test="${chapterInstance?.name}">
						<dt><g:message code="chapter.name.label" default="Name" /></dt>
						
							<dd><g:fieldValue bean="${chapterInstance}" field="name"/></dd>
						
					</g:if>
				
					<g:if test="${chapterInstance?.location}">
						<dt><g:message code="chapter.location.label" default="Location" /></dt>
						
							<dd><g:fieldValue bean="${chapterInstance}" field="location"/></dd>
						
					</g:if>
				
					<g:if test="${chapterInstance?.created}">
						<dt><g:message code="chapter.created.label" default="Created" /></dt>
						
							<dd><g:formatDate date="${chapterInstance?.created}" /></dd>
						
					</g:if>
				
					<g:if test="${chapterInstance?.status}">
						<dt><g:message code="chapter.status.label" default="Status" /></dt>
						
							<dd><g:link controller="status" action="show" id="${chapterInstance?.status?.id}">${chapterInstance?.status?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${chapterInstance?.users}">
						<dt><g:message code="chapter.users.label" default="Users" /></dt>
						
							<g:each in="${chapterInstance.users}" var="u">
							<dd><g:link controller="user" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${chapterInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${chapterInstance?.id}">
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
