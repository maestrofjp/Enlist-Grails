<%@ page import="enlist.grails.PointTransaction" %>



<div class="fieldcontain ${hasErrors(bean: pointTransactionInstance, field: 'txnType', 'error')} ">
	<label for="txnType">
		<g:message code="pointTransaction.txnType.label" default="Txn Type" />
		
	</label>
	<g:select name="txnType" from="${pointTransactionInstance.constraints.txnType.inList}" value="${pointTransactionInstance?.txnType}" valueMessagePrefix="pointTransaction.txnType" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pointTransactionInstance, field: 'acctOwner', 'error')} required">
	<label for="acctOwner">
		<g:message code="pointTransaction.acctOwner.label" default="Acct Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="acctOwner" name="acctOwner.id" from="${enlist.grails.User.list()}" optionKey="id" required="" value="${pointTransactionInstance?.acctOwner?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pointTransactionInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="pointTransaction.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="amount" required="" value="${fieldValue(bean: pointTransactionInstance, field: 'amount')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pointTransactionInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="pointTransaction.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${pointTransactionInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pointTransactionInstance, field: 'txnDate', 'error')} required">
	<label for="txnDate">
		<g:message code="pointTransaction.txnDate.label" default="Txn Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="txnDate" precision="day"  value="${pointTransactionInstance?.txnDate}"  />
</div>

