<html>
<head>
	<meta name='layout' content='bootstrap'/>
	<title><g:message code="springSecurity.login.title"/></title>
</head>

<body>
<div class="row-fluid">
	<!-- Cheesy way to center a form, but it works for a fluid layout -->
	<div class="span3"></div>
    <div class="span6">
		
		<g:if test='${flash.message}'>
		    <div class="alert alert-error">
		    	<button type="button" class="close" data-dismiss="alert">×</button>
		    	${flash.message}
		    </div>
		</g:if>
		
		<form action='${postUrl}' method='POST' id='loginForm' class='form-horizontal' autocomplete='off'>
			<legend><g:message code="springSecurity.login.header"/></legend>
			
			<div class="control-group">
				<label for='username' class="control-label"><g:message code="springSecurity.login.username.label"/></label>
				<div class="controls">
					<input type='text' class='text_' name='j_username' id='username'/>
				</div>
			</div>
		
			<div class="control-group">
				<label for='password' class="control-label"><g:message code="springSecurity.login.password.label"/></label>
				<div class="controls">
					<input type='password' class='text_' name='j_password' id='password'/>
				</div>
			</div>
		
			<div class="control-group">
				<div class="controls">
					<label for='remember_me' class="checkbox">
						<input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
						<g:message code="springSecurity.login.remember.me.label"/>
					</label>
				</div>
			</div>
			
			<div class="control-group">
				<div class="controls">
					<input type='submit' id="submit" value='${message(code: "springSecurity.login.button")}' class='btn btn-primary'/>
				</div>
			</div>
		</form>
	</div>
	<div class="span3"></div>
</div>
</body>
</html>
