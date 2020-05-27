package com.naren.route.entries

import com.naren.route.dataStructure.Record
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.XSSFRowOps

case class CCaccount(
                      accountID: Long,
                      nickName: String,
                      bankName: String,
                      creditLine: Int,
                      accountNumber: Long,
                      expiryInfo: String,
                      cvv: String,
                      dateCreated: String,
                      active: Boolean
                    ) extends Record[CCaccount]

object CCaccount {
  def apply(row: XSSFRow): CCaccount = {
    val records = row.toStrArray
    new CCaccount(
      records(0).toLong,
      records(1),
      records(2),
      records(3).toInt,
      records(4).toLong,
      records(5),
      records(6),
      records(7),
      records(8).toBoolean
    )
  }
}