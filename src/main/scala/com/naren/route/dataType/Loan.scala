package com.naren.route.dataType

import com.naren.route.dataStructure.Record
import com.naren.route.dataType.TransactionTypes.CheckingTransaction
import com.naren.route.constants.KeyWords.{DEBIT,LOAN}
import com.naren.route.entries.AssetLoans
import com.naren.route.utils.IDgenerator

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

object Loan {

  def apply(loan: AssetLoans, id: Long): Loan =
    Loan(id, loan.purchaseDate, LOAN, loan.commodity, loan.assetID, loan.lender, loan.loanAmount, 0L, 0.0)
}
