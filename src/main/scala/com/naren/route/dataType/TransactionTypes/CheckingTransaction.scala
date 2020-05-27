package com.naren.route.dataType.TransactionTypes

import com.naren.route.dataType._
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.{DoubleOps, XSSFRowOps}
import com.naren.route.constants.KeyWords.{CREDIT_CARD, DEBIT}
import com.naren.route.dataStructure.Record

case class CheckingTransaction(
                      txnID: Long,
                      dateTime: String,
                      stream: String,
                      source: String,
                      purpose: String,
                      vendor: String,
                      amount: Double,
                      accountID: Long,
                      var tillDate: Double
                    ) extends Record[CheckingTransaction]{

  def toTransaction(latest: Transaction = null): Transaction = {
    if(latest != null) {
      stream match {
        case CREDIT_CARD =>
          new Transaction(
            txnID, dateTime, DEBIT, stream, source, vendor, amount, accountID,
            latest.balance - amount, latest.creditDebt - amount, latest.credit, latest.debtsPaid + amount
          )
        case _ =>
          new Transaction(
            txnID, dateTime, DEBIT, stream, source, vendor, amount, accountID,
            latest.balance - amount, latest.creditDebt, latest.credit, latest.debtsPaid + amount
          )
      }
    } else
      new Transaction(
        txnID,dateTime,DEBIT,stream,source,vendor,amount,accountID,
        0,0,0,amount
      )
  }

}

object CheckingTransaction extends Record[CheckingTransaction]{
  def apply(row: XSSFRow): CheckingTransaction = {
    val records = row.toStrArray
    new CheckingTransaction(
      records(TXN_ID).toLong,
      records(DATE_TIME),
      records(STREAM),
      records(SOURCE),
      records(PURPOSE),
      records(VENDOR),
      records(AMOUNT).toDouble.format,
      records(ACCOUNT_ID).toLong,
      records(TILL_DATE).toDouble.format
    )
  }
}