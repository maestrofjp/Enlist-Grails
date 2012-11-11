
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
            <ul class="nav nav-list">
                <g:each in="${registeredEvents}">
                    <li><g:link controller="event" action="show" id="${it.id}">${it.name} (<g:formatDate format="MM-dd-yyyy" date="${it.start}" />)</g:link></li>
                </g:each>
            </ul>
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

                        <td><g:formatDate format="MM-dd-yyyy" date="${event.start}" /></td>

                        <td><g:formatDate format="MM-dd-yyyy" date="${event.end}" /></td>

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
