package com.naren.route.dataType

import com.naren.route.dataStructure.Record
import com.naren.route.dataType.TransactionTypes.CCtransaction

case class Shopping(
                 txnID: Long,
                 dateTime: String,
                 category: String,
                 stream: String,
                 vendor: String,
                 accountID: Long,
                 amount: Double,
                 tillDate: Double
               ) extends Record[Shopping] {

  def fromCC(cc: CCtransaction): Shopping =
    new Shopping(
      cc.txnID, cc.dateTime, cc.source, cc.purpose, cc.vendor, cc.accountID, cc.amount,
      tillDate + cc.amount
    )
}

object Shopping {

  def apply(cc: CCtransaction): Shopping =
    new Shopping(
      cc.txnID, cc.dateTime, cc.source, cc.purpose, cc.vendor, cc.accountID, cc.amount, cc.amount
    )
}
