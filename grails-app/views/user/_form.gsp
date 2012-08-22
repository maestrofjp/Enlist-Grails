<%@ page import="enlist.grails.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="user.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${userInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="user.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${userInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="user.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${userInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'address1', 'error')} ">
	<label for="address1">
		<g:message code="user.address1.label" default="Address1" />
		
	</label>
	<g:textField name="address1" value="${userInstance?.address1}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'address2', 'error')} ">
	<label for="address2">
		<g:message code="user.address2.label" default="Address2" />
		
	</label>
	<g:textField name="address2" value="${userInstance?.address2}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'chapter', 'error')} required">
	<label for="chapter">
		<g:message code="user.chapter.label" default="Chapter" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="chapter" name="chapter.id" from="${enlist.grails.Chapter.list()}" optionKey="id" required="" value="${userInstance?.chapter?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'city', 'error')} ">
	<label for="city">
		<g:message code="user.city.label" default="City" />
		
	</label>
	<g:textField name="city" value="${userInstance?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'identicaUsername', 'error')} ">
	<label for="identicaUsername">
		<g:message code="user.identicaUsername.label" default="Identica Username" />
		
	</label>
	<g:textField name="identicaUsername" value="${userInstance?.identicaUsername}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'phone', 'error')} ">
	<label for="phone">
		<g:message code="user.phone.label" default="Phone" />
		
	</label>
	<g:textField name="phone" value="${userInstance?.phone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'role', 'error')} required">
	<label for="role">
		<g:message code="user.role.label" default="Role" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="role" name="role.id" from="${enlist.grails.Role.list()}" optionKey="id" required="" value="${userInstance?.role?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="user.state.label" default="State" />
		
	</label>
	<g:textField name="state" value="${userInstance?.state}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="user.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="status" name="status.id" from="${enlist.grails.Status.list()}" optionKey="id" required="" value="${userInstance?.status?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'twitterUsername', 'error')} ">
	<label for="twitterUsername">
		<g:message code="user.twitterUsername.label" default="Twitter Username" />
		
	</label>
	<g:textField name="twitterUsername" value="${userInstance?.twitterUsername}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'zip', 'error')} ">
	<label for="zip">
		<g:message code="user.zip.label" default="Zip" />
		
	</label>
	<g:textField name="zip" value="${userInstance?.zip}"/>
</div>

