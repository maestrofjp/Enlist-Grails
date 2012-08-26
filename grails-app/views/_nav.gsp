<div class="nav-collapse">
    <ul class="nav">
        <g:each var="navItem" in="${areas}">
            <li <%= controllerName == navItem.controller ? ' class="active"' : ''  %>
                ><g:link controller="${navItem.controller}">${navItem.name}</g:link></li>
        </g:each>
    </ul>
</div>