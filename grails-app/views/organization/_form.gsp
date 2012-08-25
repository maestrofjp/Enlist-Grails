<%@ page import="enlist.grails.Organization" %>



<div class="fieldcontain ${hasErrors(bean: organizationInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="organization.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${organizationInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: organizationInstance, field: 'pointName', 'error')} ">
	<label for="pointName">
		<g:message code="organization.pointName.label" default="Point Name" />
		
	</label>
	<g:textField name="pointName" value="${organizationInstance?.pointName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: organizationInstance, field: 'pointValueDefault', 'error')} required">
	<label for="pointValueDefault">
		<g:message code="organization.pointValueDefault.label" default="Point Value Default" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="pointValueDefault" required="" value="${fieldValue(bean: organizationInstance, field: 'pointValueDefault')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: organizationInstance, field: 'emailSend', 'error')} ">
	<label for="emailSend">
		<g:message code="organization.emailSend.label" default="Email Send" />
		
	</label>
	<g:checkBox name="emailSend" value="${organizationInstance?.emailSend}" />
</div>

<div class="fieldcontain ${hasErrors(bean: organizationInstance, field: 'emailSender', 'error')} required">
	<label for="emailSender">
		<g:message code="organization.emailSender.label" default="Email Sender" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="emailSender" required="" value="${organizationInstance?.emailSender}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: organizationInstance, field: 'address', 'error')} required">
	<label for="address">
		<g:message code="organization.address.label" default="Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="address" name="address.id" from="${enlist.grails.Address.list()}" optionKey="id" required="" value="${organizationInstance?.address?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: organizationInstance, field: 'sendEmail', 'error')} ">
	<label for="sendEmail">
		<g:message code="organization.sendEmail.label" default="Send Email" />
		
	</label>
	<g:checkBox name="sendEmail" value="${organizationInstance?.sendEmail}" />
</div>

