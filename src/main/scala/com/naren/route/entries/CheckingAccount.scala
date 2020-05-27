package com.naren.route.entries

import com.naren.route.dataStructure.Record
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.XSSFRowOps

case class CheckingAccount(
                        accountID: Long,
                        nickName: String,
                        bankName: String,
                        address: String,
                        accountNumber: Long,
                        routingNumber: Long,
                        dateCreated: String,
                        active: Boolean
                       ) extends Record[CheckingAccount]

object CheckingAccount extends Record[CheckingAccount] {
  def apply(row: XSSFRow): CheckingAccount = {
    val records = row.toStrArray
    new CheckingAccount(
      records(0).toLong,
      records(1),
      records(2),
      records(3),
      records(4).toLong,
      records(5).toLong,
      records(6),
      records(7).toBoolean
    )
  }
}

