package com.naren.route.dataType

import com.naren.route.dataStructure.Record
import com.naren.route.dataType.TransactionTypes.CCtransaction
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.{DoubleOps, XSSFRowOps}

case class Entertainment(
                 txnID: Long,
                 dateTime: String,
                 stream: String,
                 vendor: String,
                 accountID: Long,
                 amount: Double,
                 tillDate: Double
               ) extends Record[Entertainment] {

  def fromCC(cc:CCtransaction): Entertainment =
    new Entertainment(
      cc.txnID, cc.dateTime, cc.source, cc.vendor, cc.accountID, cc.amount, tillDate + cc.amount
    )
}

object Entertainment {

  def apply(cc:CCtransaction): Entertainment =
    new Entertainment(
      cc.txnID, cc.dateTime, cc.source, cc.vendor, cc.accountID, cc.amount, cc.amount
    )
}