package com.naren.route.dataType.TransactionTypes

import com.naren.route.constants.KeyWords.{CREDIT_CARD, DEBIT}
import com.naren.route.dataStructure.Record
import com.naren.route.dataType._
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.{DoubleOps, XSSFRowOps}


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
}

object CCtransaction extends Record[CCtransaction]{
  def apply(row: XSSFRow): CCtransaction = {
    val records = row.toStrArray
    new CCtransaction(
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