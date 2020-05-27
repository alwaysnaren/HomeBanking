package com.naren.route.dataType

import com.naren.route.dataStructure.Record
import com.naren.route.dataType.TransactionTypes.CCtransaction
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.{DoubleOps, XSSFRowOps}

case class Food(
                 txnID: Long,
                 dateTime: String,
                 stream: String,
                 vendor: String,
                 accountID: Long,
                 amount: Double,
                 tillDate: Double
               ) extends Record[Food] {

  def fromCC(cc: CCtransaction): Food =
    new Food(
      cc.txnID, cc.dateTime, cc.source, cc.vendor, cc.accountID, cc.amount, tillDate + cc.amount
    )
}

object Food extends Record[Food]{
  def apply(row: XSSFRow): Food = {
    val records = row.toStrArray
    new Food(
      records(TXN_ID).toLong,
      records(DATE_TIME),
      records(STREAM),
      records(VENDOR),
      records(ACCOUNT_ID).toLong,
      records(AMOUNT).toDouble.format,
      records(TILL_DATE).toDouble.format
    )
  }

  def apply(cc: CCtransaction): Food =
    new Food(
      cc.txnID, cc.dateTime, cc.source, cc.vendor, cc.accountID, cc.amount, cc.amount
    )
}