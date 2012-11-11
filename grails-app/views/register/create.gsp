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
					<f:field bean="user" property="chapter"/>
					<f:field bean="user" property="firstName"/>
					<f:field bean="user" property="lastName"/>
					<f:field bean="user" property="username"/>
					<f:field bean="user" property="password"/>
					<f:field bean="user" property="email"/>
					<f:field bean="user" property="phone"/>

                    <button type="button" class="btn btn-link" data-toggle="collapse" data-target="#register-address">
                        Fill in address now
                    </button>
                    <div id="register-address" class="collapse">
                        <f:field bean="user" property="address"/>
                    </div>
					%{--<f:field bean="user" property="address"/>--}%

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
    <script>

		$('#username').on('change',function(event){
			var url = '<g:createLink controller="user" action="isUsernameAvailable"/>';

			//Clean up any info already there
			var container = $(this).parents('.control-group');
		    container.removeClass("error").removeClass("success");
			container.find(".help-inline").remove();

			var username = $(this).val();

			container.addClass("info");
			container.find('.controls').append('<span class="help-inline"><r:img uri="/images/spinner.gif" /> </span>');

			$.ajax({
				url: url,
				data: {username:username},
				success: function(data){
					container.removeClass("info");
					container.find(".help-inline").remove();
					if(data.available)
					{
						container.addClass("success");
						container.find('.controls').append('<span class="help-inline">Username available.</span>');
					}
					else
					{
						//Username is taken so warn the user.
						container.addClass("error");
						container.find('.controls').append('<span class="help-inline">Username already taken.</span>');
					}
				},
				error: function(jqXHR,status,error) {
					container.find(".help-inline").remove();
					container.find('.controls').append('<span class="help-inline">Could not check username.</span>');
				}
			});


		});



        var requiredEmbeddedField= "req-emb";
        var $embeddedView = $('#register-address');
        var isInitiallyShown = $embeddedView.hasClass("in");
        $embeddedView.find(":input[required]").each(function(){
            $(this).addClass(requiredEmbeddedField)
            if(isInitiallyShown == false) $(this).removeAttr("required");
        });
        $embeddedView.on('hidden', function () {
            $embeddedView.find("."+requiredEmbeddedField).each(function(){
                $(this).removeAttr("required");
            })
        }).on('shown', function () {
                    $embeddedView.find("."+requiredEmbeddedField).each(function(){
                        $(this).attr("required", "required");
                    })
        });
    </script>
</body>
</html>
