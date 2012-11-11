package enlist.grails

import org.codehaus.groovy.grails.commons.ConfigurationHolder
import enlist.grails.util.DateParser


class ActivityReminderJob {
    static triggers = {
        cron name: 'ActivityReminder', cronExpression: ConfigurationHolder.config.batch.ActivityReminderJob
    }

//    static final String BATCH_JOB_NAME = "activityReminder"
//    static final String LAST_ACT_END = "lastActivityReminderDate"

    def activityService
    def execute() {
        println "run ActivityReminderJob"
        activityService.batchSendEmailReminder()
//        BatchJobParameter batchJobParameter = BatchJobParameter.get( BATCH_JOB_NAME, LAST_ACT_END )
//        Date lastActReminderDate = activityService.batchSendEmailReminder(batchJobParameter)
//        if(lastActReminderDate) {
//            if(!batchJobParameter) batchJobParameter = new BatchJobParameter(batchName: BATCH_JOB_NAME, parameterName: LAST_ACT_END)
//            batchJobParameter.value = DateParser.printDateTimeDefault(lastActReminderDate)
//            batchJobParameter.save(failOnError: true, flush: true, validate: false)
//        }
    }
}
