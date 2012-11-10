package enlist.grails

class PointTransaction {
    public static final String TRANSFER = "TXF"
    public static final String VOLUNTEER = "VOL"
    User acctOwner
    Date txnDate
    String txnType
    String description
    Integer amount

    static constraints = {
        txnType inList: [TRANSFER, VOLUNTEER]
    }
}
