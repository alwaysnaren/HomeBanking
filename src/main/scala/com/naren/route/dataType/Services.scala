package com.naren.route.dataType

import com.naren.route.dataStructure.Record
import com.naren.route.dataType.TransactionTypes.{CCtransaction, CheckingTransaction}
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.{DoubleOps, XSSFRowOps}

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

  def apply(row: XSSFRow): Services = {
    val record = row.toStrArray
    new Services(
      record(TXN_ID).toLong,
      record(DATE_TIME),
      record(STREAM),
      record(SOURCE),
      record(VENDOR),
      record(AMOUNT).toDouble.format,
      record(ACCOUNT_ID).toLong,
      record(TILL_DATE).toDouble.format
    )
  }

  def apply(cc: CCtransaction): Services =
    new Services(
      cc.txnID, cc.dateTime, cc.stream, cc.source, cc.vendor, cc.amount, cc.accountID, cc.amount
    )

}
