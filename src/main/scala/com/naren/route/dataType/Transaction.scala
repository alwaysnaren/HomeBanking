package com.naren.route.dataType

import com.naren.route.constants.KeyWords.{CREDIT, CREDIT_CARD, CREDIT_REWARD, DEBIT}
import com.naren.route.dataStructure.Record
import com.naren.route.dataType.TransactionTypes.{CCtransaction, CheckingTransaction, Deposit}
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

}

object Transaction extends Record[Transaction]{
  def apply(row: XSSFRow): Transaction = {
    if(row.getLastCellNum.toInt == (fields.length-1)) {
      val records = row.toStrArray
      new Transaction(
        records(0).toLong,
        records(1),
        records(2),
        records(3),
        records(4),
        records(5),
        records(6).toDouble.format,
        records(7).toLong,
        records(8).toDouble.format,
        records(9).toDouble.format,
        records(10).toDouble.format,
        records(11).toDouble.format,
      )
    }
    else null // TODO -- Implement logger with Option
  }

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
