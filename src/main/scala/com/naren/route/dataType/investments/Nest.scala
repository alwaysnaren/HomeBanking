package com.naren.route.dataType.investments

import com.naren.route.dataStructure.Record
import com.naren.route.dataType.TransactionTypes.{CheckingTransaction, Deposit}
import com.naren.route.constants.KeyWords.{CREDIT,DEBIT}

case class Nest(
                 txnID: Long,
                 dateTime: String,
                 transactionType: String,
                 assetID: Long,
                 stream: String,
                 vendor: String,
                 amount: Double,
                 accountID: Long,
                 invested: Double,
                 returns: Double
               ) extends Record[Nest]{

  def fromDep(dep:Deposit): Nest =
    Nest(
      dep.txnID,dep.dateTime,CREDIT,assetID,dep.source,dep.vendor,dep.amount,dep.accountID,invested,
      returns + dep.amount
    )

  def fromDeb(deb: CheckingTransaction): Nest =
    Nest(
      deb.txnID,deb.dateTime,DEBIT,assetID,deb.source,deb.vendor,deb.amount,deb.accountID,
      invested + deb.amount, returns
    )
}
