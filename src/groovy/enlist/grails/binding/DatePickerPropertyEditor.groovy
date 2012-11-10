package enlist.grails.binding

import java.beans.PropertyEditorSupport
import org.codehaus.groovy.grails.web.binding.StructuredPropertyEditor
import org.apache.commons.lang.StringUtils
import java.text.SimpleDateFormat

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 11/10/12
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
class DatePickerPropertyEditor extends PropertyEditorSupport implements StructuredPropertyEditor {
    private static final String DATE_FORMAT = 'MM/dd/yyyy'
    private static final String DATE_TIME_FORMAT = 'MM/dd/yyyy h:mm a'
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT)
    private SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATE_TIME_FORMAT)
    public DatePickerPropertyEditor() {
        dateFormatter = new SimpleDateFormat(DATE_FORMAT)
        dateTimeFormatter = new SimpleDateFormat(DATE_TIME_FORMAT)
    }
    public DatePickerPropertyEditor(String dateFormat, String dateTimeFormat) {
        dateFormatter = new SimpleDateFormat(dateFormat)
        dateTimeFormatter = new SimpleDateFormat(dateTimeFormat)
    }

    @Override
    List getRequiredFields() { ["date"]}

    @Override
    List getOptionalFields() {["time"]}

    @Override
    Object assemble(Class type, Map fieldValues) throws IllegalArgumentException {
        if (fieldValues.isEmpty() || fieldValues.every {StringUtils.isEmpty(it.value) }) return null
        requiredFields.each {
            if (!fieldValues."$it") {
                throw new IllegalArgumentException("Can't populate a $type without a $it")
            }
        }
        String dateStr, timeStr
        requiredFields.each { dateStr = fieldValues[it] }
        optionalFields.each { timeStr = fieldValues[it] }
        if(StringUtils.isEmpty(timeStr)) {
            return dateFormatter.parse(dateStr)
        }
        return dateTimeFormatter.parse(dateStr + ' ' + timeStr)
    }
    String getAsText() {
        if(!value) return null
        try {
            return dateFormatter.format(value)
        } catch(Exception e) {
            return dateTimeFormatter.format(value)
        }
    }

    void setAsText(String text) {
        if(!text) value = null
        try {
            value = dateFormatter.parse(text)
        } catch(Exception e) {
            value = dateTimeFormatter.parse(text)
        }
    }
}