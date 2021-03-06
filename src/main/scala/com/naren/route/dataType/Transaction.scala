package com.naren.route.dataType

import com.naren.route.constants.KeyWords.{CREDIT, CREDIT_CARD, CREDIT_REWARD, DEBIT, LOAN,TRANSFER}
import com.naren.route.dataStructure.Record
import com.naren.route.dataType.TransactionTypes.{CCtransaction, CheckingTransaction, Deposit, Transfer}
import com.naren.route.entries.AssetLoans
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.{DoubleOps, XSSFRowOps}

case class Transaction(
                 txnID: Long,
                 dateTime: String,
                 transactionType: String,
                 stream: String,
                 source: String,
                 vendor: String,
                 amount: Double,
                 accountID: Long,
                 balance: Double,
                 creditDebt: Double,
                 credit: Double,
                 debtsPaid: Double,
               ) extends Record[Transaction] {

  def fromDeb(deb: CheckingTransaction): Transaction = {
    deb.stream match {
      case CREDIT_CARD =>
        new Transaction(
          deb.txnID, deb.dateTime, DEBIT, deb.stream, deb.source, deb.vendor, deb.amount,
          deb.accountID, balance - deb.amount, creditDebt - deb.amount,
          credit, debtsPaid + deb.amount
        )
      case _ =>
        new Transaction(
          deb.txnID, deb.dateTime, DEBIT, deb.stream, deb.source, deb.vendor, deb.amount, deb.accountID,
          balance - deb.amount, creditDebt, credit, debtsPaid + deb.amount
        )
    }
  }

  def fromDep(dep: Deposit): Transaction = {
    dep.stream match {
      case CREDIT_REWARD =>
        new Transaction(
          dep.txnID, dep.dateTime, CREDIT, dep.stream, dep.source, dep.vendor, dep.amount, dep.accountID,
          balance, creditDebt - dep.amount, credit + dep.amount, debtsPaid + dep.amount
        )
      case _ => {
        new Transaction(
          dep.txnID, dep.dateTime, CREDIT, dep.stream, dep.source, dep.vendor, dep.amount, dep.accountID,
          balance + dep.amount, creditDebt, credit + dep.amount, debtsPaid
        )
      }
    }
  }

  def fromCC(cc:CCtransaction): Transaction =
    new Transaction(
      cc.txnID, cc.dateTime, CREDIT_CARD, cc.stream, cc.source, cc.vendor, cc.amount, cc.accountID,
      balance, creditDebt + cc.amount, credit, debtsPaid
    )

  def fromAssetLoan(loan: AssetLoans, id: Long): Transaction =
    Transaction(id,loan.purchaseDate,CREDIT_CARD,LOAN,loan.nickName,loan.lender,loan.loanAmount,
      0L,balance,creditDebt,credit,debtsPaid)

  def fromTransfer(trn: Transfer): Transaction =
    new Transaction(trn.txnID,trn.dateTime,TRANSFER,"Internal",trn.fromAcc.toString,
      trn.toAcc.toString,trn.amount,trn.toAcc,balance,creditDebt,credit,debtsPaid)

}

object Transaction {

  def apply(deb: CheckingTransaction): Transaction =
    new Transaction(
      deb.txnID, deb.dateTime, DEBIT, deb.stream, deb.source, deb.vendor,
      deb.amount, deb.accountID,0,0,0, deb.amount
    )

  def apply(dep: Deposit): Transaction =
    new Transaction(
      dep.txnID, dep.dateTime, CREDIT, dep.stream, dep.source, dep.vendor, dep.amount, dep.accountID,
      dep.amount, 0, dep.amount, 0
    )

  def apply(cc:CCtransaction): Transaction =
    new Transaction(
      cc.txnID, cc.dateTime, CREDIT_CARD, cc.stream, cc.source, cc.vendor, cc.amount, cc.accountID,
      0, cc.amount, 0,0
    )
}
