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

