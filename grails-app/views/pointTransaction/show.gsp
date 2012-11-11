
<%@ page import="enlist.grails.PointTransaction" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'pointTransaction.label', default: 'PointTransaction')}" />
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
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${pointTransactionInstance?.txnType}">
						<dt><g:message code="pointTransaction.txnType.label" default="Txn Type" /></dt>
						
							<dd><g:fieldValue bean="${pointTransactionInstance}" field="txnType"/></dd>
						
					</g:if>
				
					<g:if test="${pointTransactionInstance?.acctOwner}">
						<dt><g:message code="pointTransaction.acctOwner.label" default="Acct Owner" /></dt>
						
							<dd><g:link controller="user" action="show" id="${pointTransactionInstance?.acctOwner?.id}">${pointTransactionInstance?.acctOwner?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${pointTransactionInstance?.amount}">
						<dt><g:message code="pointTransaction.amount.label" default="Amount" /></dt>
						
							<dd><g:fieldValue bean="${pointTransactionInstance}" field="amount"/></dd>
						
					</g:if>
				
					<g:if test="${pointTransactionInstance?.description}">
						<dt><g:message code="pointTransaction.description.label" default="Description" /></dt>
						
							<dd><g:fieldValue bean="${pointTransactionInstance}" field="description"/></dd>
						
					</g:if>
				
					<g:if test="${pointTransactionInstance?.txnDate}">
						<dt><g:message code="pointTransaction.txnDate.label" default="Txn Date" /></dt>
						
							<dd><g:formatDate date="${pointTransactionInstance?.txnDate}" /></dd>
						
					</g:if>
				
				</dl>

				%{--<g:form>--}%
					%{--<g:hiddenField name="id" value="${pointTransactionInstance?.id}" />--}%
					%{--<div class="form-actions">--}%
						%{--<g:link class="btn" action="edit" id="${pointTransactionInstance?.id}">--}%
							%{--<i class="icon-pencil"></i>--}%
							%{--<g:message code="default.button.edit.label" default="Edit" />--}%
						%{--</g:link>--}%
						%{--<button class="btn btn-danger" type="submit" name="_action_delete">--}%
							%{--<i class="icon-trash icon-white"></i>--}%
							%{--<g:message code="default.button.delete.label" default="Delete" />--}%
						%{--</button>--}%
					%{--</div>--}%
				%{--</g:form>--}%

			</div>

		</div>
	</body>
</html>
