<%@ page import="enlist.grails.Chapter" %>



<div class="fieldcontain ${hasErrors(bean: chapterInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="chapter.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${chapterInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: chapterInstance, field: 'location', 'error')} required">
	<label for="location">
		<g:message code="chapter.location.label" default="Location" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="location" required="" value="${chapterInstance?.location}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: chapterInstance, field: 'created', 'error')} required">
	<label for="created">
		<g:message code="chapter.created.label" default="Created" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="created" precision="day"  value="${chapterInstance?.created}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: chapterInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="chapter.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="status" name="status.id" from="${enlist.grails.Status.list()}" optionKey="id" required="" value="${chapterInstance?.status?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: chapterInstance, field: 'users', 'error')} ">
	<label for="users">
		<g:message code="chapter.users.label" default="Users" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${chapterInstance?.users?}" var="u">
    <li><g:link controller="user" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="user" action="create" params="['chapter.id': chapterInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'user.label', default: 'User')])}</g:link>
</li>
</ul>

</div>

