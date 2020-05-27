package com.naren.route.dataType

import com.naren.route.dataStructure.Record
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.{DoubleOps, XSSFRowOps}

case class Loan(
                 txnID: Long,
                 dateTime: String,
                 TransactionType: String,
                 commodity: String,
                 assetId: Long,
                 vendor: String,
                 amount: Double,
                 accountID: Long,
                 tillDate: Double
               ) extends Record[Loan]

object Loan extends Record[Loan]{
  def apply(row: XSSFRow): Loan = {
    if(row.getLastCellNum.toInt == (fields.length-1)) {
      val records = row.toStrArray
      new Loan(
        records(0).toLong,
        records(1),
        records(2),
        records(3),
        records(4).toLong,
        records(5),
        records(6).toDouble.format,
        records(7).toLong,
        records(8).toDouble.format
      )
    }
    else null // TODO -- Implement logger with Option
  }
}