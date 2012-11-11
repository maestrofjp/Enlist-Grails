
<%@ page import="enlist.grails.User" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title>Your Dashboard</title>
</head>
<body>
<div class="row-fluid">

    <aside id="application-status" class="span3">
        <div class="well sidebar-nav">
            <h4>Your Points</h4>
            <p>You have <strong>${userInstance.currPoints}</strong> points.</p>
            <button class="btn btn-mini btn-primary">Redeem</button>&nbsp;<button class="btn btn-mini btn-primary">Transfer</button>
        </div>
    </aside>

    <section id="main" class="span9">
        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <h3>Your Registered Events</h3>

        <g:if test="${registeredEvents}">
            <table class="table table-striped">
                <thead>
                <tr>
                    <g:sortableColumn property="event.activity.event.name" title="${message(code: 'event.name.label', default: 'Event')}" />
                    <g:sortableColumn property="activity.title" title="${message(code: 'activity.title.label', default: 'Activity')}" />
                    <g:sortableColumn property="activity.startDate" title="${message(code: 'activity.startDate.label', default: 'Date/Time')}" />
                    <g:sortableColumn property="activity.location" title="${message(code: 'activity.location.label', default: 'Location')}" />
                </tr>
                </thead>
                <tbody>
                <g:each in="${registeredEvents}" var="signup">
                    <tr>
                        <td>${fieldValue(bean: signup.activity.event, field: "name")}</td>
                        <td><g:link controller="activity" action="show" id="${signup.activity.id}">${fieldValue(bean: signup.activity, field: "title")}</g:link></td>
                        <td><g:formatDate type="datetime" format="SHORT" date="${signup.activity.startDate}" /></td>
                        <td>${fieldValue(bean: signup.activity, field: "location")}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p><g:message code="event.noneRegistered" /></p>
        </g:else>

        <h3>Upcoming Events</h3>

        <g:if test="${upcomingEvents}">

            <table class="table table-striped">
                <thead>
                <tr>
                    <g:sortableColumn property="name" title="${message(code: 'event.name.label', default: 'Event')}" />
                    <g:sortableColumn property="start" title="${message(code: 'event.start.label', default: 'Start Date')}" />
                    <g:sortableColumn property="end" title="${message(code: 'event.end.label', default: 'End Date')}" />
                    <g:sortableColumn property="location" title="${message(code: 'event.location.label', default: 'Location')}" />
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${upcomingEvents}" var="event">
                    <tr>
                        <td><g:link controller="event" action="show" id="${event.id}">${fieldValue(bean: event, field: "name")}</g:link></td>
                        <td><g:formatDate type="date" format="SHORT" date="${event.start}" /></td>
                        <td><g:formatDate type="date" format="SHORT" date="${event.end}" /></td>
                        <td>${fieldValue(bean: event, field: "location")}</td>
                        <td class="link">
                            <g:link controller="event" action="volunteer" id="${event.id}" class="btn btn-small btn-success">Volunteer</g:link>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p><g:message code="event.noneUpcoming" /></p>
        </g:else>
    </section>

</div>
</body>
</html>
