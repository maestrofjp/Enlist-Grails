
<%@ page import="enlist.grails.User" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div class="row-fluid">

    <aside id="application-status" class="span3">
        <div class="well sidebar-nav">
            <h3>Upcoming Events</h3>

            <g:if test="${upcomingEvents}">
                <ul class="nav nav-list">
                    <g:each in="${upcomingEvents}">
                        <li><g:link controller="event" action="show" id="${it.id}">${it.name} (<g:formatDate format="MM-dd-yyyy" date="${it.start}" />)</g:link></li>
                    </g:each>
                </ul>
            </g:if>
            <g:else>
                <p><g:message code="event.noneUpcoming" /></p>
            </g:else>
        </div>
    </aside>

</div>
</body>
</html>
