
<%@ page import="enlist.grails.PointTransaction" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'pointTransaction.label', default: 'PointTransaction')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div class="row-fluid">

    <div class="span3">
        <div class="well">
            <ul class="nav nav-list">
                <li class="nav-header">${entityName}</li>
                <li class="active">
                    <g:link class="list" action="list">
                        <i class="icon-list icon-white"></i>
                        <g:message code="default.list.label" args="[entityName]" />
                    </g:link>
                </li>
                %{--<li>--}%
                %{--<g:link class="create" action="create">--}%
                %{--<i class="icon-plus"></i>--}%
                %{--<g:message code="default.create.label" args="[entityName]" />--}%
                %{--</g:link>--}%
                %{--</li>--}%
            </ul>
        </div>
    </div>

    <div class="span9">

        <div class="page-header">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>
        <div class="">
            <p>You have <span class="badge badge-success">${currBalance ?: 0}</span> <inf:pluralize count="${currBalance ?: 0}">point</inf:pluralize></p>
            <button class="btn">Transfer</button>
            <button class="btn">Redeem</button>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>


                <g:sortableColumn property="txnDate" defaultOrder="desc" title="${message(code: 'pointTransaction.txnDate.label', default: 'Txn Date')}" />
                <g:sortableColumn property="txnType" title="${message(code: 'pointTransaction.txnType.label', default: 'Txn Type')}" />
                <enlist:isAdmin>
                    <th class="header"><g:message code="pointTransaction.acctOwner.label" default="Acct Owner" /></th>
                </enlist:isAdmin>

                <th class="header"><g:message code="pointTransaction.debit.label" default="Debit Point" /></th>
                <th class="header"><g:message code="pointTransaction.credit.label" default="Credit Point" /></th>

                <g:sortableColumn property="description" title="${message(code: 'pointTransaction.description.label', default: 'Description')}" />

                <th></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${pointTransactionInstanceList}" var="pointTransactionInstance">
                <tr>


                    <td><g:formatDate date="${pointTransactionInstance.txnDate}" /></td>
                    <td>${fieldValue(bean: pointTransactionInstance, field: "txnType")}</td>

                    <enlist:isAdmin>
                        <td>${fieldValue(bean: pointTransactionInstance, field: "acctOwner")}</td>
                    </enlist:isAdmin>

                    <td>${pointTransactionInstance.amount > 0 ? fieldValue(bean: pointTransactionInstance, field: "amount") : 0}</td>

                    <td>${pointTransactionInstance.amount < 0 ? Math.abs(pointTransactionInstance.amount) : 0}</td>

                    <td>${fieldValue(bean: pointTransactionInstance, field: "description")}</td>

                    <td class="link">
                        <g:link action="show" id="${pointTransactionInstance.id}" class="btn btn-small">Show &raquo;</g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
        <div class="pagination">
            <bootstrap:paginate total="${pointTransactionInstanceTotal}" />
        </div>
    </div>

</div>
</body>
</html>
