
<%@ page import="enlist.grails.CatalogItem" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'catalogItem.label', default: 'CatalogItem')}" />
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
				
					<g:if test="${catalogItemInstance?.name}">
						<dt><g:message code="catalogItem.name.label" default="Name" /></dt>
						
							<dd><g:fieldValue bean="${catalogItemInstance}" field="name"/></dd>
						
					</g:if>
				
					<g:if test="${catalogItemInstance?.points}">
						<dt><g:message code="catalogItem.points.label" default="Points" /></dt>
						
							<dd><g:fieldValue bean="${catalogItemInstance}" field="points"/></dd>
						
					</g:if>
				
					<g:if test="${catalogItemInstance?.category}">
						<dt><g:message code="catalogItem.category.label" default="Category" /></dt>
						
							<dd><g:link controller="catalogItemCategory" action="show" id="${catalogItemInstance?.category?.id}">${catalogItemInstance?.category?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${catalogItemInstance?.available}">
						<dt><g:message code="catalogItem.available.label" default="Available" /></dt>
						
							<dd><g:formatBoolean boolean="${catalogItemInstance?.available}" /></dd>
						
					</g:if>
				
					<g:if test="${catalogItemInstance?.photo}">
						<dt><g:message code="catalogItem.photo.label" default="Photo" /></dt>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${catalogItemInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${catalogItemInstance?.id}">
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
