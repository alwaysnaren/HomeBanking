package com.naren.route.dataType

import com.naren.route.dataStructure.Record
import com.naren.route.dataType.TransactionTypes.CheckingTransaction
import com.naren.route.constants.KeyWords.DEBIT

case class Loan(
                 txnID: Long,
                 dateTime: String,
                 TransactionType: String,
                 commodity: String,
                 assetId: Long,
                 vendor: String,
                 amount: Double,
                 accountID: Long,
                 tillDate: Double
               ) extends Record[Loan] {

  def fromDeb(deb: CheckingTransaction): Loan =
    Loan(
      deb.txnID,deb.dateTime,DEBIT,deb.purpose,assetId,deb.vendor,deb.amount,deb.accountID,
      tillDate + deb.amount
    )
}
