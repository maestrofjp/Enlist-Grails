<%@ page import="enlist.grails.Role" %>



<div class="fieldcontain ${hasErrors(bean: roleInstance, field: 'role', 'error')} required">
	<label for="role">
		<g:message code="role.role.label" default="Role" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="role" required="" value="${roleInstance?.role}"/>
</div>

