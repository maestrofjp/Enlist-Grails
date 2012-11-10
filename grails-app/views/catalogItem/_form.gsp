<%@ page import="enlist.grails.CatalogItem" %>



<div class="fieldcontain ${hasErrors(bean: catalogItemInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="catalogItem.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${catalogItemInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: catalogItemInstance, field: 'points', 'error')} required">
	<label for="points">
		<g:message code="catalogItem.points.label" default="Points" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="points" required="" value="${fieldValue(bean: catalogItemInstance, field: 'points')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: catalogItemInstance, field: 'category', 'error')} required">
	<label for="category">
		<g:message code="catalogItem.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="category" name="category.id" from="${enlist.grails.CatalogItemCategory.list()}" optionKey="id" required="" value="${catalogItemInstance?.category?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: catalogItemInstance, field: 'available', 'error')} ">
	<label for="available">
		<g:message code="catalogItem.available.label" default="Available" />
		
	</label>
	<g:checkBox name="available" value="${catalogItemInstance?.available}" />
</div>

<div class="fieldcontain ${hasErrors(bean: catalogItemInstance, field: 'photo', 'error')} required">
	<label for="photo">
		<g:message code="catalogItem.photo.label" default="Photo" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="photo" name="photo" />
</div>

