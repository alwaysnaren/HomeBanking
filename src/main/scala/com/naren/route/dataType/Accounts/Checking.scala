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

object Checking extends Record[Checking] {
  def apply(deb: CheckingTransaction): Checking =
    new Checking(deb.txnID, deb.dateTime, deb.vendor, deb.amount, deb.amount*(-1))

  def apply(row: XSSFRow): Checking = {
    val records = row.toStrArray
    new Checking (
      records(TXN_ID).toLong,
      records(DATE_TIME),
      records(VENDOR),
      records(AMOUNT).toDouble.format,
      records(CA_BALANCE).toDouble.format
    )
  }
}
