package com.naren.route.dataType.Accounts

import com.naren.route.dataStructure.Record
import com.naren.route.dataType.TransactionTypes.CheckingTransaction
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.{DoubleOps, XSSFRowOps}


case class Checking (
                    txnID: Long,
                    dateTime: String,
                    vendor: String,
                    amount: Double,
                    balance: Double
                   ) extends Record[Checking]{

  def fromDeb(deb: CheckingTransaction): Checking =
    new Checking(
      deb.txnID, deb.dateTime, deb.vendor, deb.amount, balance - deb.amount
    )
}

object Checking extends {
  def apply(deb: CheckingTransaction): Checking =
    new Checking(deb.txnID, deb.dateTime, deb.vendor, deb.amount, deb.amount*(-1))
}
