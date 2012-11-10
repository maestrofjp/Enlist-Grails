<%@ page import="enlist.grails.CatalogItemCategory" %>



<div class="fieldcontain ${hasErrors(bean: catalogItemCategoryInstance, field: 'category', 'error')} required">
	<label for="category">
		<g:message code="catalogItemCategory.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="category" required="" value="${catalogItemCategoryInstance?.category}"/>
</div>

