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

object Travel {

  def apply(cc: CCtransaction): Travel =
    new Travel(
      cc.txnID, cc.dateTime, cc.source, cc.purpose, cc.vendor, cc.accountID, cc.amount, cc.amount
    )
}