
<%@ page import="enlist.grails.CatalogItem" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'catalogItem.label', default: 'CatalogItem')}" />
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
						
							<g:sortableColumn property="name" title="${message(code: 'catalogItem.name.label', default: 'Name')}" />
						
							<g:sortableColumn property="points" title="${message(code: 'catalogItem.points.label', default: 'Points')}" />
						
							<th class="header"><g:message code="catalogItem.category.label" default="Category" /></th>
						
							<g:sortableColumn property="available" title="${message(code: 'catalogItem.available.label', default: 'Available')}" />
						
							<g:sortableColumn property="photo" title="${message(code: 'catalogItem.photo.label', default: 'Photo')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${catalogItemInstanceList}" var="catalogItemInstance">
						<tr>
						
							<td>${fieldValue(bean: catalogItemInstance, field: "name")}</td>
						
							<td>${fieldValue(bean: catalogItemInstance, field: "points")}</td>
						
							<td>${fieldValue(bean: catalogItemInstance, field: "category")}</td>
						
							<td><g:formatBoolean boolean="${catalogItemInstance.available}" /></td>
						
							<td>${fieldValue(bean: catalogItemInstance, field: "photo")}</td>
						
							<td class="link">
								<g:link action="show" id="${catalogItemInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${catalogItemInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
