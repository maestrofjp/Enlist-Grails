<%@ page import="enlist.grails.Activity" %>

<div class="control-group ${hasErrors(bean: activityInstance, field: 'event', 'error')} required">
	<label for="event" class="control-label">
		<g:message code="activity.event.label" default="Event" />
	</label>
	<div class="controls">
		<g:select id="event" name="event.id" from="${enlist.grails.Event.list()}" optionKey="id" required="" value="${activityInstance?.event?.id}" class="many-to-one"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: activityInstance, field: 'title', 'error')} required">
	<label for="title" class="control-label">
		<g:message code="activity.title.label" default="Title" />
	</label>
	<div class="controls">
		<g:textField name="title" value="${activityInstance?.title}"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: activityInstance, field: 'location', 'error')} ">
	<label for="location" class="control-label">
		<g:message code="activity.location.label" default="Location" />
	</label>
	<div class="controls">
		<g:textField name="location" value="${activityInstance?.location}"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: activityInstance, field: 'description', 'error')} required">
	<label for="description" class="control-label">
		<g:message code="activity.description.label" default="Description" />
	</label>
	<div class="controls">
		<g:textArea name="description" value="${activityInstance?.description}"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: activityInstance, field: 'coordinators', 'error')} ">
	<label for="coordinators" class="control-label">
		<g:message code="activity.coordinators.label" default="Coordinators" />
	</label>
	<div class="controls">
		<g:select name="coordinators" from="${enlist.grails.User.list()}" multiple="multiple" optionKey="id" size="5" value="${activityInstance?.coordinators*.id}" class="many-to-many"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: activityInstance, field: 'startDate', 'error')} required">
	<label for="startDate" class="control-label">
		<g:message code="activity.startDate.label" default="Start Date" />

	</label>
	<div class="controls">
		<g:datePicker name="startDate" value="${activityInstance?.startDate}" precision="minute" years="${(1900 + (new Date().year))..(1900+ (new Date() + (5 * 365)).year)}" />
	</div>
</div>

<div class="control-group ${hasErrors(bean: activityInstance, field: 'endDate', 'error')} required">
	<label for="endDate" class="control-label">
		<g:message code="activity.endDate.label" default="End Date" />

	</label>
	<div class="controls">
		<g:datePicker name="endDate" value="${activityInstance?.endDate}" precision="minute" years="${(1900 + (new Date().year))..(1900+ (new Date() + (5 * 365)).year)}" />
	</div>
</div>



<div class="control-group ${hasErrors(bean: activityInstance, field: 'numPeopleNeeded', 'error')} required">
	<label for="numPeopleNeeded" class="control-label">
		<g:message code="activity.numPeopleNeeded.label" default="Num People Needed" />

	</label>
	<div class="controls">
		<g:field type="number" name="numPeopleNeeded" required="" value="${fieldValue(bean: activityInstance, field: 'numPeopleNeeded')}"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: activityInstance, field: 'points', 'error')} required">
	<label for="points" class="control-label">
		<g:message code="activity.points.label" default="Points" />

	</label>
	<div class="controls">
		<g:field type="number" name="points" required="" value="${fieldValue(bean: activityInstance, field: 'points')}"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: activityInstance, field: 'pointsType', 'error')} ">
	<label for="pointsType" class="control-label">
		<g:message code="activity.pointsType.label" default="Points Type" />
	</label>
	<div class="controls">
		<g:textField name="pointsType" value="${activityInstance?.pointsType}"/>
	</div>
</div>

