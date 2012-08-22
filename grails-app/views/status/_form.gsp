<%@ page import="enlist.grails.Status" %>



<div class="fieldcontain ${hasErrors(bean: statusInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="status.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="status" required="" value="${statusInstance?.status}"/>
</div>

