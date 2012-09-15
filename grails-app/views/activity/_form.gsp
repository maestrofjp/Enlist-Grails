<%@ page import="enlist.grails.Activity" %>



<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'coordinators', 'error')} ">
	<label for="coordinators">
		<g:message code="activity.coordinators.label" default="Coordinators" />
		
	</label>
	<g:select name="coordinators" from="${enlist.grails.User.list()}" multiple="multiple" optionKey="id" size="5" value="${activityInstance?.coordinators*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="activity.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${activityInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'endDate', 'error')} required">
	<label for="endDate">
		<g:message code="activity.endDate.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endDate" value="${activityInstance?.endDate}" precision="minute"  />
</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="activity.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${enlist.grails.Event.list()}" optionKey="id" required="" value="${activityInstance?.event?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="activity.location.label" default="Location" />
		
	</label>
	<g:textField name="location" value="${activityInstance?.location}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'numPeopleNeeded', 'error')} required">
	<label for="numPeopleNeeded">
		<g:message code="activity.numPeopleNeeded.label" default="Num People Needed" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="numPeopleNeeded" required="" value="${fieldValue(bean: activityInstance, field: 'numPeopleNeeded')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'points', 'error')} required">
	<label for="points">
		<g:message code="activity.points.label" default="Points" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="points" required="" value="${fieldValue(bean: activityInstance, field: 'points')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'pointsType', 'error')} ">
	<label for="pointsType">
		<g:message code="activity.pointsType.label" default="Points Type" />
		
	</label>
	<g:textField name="pointsType" value="${activityInstance?.pointsType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="activity.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startDate" value="${activityInstance?.startDate}" precision="minute" />
</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="activity.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${activityInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'volunteers', 'error')} ">
	<label for="volunteers">
		<g:message code="activity.volunteers.label" default="Volunteers" />
		
	</label>
	<g:select name="volunteers" from="${enlist.grails.User.list()}" multiple="multiple" optionKey="id" size="5" value="${activityInstance?.volunteers*.id}" class="many-to-many"/>
</div>

