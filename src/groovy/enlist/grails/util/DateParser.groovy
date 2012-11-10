package enlist.grails.util

import java.text.SimpleDateFormat

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
        String res = fmt.format(value)
        println "print : ${res}"
        res
    }
    static String printDefault(Date value ) { print(DEFAULT_DATE_FORMAT, value) }
}
