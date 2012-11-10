package enlist.grails

class BatchJobParameter implements Serializable{
    String batchName
    String parameterName
    String value

    static mapping = {
        id composite: ['batchName', 'parameterName']
    }
    static BatchJobParameter get( String batchName, String parameterName) {
        find 'from BatchJobParameter where batchName=:batchName and parameterName=:parameterName',
                [batchName: batchName, parameterName: parameterName]
    }

    static constraints = {
    }
}
