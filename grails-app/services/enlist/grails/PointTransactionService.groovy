package enlist.grails

import org.springframework.transaction.annotation.Transactional
import org.apache.commons.lang.StringUtils

class PointTransactionService {

    static transactional = false
    @Transactional
    def transfer(User from, User to, Integer point, String description) {
        println "Transfer ${point} points from ${from} to ${to}. Description : ${description}"
        Date txnDate = new Date()
        PointTransaction txnFrom = new PointTransaction(acctOwner: from, txnDate: txnDate,
                txnType: PointTransaction.TRANSFER, amount: (-1 * point),
                description: StringUtils.isEmpty(description) ? "Transfer to ${to.username}" : description)
        PointTransaction txnTo = new PointTransaction(acctOwner: to, txnDate: txnDate,
                txnType: PointTransaction.TRANSFER, amount: (point),
                description: StringUtils.isEmpty(description) ? "Transfer from ${from.username}" : description)
        saveTxn(txnFrom)
        saveTxn(txnTo)
    }

    @Transactional
    def saveTxn(PointTransaction txn) {
        txn.save(failOnError: true)
        txn.acctOwner.currPoints =  txn.acctOwner.currPoints + txn.amount
        txn.acctOwner.save(validate: false, failOnError: true)
    }
}
