package com.naren.route.dataType

import com.naren.route.dataStructure.Record
import com.naren.route.dataType.TransactionTypes.CCtransaction
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.{DoubleOps, XSSFRowOps}


case class Car(
                 txnID: Long,
                 dateTime: String,
                 stream: String,
                 vendor: String,
                 accountID: Long,
                 amount: Double,
                 tillDate: Double
               ) extends Record[Car]{

  def fromCC(cc: CCtransaction): Car =
    new Car(
      cc.txnID, cc.dateTime, cc.stream, cc.vendor, cc.accountID, cc.amount, tillDate + cc.amount
    )
}

object Car {

  def apply(cc: CCtransaction): Car =
    new Car(
      cc.txnID, cc.dateTime, cc.stream, cc.vendor, cc.accountID, cc.amount, cc.amount
    )

}
