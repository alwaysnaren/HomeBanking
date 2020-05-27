package com.naren.route.dataType

import com.naren.route.dataStructure.Record
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.{DoubleOps, XSSFRowOps}

case class Summary(
                  txnID: Long,
                  transactions: Int,
                  startBalance: Double,
                  expenses: Double,
                  credited: Double,
                  payments: Double,
                  creditDebt: Double,
                  Food: Double,
                  LoanPayments: Double,
                  groceries: Double,
                  entertainment: Double,
                  car: Double,
                  travel: Double,
                  ccUsage: Double,
                  ccPayment: Double,
                  utilities: Double,
                  shopping: Double,
                  miscellaneous: Double,
                  endBalance: Double,
                  ) extends Record[Summary]

object Summary extends Record[Summary]{
  def apply(row: XSSFRow): Summary = {
    if(row.getLastCellNum.toInt == (fields.length-1)) {
      val records = row.toStrArray
      new Summary(
        records(0).toLong,
        records(1).toInt,
        records(2).toDouble.format,
        records(3).toDouble.format,
        records(4).toDouble.format,
        records(5).toDouble.format,
        records(6).toDouble.format,
        records(7).toDouble.format,
        records(8).toDouble.format,
        records(9).toDouble.format,
        records(10).toDouble.format,
        records(11).toDouble.format,
        records(12).toDouble.format,
        records(13).toDouble.format,
        records(14).toDouble.format,
        records(15).toDouble.format,
        records(16).toDouble.format,
        records(17).toDouble.format,
        records(18).toDouble.format
      )
    }
    else null // TODO -- Implement logger with Option
  }
}