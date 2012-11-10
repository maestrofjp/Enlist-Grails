<%@ page import="enlist.grails.Event" %>
<!doctype html>
<html>
<head>
	<meta name="layout" content="bootstrap">
	<title><g:message code="registration.create" /></title>
</head>
<body>
	<div class="row-fluid">

		<div class="span12">

			<div class="page-header">
				<h1><g:message code="registration.create" /></h1>
			</div>

			<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
			</g:if>

			<g:hasErrors bean="${params}">
				<bootstrap:alert class="alert-error">
					<ul>
						<g:eachError bean="${params}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
						</g:eachError>
					</ul>
				</bootstrap:alert>
			</g:hasErrors>

			<fieldset>
				<g:form class="form-horizontal" action="submit" >
					<f:field bean="user" property="firstName"/>
					<f:field bean="user" property="lastName"/>
					<f:field bean="user" property="username"/>
					<f:field bean="user" property="password"/>
					<f:field bean="user" property="email"/>
					<f:field bean="user" property="phone"/>
					<f:field bean="user" property="address"/>

						<div class="form-actions">
							<button type="submit" class="btn btn-primary">
								<i class="icon-ok icon-white"></i>
								<g:message code="default.button.submit.label" default="Submit" />
							</button>
						</div>
				</g:form>
			</fieldset>

		</div>

	</div>
</body>
</html>
