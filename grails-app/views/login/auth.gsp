<html>
<head>
	<meta name='layout' content='bootstrap'/>
	<title><g:message code="springSecurity.login.title"/></title>
</head>

<body>
<g:if test='${flash.message}'>
	<div class="row-fluid">
		<div class="span3"></div>
		<div class="span6">
		    <div class="alert alert-error">
		    	<button type="button" class="close" data-dismiss="alert">×</button>
		    	${flash.message}
		    </div>
		</div>
		<div class="span3"></div>
	</div>
</g:if>	

<div class="row-fluid">
	<!-- Cheesy way to center a form, but it works for a fluid layout -->
	<div class="span3"></div>
    <div class="span3">
		<h3><g:message code="springSecurity.login.header"/></h3>
		
		<form action='${postUrl}' method='POST' id='loginForm' autocomplete='off'>
		
			<div class="well" style="height:175px;">				
			
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
			</div>
			<div class="form-actions">
				<input type='submit' id="submit" value='${message(code: "springSecurity.login.button")}' class='btn btn-large btn-primary'/>
			</div>
		</form>
	</div>
	<div class="span3">
		<h3>Register</h3>
		<div class="well" style="height:175px;">
			<p>Haven't used Enlist before? Then register to become one of our all-star volunteers.</p>
			<p>We made it simple so we bet you'll get be done in less than 2 minutes!</p>
		</div>
		<div class="form-actions">
			<g:link url="/${meta(name: 'app.name')}" class="btn btn-large btn-primary pull-left">Register</g:link>
		</div>
	</div>
	<div class="span3"></div>
</div>
</body>
</html>
