<%@ page import="enlist.grails.Chapter" %>



<div class="fieldcontain ${hasErrors(bean: chapterInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="chapter.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${chapterInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: chapterInstance, field: 'address', 'error')} required">
	<label for="address">
		<g:message code="chapter.address.label" default="Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="address" name="address.id" from="${enlist.grails.Address.list()}" optionKey="id" required="" value="${chapterInstance?.address?.id}" class="many-to-one"/>
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

