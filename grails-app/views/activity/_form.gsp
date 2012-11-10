<%@ page import="enlist.grails.util.DateParser; enlist.grails.Role; enlist.grails.Activity" %>

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
        <button type="button" class="btn" data-toggle="collapse" data-target="#user-location-address">
            Fill in Address
        </button>
	</div>
</div>
<div id="user-location-address" class="collapse">
    <f:field bean="activityInstance" property="locationAddress"/>
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
		<g:select name="coordinators" from="${enlist.grails.UserRole.findAllByAuthority(Role.ACTIVITY_COORDINATOR)}" multiple="multiple" optionKey="id" size="5" value="${activityInstance?.coordinators*.id}" class="many-to-many"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: activityInstance, field: 'startDate', 'error')} required">
	<label for="startDate" class="control-label">
		<g:message code="activity.startDate.label" default="Start Date" />
	</label>
	<div class="controls">
        <g:hiddenField name="startDate" value="struct" />
		<div class="input-append date datepicker" data-date="${DateParser.printDefault(activityInstance?.startDate)}" data-date-format="mm/dd/yyyy">
			<input name="startDate_date" class="span8" size="16" type="text" value="${DateParser.printDefault(activityInstance?.startDate)}" />
			<span class="add-on"><i class="icon-th"></i></span>
		</div>
		<div class="input-append bootstrap-timepicker-component">
		    <input name="startDate_time" type="text" class="timepicker-default input-small">
		    <span class="add-on">
		    	<i class="icon-time"></i>
		    </span>
	    </div>
	</div>
</div>

<div class="control-group ${hasErrors(bean: activityInstance, field: 'endDate', 'error')} required">
	<label for="endDate" class="control-label">
		<g:message code="activity.endDate.label" default="End Date" />
	</label>
	<div class="controls">
        <g:hiddenField name="endDate" value="struct" />
		<div class="input-append date datepicker" data-date="${DateParser.printDefault(activityInstance?.endDate)}" data-date-format="mm/dd/yyyy">
			<input name="endDate_date" class="span8" size="16" type="text" value="${DateParser.printDefault(activityInstance?.endDate)}" />
			<span class="add-on"><i class="icon-th"></i></span>
		</div>
		<div class="input-append bootstrap-timepicker-component">
		    <input name="endDate_time" type="text" class="timepicker-default input-small">
		    <span class="add-on">
		    	<i class="icon-time"></i>
		    </span>
	    </div>
	</div>
</div>

<div class="control-group ${hasErrors(bean: activityInstance, field: 'numPeopleNeeded', 'error')} required">
	<label for="numPeopleNeeded" class="control-label">
		<g:message code="activity.numPeopleNeeded.label" default="People Needed" />
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
		<g:select name="pointsType" 
			value="${activityInstance?.pointsType}" 
			from="${['Hourly','Flat']}"
			noSelection="${['null':'Select one']}" />
	</div>
</div>


<script>
	$('.timepicker-default').timepicker();
	$('.datepicker').datepicker();
	/* TODO: Auto-populate the end date with the same date */
	/* TODO: Auto-populate the end time with the same time + 1 hour */
    var requiredEmbeddedField= "req-emb";
    var $embeddedView = $('#user-location-address');
    var isInitiallyShown = $embeddedView.hasClass("in");
    $embeddedView.find(":input[required]").each(function(){
        $(this).addClass(requiredEmbeddedField)
        if(isInitiallyShown == false) $(this).removeAttr("required");
    });
    $embeddedView.on('hidden', function () {
        $embeddedView.find("."+requiredEmbeddedField).each(function(){
            $(this).removeAttr("required");
        })
    }).on('shown', function () {
        $embeddedView.find("."+requiredEmbeddedField).each(function(){
            $(this).attr("required", "required");
        })
    });
</script>
