package com.naren.route.entries

import com.naren.route.dataStructure.Record
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.{DoubleOps, XSSFRowOps}

case class House(
                assetID: Long,
                nickName: String,
                purchaseDate: String,
                address: String,
                costPrice: Double,
                closingCosts: Double = 0,
                downPayment: Double = 0,
                lender: String = "",
                loanAmount: Double = 0,
                interestRate: Double = 0
                ) extends Record[House]

object House extends Record[House] {
  def apply(row: XSSFRow): House = {
    if(row.getLastCellNum.toInt == (fields.length-1)) {
      val records = row.toStrArray
      new House(
        records(0).toLong,
        records(1),
        records(2),
        records(3),
        records(4).toDouble.format,
        records(5).toDouble.format,
        records(6).toDouble.format,
        records(7),
        records(8).toLong,
        records(9).toDouble.format
      )
    }
    else null // TODO -- Implement logger with Option
  }
}
