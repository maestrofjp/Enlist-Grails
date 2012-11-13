<div class="nav-collapse">
    <ul class="nav">
        <g:each var="navItem" in="${areas}">
	        <g:if test="${navItem.controller == '/'}">

	            <li <%= controllerName == null ? ' class="active"' : ''  %>>
	            <a href="${createLink(uri: '/')}"><i class="icon-home"></i>  ${navItem.name}</a>
	            </li>
	        </g:if>
	        <g:else>
	            <li <%= controllerName == navItem.controller ? ' class="active"' : ''  %>><g:link controller="${navItem.controller}">${navItem.name}</g:link></li>
	        </g:else>
        </g:each>
    </ul>
</div>