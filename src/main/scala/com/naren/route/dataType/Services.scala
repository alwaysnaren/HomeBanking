package com.naren.route.dataType

import com.naren.route.dataStructure.Record
import com.naren.route.dataType.TransactionTypes.{CCtransaction, CheckingTransaction}

case class Services(
                     txnID: Long,
                     dateTime: String,
                     stream: String,
                     source: String,
                     vendor: String,
                     amount: Double,
                     accountID: Long,
                     tillDate: Double
                   ) extends Record[Services]{

  def fromDeb(deb: CheckingTransaction): Services =
    new Services(
      deb.txnID, deb.dateTime, deb.purpose, deb.source, deb.vendor, deb.amount, deb.accountID,
      tillDate + deb.amount
    )

  def fromCC(cc: CCtransaction): Services =
    new Services(
      cc.txnID, cc.dateTime, cc.stream, cc.source, cc.vendor, cc.amount, cc.accountID,
      tillDate + cc.amount
    )
}

object Services {

  def apply(cc: CCtransaction): Services =
    new Services(
      cc.txnID, cc.dateTime, cc.stream, cc.source, cc.vendor, cc.amount, cc.accountID, cc.amount
    )

}
