<%@ page import="enlist.grails.Event" %>



<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="event.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${eventInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'location', 'error')} required">
	<label for="location">
		<g:message code="event.location.label" default="Location" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="location" required="" value="${eventInstance?.location}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'end', 'error')} required">
	<label for="end">
		<g:message code="event.end.label" default="End" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="end" precision="day"  value="${eventInstance?.end}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'start', 'error')} required">
	<label for="start">
		<g:message code="event.start.label" default="Start" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="start" precision="day"  value="${eventInstance?.start}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="event.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="status" name="status.id" from="${enlist.grails.Status.list()}" optionKey="id" required="" value="${eventInstance?.status?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'volunteers', 'error')} ">
	<label for="volunteers">
		<g:message code="event.volunteers.label" default="Volunteers" />
		
	</label>
	<g:select name="volunteers" from="${enlist.grails.User.list()}" multiple="multiple" optionKey="id" size="5" value="${eventInstance?.volunteers*.id}" class="many-to-many"/>
</div>

