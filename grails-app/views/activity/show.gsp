
<%@ page import="enlist.grails.util.DateParser; enlist.grails.Activity" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'activity.label', default: 'Activity')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div class="row-fluid">

    <div class="span3">
        <div class="well">
            <ul class="nav nav-list">
                <li class="nav-header">${entityName}</li>
                <li>
                    <g:link class="list" action="list">
                        <i class="icon-list"></i>
                        <g:message code="default.list.label" args="[entityName]" />
                    </g:link>
                </li>
                <g:if test="${isAdmin}">
                    <li>
                        <g:link class="create" action="create">
                            <i class="icon-plus"></i>
                            <g:message code="default.create.label" args="[entityName]" />
                        </g:link>
                    </li>
                </g:if>
            </ul>
        </div>
    </div>

    <div class="span9">

        <div class="page-header">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>


        <dl>
            <g:if test="${activityInstance?.event}">
                <dt><g:message code="activity.event.label" default="Event" /></dt>

                <dd><g:link controller="event" action="show" id="${activityInstance?.event?.id}">${activityInstance?.event?.encodeAsHTML()}</g:link></dd>

            </g:if>
            <g:if test="${activityInstance?.title}">
                <dt><g:message code="activity.title.label" default="Title" /></dt>

                <dd><g:fieldValue bean="${activityInstance}" field="title"/></dd>

            </g:if>

            <g:if test="${activityInstance?.description}">
                <dt><g:message code="activity.description.label" default="Description" /></dt>

                <dd><g:fieldValue bean="${activityInstance}" field="description"/></dd>

            </g:if>
            <g:if test="${activityInstance?.startDate}">
                <dt><g:message code="activity.startDate.label" default="Start Date" /></dt>

                <dd><g:formatDate date="${activityInstance?.startDate}" /></dd>

            </g:if>

            <g:if test="${activityInstance?.endDate}">
                <dt><g:message code="activity.endDate.label" default="End Date" /></dt>

                <dd><g:formatDate date="${activityInstance?.endDate}" /></dd>

            </g:if>
            <g:if test="${activityInstance?.location}">
                <dt><g:message code="activity.location.label" default="Location" /></dt>

                <dd><g:fieldValue bean="${activityInstance}" field="location"/></dd>

            </g:if>

            <g:if test="${activityInstance?.numPeopleNeeded}">
                <dt><g:message code="activity.numPeopleNeeded.label" default="Num People Needed" /></dt>

                <dd><g:fieldValue bean="${activityInstance}" field="numPeopleNeeded"/></dd>

            </g:if>

            <g:if test="${activityInstance?.points}">
                <dt><g:message code="activity.points.label" default="Points" /></dt>

                <dd><g:fieldValue bean="${activityInstance}" field="points"/></dd>

            </g:if>

            <g:if test="${activityInstance?.pointsType}">
                <dt><g:message code="activity.pointsType.label" default="Points Type" /></dt>

                <dd><g:fieldValue bean="${activityInstance}" field="pointsType"/></dd>

            </g:if>

            <g:if test="${activityInstance?.featured}">
                <dt><g:message code="activity.featured.label" default="Featured" /></dt>

                <dd><g:fieldValue bean="${activityInstance}" field="featured"/></dd>

            </g:if>

            <g:if test="${activityInstance?.coordinators}">
                <dt><g:message code="activity.coordinators.label" default="Coordinators" /></dt>

                <g:each in="${activityInstance.coordinators}" var="c">
                    <dd><g:link controller="user" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></dd>
                </g:each>

            </g:if>

            <g:if test="${activityInstance?.volunteers}">
                <dt><g:message code="activity.volunteers.label" default="Volunteers" /></dt>

                <g:each in="${activityInstance.volunteers}" var="v">
                    <dd><g:link controller="user" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></dd>
                </g:each>

            </g:if>

        </dl>
        <g:form>
            <g:hiddenField name="id" value="${activityInstance?.id}" />
            <div id="activity-set-reminder" class="collapse">
                <div class="form-inline">
                    <label for="reminderDate" class="control-label checkbox">
                        <input type="checkbox" <g:if test="${reminderAt}">checked</g:if> id="activity-remindMe" name="remindMe"> <g:message code="activity.reminderDate.label" default="Remind me at" />
                        <div id="startDatePicker" class="input-append date datepicker" data-date="${DateParser.printDefault(reminderAt ?: activityInstance?.startDate)}" data-date-format="mm/dd/yyyy">
                            <input name="reminderDate_date" class="span8" size="16" type="text" value="${DateParser.printDefault(reminderAt ?: activityInstance?.startDate)}" />
                            <span class="add-on"><i class="icon-th"></i></span>
                        </div>
                        <div  class="input-append bootstrap-timepicker-component">
                            <input id="startTimePicker" name="reminderDate_time" type="text" value="${DateParser.printTimeDefault(reminderAt ?: activityInstance?.startDate)}"  class="timepicker-default input-small">
                            <span class="add-on">
                                <i class="icon-time"></i>
                            </span>
                        </div>
                    </label>
                    %{--<g:link class="btn" action="changeReminder" id="${activityInstance?.id}">--}%
                        %{--<i class="icon-star-empty"></i>--}%
                        %{--<g:message code="default.button.confirm.label" default="Confirm" />--}%
                    %{--</g:link>--}%
                    <button class="btn" type="submit" name="_action_changeReminder">
                        <i class="icon-ok"></i>
                    <g:message code="default.button.confirm.label" default="Confirm" />
                    </button>
                </div>
            </div>
        </g:form>
        <g:form>
            <g:hiddenField name="id" value="${activityInstance?.id}" />
            <div class="form-actions">
                <g:if test="${canVolunteer}">
                    <g:if test="${hasSignUp}">
                        <button type="button" class="btn" data-toggle="collapse" data-target="#activity-set-reminder">
                            <i class="icon-time"></i>
                            Set reminder
                        </button>
                        <g:link class="btn" action="cancelSignUp" id="${activityInstance?.id}">
                            <i class="icon-star-empty"></i>
                            <g:message code="default.button.cancelSignUp.label" default="Cancel Sign Up" />
                        </g:link>
                    </g:if>
                    <g:else>
                        <g:link class="btn btn-info" action="signUp" id="${activityInstance?.id}">
                            <i class="icon-star"></i>
                            <strong><g:message code="default.button.signUp.label" default="Sign Up" /></strong>

                        </g:link>
                    </g:else>
                </g:if>
                <g:if test="${isAdmin}">
                    <g:link class="btn" action="edit" id="${activityInstance?.id}">
                        <i class="icon-pencil"></i>
                        <g:message code="default.button.edit.label" default="Edit" />
                    </g:link>
                    <button class="btn btn-danger" type="submit" name="_action_delete">
                        <i class="icon-trash icon-white"></i>
                        <g:message code="default.button.delete.label" default="Delete" />
                    </button>
                </g:if>
            </div>
        </g:form>

    </div>

</div>
<g:if test="${canVolunteer && hasSignUp}">
    <script>
        // if it has value, set defaultTime to 'value'.
        var opts = { defaultTime: 'value'}
        if($('#startTimePicker').val() == "") { defaultTime: 'current'}
        $('#startTimePicker').timepicker(opts).on('change',function(event){
            $('#activity-remindMe').prop('checked', true);
        });
        $('#startDatePicker').datepicker().on('changeDate', function(ev) {
            $('#activity-remindMe').prop('checked', true);
        });
    </script>
</g:if>
</body>
</html>
