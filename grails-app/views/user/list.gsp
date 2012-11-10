
<%@ page import="enlist.grails.User" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
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

                <div class="search-item">
                    <form class="form-search" action="search">
                        <input type="text" class="input-medium search-query" name="q">
                        <button type="submit" class="btn">Search</button>
                    </form>
                </div>
				
				<table class="table table-striped">
					<thead>
						<tr>
						
							<g:sortableColumn property="firstName" title="${message(code: 'user.firstName.label', default: 'First Name')}" />
						
							<g:sortableColumn property="lastName" title="${message(code: 'user.lastName.label', default: 'Last Name')}" />
						
							<g:sortableColumn property="email" title="${message(code: 'user.email.label', default: 'Email')}" />
						
							<th class="header"><g:message code="user.address.label" default="Address" /></th>
						
							<th class="header"><g:message code="user.chapter.label" default="Chapter" /></th>
						
							<g:sortableColumn property="phone" title="${message(code: 'user.phone.label', default: 'Phone')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${userInstanceList}" var="userInstance">
						<tr>
						
							<td>${fieldValue(bean: userInstance, field: "firstName")}</td>
						
							<td>${fieldValue(bean: userInstance, field: "lastName")}</td>
						
							<td>${fieldValue(bean: userInstance, field: "email")}</td>
						
							<td>${fieldValue(bean: userInstance, field: "address")}</td>
						
							<td>${fieldValue(bean: userInstance, field: "chapter")}</td>
						
							<td>${fieldValue(bean: userInstance, field: "phone")}</td>
						
							<td class="link">
								<g:link action="show" id="${userInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${userInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
