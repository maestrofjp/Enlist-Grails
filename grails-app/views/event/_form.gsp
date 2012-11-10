<%@ page import="enlist.grails.util.DateParser; enlist.grails.Event" %>

<div class="control-group ${hasErrors(bean: eventInstance, field: 'status', 'error')} required">
	<label for="status" class="control-label">
		<g:message code="event.status.label" default="Status" />
	</label>
	<div class="controls">
		<g:select id="status" name="status.id" from="${enlist.grails.Status.list()}" optionKey="id" required="" value="${eventInstance?.status?.id}" class="many-to-one"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: eventInstance, field: 'name', 'error')} required">
	<label for="name" class="control-label">
		<g:message code="event.name.label" default="Name" />
	</label>
	<div class="controls">
		<g:textField name="name" required="" value="${eventInstance?.name}"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: eventInstance, field: 'location', 'error')} required">
	<label for="location" class="control-label">
		<g:message code="event.location.label" default="Location" />
	</label>
	<div class="controls">
		<g:textField name="location" required="" value="${eventInstance?.location}"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: eventInstance, field: 'start', 'error')} required">
	<label for="start" class="control-label">
		<g:message code="event.start.label" default="Start Date" />
	</label>
	<div class="controls">
		<div class="input-append date datepicker" data-date="${DateParser.printDefault(eventInstance?.start)}" data-date-format="mm/dd/yyyy">
            <g:hiddenField name="start" value="struct" />
			<input name="start_date" class="span8" size="16" type="text" value="${DateParser.printDefault(eventInstance?.start)}" />
			<span class="add-on"><i class="icon-th"></i></span>
		</div>
	</div>
</div>

<div class="control-group ${hasErrors(bean: eventInstance, field: 'end', 'error')} required">
	<label for="end" class="control-label">
		<g:message code="event.end.label" default="End Date" />
	</label>
	<div class="controls">
		<div class="input-append date datepicker" data-date="${DateParser.printDefault(eventInstance?.end)}" data-date-format="mm/dd/yyyy">
            <g:hiddenField name="end" value="struct" />
			<input name="end_date" class="span8" size="16" type="text" value="${DateParser.printDefault(eventInstance?.end)}" />
			<span class="add-on"><i class="icon-th"></i></span>
		</div>
	</div>
</div>

<script>
	$('.timepicker-default').timepicker();
	$('.datepicker').datepicker();
	/* TODO: Auto-populate the end date with the same date */
	/* TODO: Auto-populate the end time with the same time + 1 hour */
</script>
