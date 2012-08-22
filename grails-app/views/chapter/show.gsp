
<%@ page import="enlist.grails.Chapter" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'chapter.label', default: 'Chapter')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-chapter" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-chapter" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list chapter">
			
				<g:if test="${chapterInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="chapter.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${chapterInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${chapterInstance?.location}">
				<li class="fieldcontain">
					<span id="location-label" class="property-label"><g:message code="chapter.location.label" default="Location" /></span>
					
						<span class="property-value" aria-labelledby="location-label"><g:fieldValue bean="${chapterInstance}" field="location"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${chapterInstance?.created}">
				<li class="fieldcontain">
					<span id="created-label" class="property-label"><g:message code="chapter.created.label" default="Created" /></span>
					
						<span class="property-value" aria-labelledby="created-label"><g:formatDate date="${chapterInstance?.created}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${chapterInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="chapter.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:link controller="status" action="show" id="${chapterInstance?.status?.id}">${chapterInstance?.status?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${chapterInstance?.users}">
				<li class="fieldcontain">
					<span id="users-label" class="property-label"><g:message code="chapter.users.label" default="Users" /></span>
					
						<g:each in="${chapterInstance.users}" var="u">
						<span class="property-value" aria-labelledby="users-label"><g:link controller="user" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${chapterInstance?.id}" />
					<g:link class="edit" action="edit" id="${chapterInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
