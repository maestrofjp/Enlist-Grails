package enlist.grails

import org.apache.commons.lang.StringUtils


class DynamicSendMailJob {
    static triggers = {
    }
    def activityService
    def execute(context) {
        String recipient = context.mergedJobDataMap.get("to")
        String title = context.mergedJobDataMap.get("title")
        String content = context.mergedJobDataMap.get("content")
        println "${recipient} ${title} ${content}"
        if(!StringUtils.isEmpty(recipient) && !StringUtils.isEmpty(title) && !StringUtils.isEmpty(content))
            activityService.sendSingleMail(recipient, title,content)
    }
}
