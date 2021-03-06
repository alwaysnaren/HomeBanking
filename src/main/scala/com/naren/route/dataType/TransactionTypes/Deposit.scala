package com.naren.route.dataType.TransactionTypes

import com.naren.route.constants.KeyWords.{CREDIT_REWARD, DEPOSIT}
import com.naren.route.dataStructure.Record
import com.naren.route.dataType.Transaction

case class Deposit(
                    txnID: Long,
                    dateTime: String,
                    stream: String,
                    source: String,
                    purpose: String,
                    vendor: String,
                    amount: Double,
                    accountID: Long,
                    var tillDate: Double) extends Record[Deposit]{

  def toTransaction(latest: Transaction = null): Transaction = {
    if(latest == null) {
      return new Transaction(
        txnID,dateTime,DEPOSIT,stream,source,vendor,amount,accountID,
        amount,0,amount,0
      )
    }
    stream match {
      case CREDIT_REWARD =>
        new Transaction(
          txnID,dateTime,DEPOSIT,stream,source,vendor,amount,accountID,
          latest.balance,latest.creditDebt - amount,latest.credit + amount,latest.debtsPaid + amount
        )
      case _ =>
        new Transaction(
          txnID,dateTime,DEPOSIT,stream,source,vendor,amount,accountID,
          latest.balance + amount,latest.creditDebt,latest.credit + amount,latest.debtsPaid
        )
    }
  }

}