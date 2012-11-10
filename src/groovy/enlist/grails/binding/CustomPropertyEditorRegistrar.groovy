package enlist.grails.binding

import org.springframework.beans.PropertyEditorRegistrar
import org.springframework.beans.PropertyEditorRegistry

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 11/10/12
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

    void registerCustomEditors(PropertyEditorRegistry registry) {
        registry.registerCustomEditor(Date.class, new DatePickerPropertyEditor())
    }
}
