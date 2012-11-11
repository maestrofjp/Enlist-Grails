package enlist.grails

import enlist.grails.util.DateParser
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class ActivityPointJob {
//    def timeout = 60 * 1000 * 1
    static triggers = {
        cron name: 'ActivityPoint', cronExpression: ConfigurationHolder.config.batch.ActivityPointJob
    }

    static final String BATCH_JOB_NAME = "activityPoint"
    static final String LAST_ACT_END = "lastActivityEndDate"

    def activityService
    def execute() {
        println "run ActivityPointJob"
        BatchJobParameter batchJobParameter = BatchJobParameter.get( BATCH_JOB_NAME, LAST_ACT_END )
        Date lastActEndDate = activityService.batchCalculateActivityPoints(batchJobParameter)
        if(lastActEndDate) {
            if(!batchJobParameter) batchJobParameter = new BatchJobParameter(batchName: BATCH_JOB_NAME, parameterName: LAST_ACT_END)
            batchJobParameter.value = DateParser.printDateTimeDefault(lastActEndDate)
            batchJobParameter.save(failOnError: true, flush: true, validate: false)
        }
    }
}
