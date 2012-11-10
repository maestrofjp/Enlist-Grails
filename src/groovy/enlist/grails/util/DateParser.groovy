package enlist.grails.util

import java.text.SimpleDateFormat
import org.apache.commons.lang.StringUtils

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 11/10/12
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */
class DateParser {
    public static final String DEFAULT_DATE_FORMAT = 'MM/dd/yyyy'
    public static final String DEFAULT_DATE_TIME_FORMAT = 'MM/dd/yyyy h:mm a'
    private static Map<String, SimpleDateFormat> dateFormatMap = new HashMap<String, SimpleDateFormat>()

    static String print(String format, Date value ) {
        if(!value) return null
        if(!dateFormatMap.containsKey(format)) {
            dateFormatMap.put(format, new SimpleDateFormat(format))
        }
        SimpleDateFormat fmt = dateFormatMap.get(format)
        fmt.format(value)
    }
    static String printDefault(Date value ) { print(DEFAULT_DATE_FORMAT, value) }
    static String printDateTimeDefault(Date value ) { print(DEFAULT_DATE_TIME_FORMAT, value) }

    static Date parse(String format, String value ) {
        if(StringUtils.isEmpty(value)) return null
        if(!dateFormatMap.containsKey(format)) {
            dateFormatMap.put(format, new SimpleDateFormat(format))
        }
        SimpleDateFormat fmt = dateFormatMap.get(format)
        fmt.parse(value)
    }
    static Date parseDateDefault(String value ) { parse(DEFAULT_DATE_FORMAT, value) }
    static Date parseDateTimeDefault(String value ) { parse(DEFAULT_DATE_TIME_FORMAT, value) }
}
