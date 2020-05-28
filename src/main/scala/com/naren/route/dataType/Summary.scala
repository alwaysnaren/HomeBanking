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