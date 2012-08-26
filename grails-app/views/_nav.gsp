<div class="nav-collapse">
    <ul class="nav">
        <g:each var="navItem" in="${areas}">
	        <g:if test="${navItem.controller == '/'}">
	            <li <%= controllerName == null ? ' class="active"' : ''  %>><g:link url="/${meta(name: 'app.name')}">${navItem.name}</g:link></li>
	        </g:if>
	        <g:else>
	            <li <%= controllerName == navItem.controller ? ' class="active"' : ''  %>><g:link controller="${navItem.controller}">${navItem.name}</g:link></li>
	        </g:else>
        </g:each>
    </ul>
</div>