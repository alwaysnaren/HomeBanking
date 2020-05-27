package com.naren.route.dataType

import com.naren.route.dataStructure.Record
import com.naren.route.dataType.TransactionTypes.CCtransaction
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.{DoubleOps, XSSFRowOps}

case class Travel(
                 txnID: Long,
                 dateTime: String,
                 category: String,
                 stream: String,
                 vendor: String,
                 accountID: Long,
                 amount: Double,
                 tillDate: Double
               ) extends Record[Travel] {

  def fromCC(cc: CCtransaction): Travel =
    new Travel(
      cc.txnID, cc.dateTime, cc.source, cc.purpose, cc.vendor, cc.accountID, cc.amount,
      tillDate + cc.amount
    )
}

object Travel extends Record[Travel]{
  def apply(row: XSSFRow): Travel = {
    val records = row.toStrArray
    new Travel(
      records(0).toLong,
      records(1),
      records(2),
      records(3),
      records(4),
      records(5).toLong,
      records(5).toDouble.format,
      records(6).toDouble.format
    )
  }

  def apply(cc: CCtransaction): Travel =
    new Travel(
      cc.txnID, cc.dateTime, cc.source, cc.purpose, cc.vendor, cc.accountID, cc.amount, cc.amount
    )
}