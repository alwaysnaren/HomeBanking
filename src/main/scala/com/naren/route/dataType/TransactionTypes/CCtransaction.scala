package com.naren.route.dataType.TransactionTypes

import com.naren.route.constants.KeyWords.{CREDIT_CARD,CREDIT,LOAN}
import com.naren.route.dataStructure.Record
import com.naren.route.dataType._
import com.naren.route.entries.AssetLoans


case class CCtransaction(
                       txnID: Long,
                       dateTime: String,
                       stream: String,
                       source: String,
                       purpose: String,
                       vendor: String,
                       amount: Double,
                       accountID: Long,
                       var tillDate: Double
                     ) extends Record[CCtransaction]{

  def toTransaction(latest: Transaction = null): Transaction = {
    if(latest != null) {
      new Transaction(
        txnID,dateTime,CREDIT_CARD,stream,source,vendor,amount,accountID,
        latest.balance,latest.creditDebt + amount,latest.credit,latest.debtsPaid
      )
    } else {
      new Transaction(
        txnID,dateTime,CREDIT_CARD,stream,source,vendor,amount,accountID,
        0,amount,0,0
      )
    }
  }

  def fromAssetLoan(loan: AssetLoans, id: Long): CCtransaction =
    CCtransaction(id,loan.purchaseDate,LOAN,loan.nickName,loan.commodity,loan.lender,loan.loanAmount,0L,tillDate + loan.loanAmount)
}
