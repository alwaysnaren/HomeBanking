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
