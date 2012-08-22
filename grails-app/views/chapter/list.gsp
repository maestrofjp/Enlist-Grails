
<%@ page import="enlist.grails.Chapter" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'chapter.label', default: 'Chapter')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-chapter" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-chapter" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'chapter.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="location" title="${message(code: 'chapter.location.label', default: 'Location')}" />
					
						<g:sortableColumn property="created" title="${message(code: 'chapter.created.label', default: 'Created')}" />
					
						<th><g:message code="chapter.status.label" default="Status" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${chapterInstanceList}" status="i" var="chapterInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${chapterInstance.id}">${fieldValue(bean: chapterInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: chapterInstance, field: "location")}</td>
					
						<td><g:formatDate date="${chapterInstance.created}" /></td>
					
						<td>${fieldValue(bean: chapterInstance, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${chapterInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
