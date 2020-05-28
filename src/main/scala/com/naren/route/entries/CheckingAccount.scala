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


