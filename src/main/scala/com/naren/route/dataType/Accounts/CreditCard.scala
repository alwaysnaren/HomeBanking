package com.naren.route.dataType.Accounts

import com.naren.route.constants.pages.CreditCards
import com.naren.route.dataStructure.Record
import com.naren.route.dataType.TransactionTypes.CheckingTransaction
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.{DoubleOps, XSSFRowOps}


case class CreditCard(
                       txnID: Long,
                       dateTime: String,
                       vendor: String,
                       amount: Double,
                       debt: Double,
                       balance: Double
                     ) extends Record[CreditCard] {

  def fromDeb(deb: CheckingTransaction): CreditCard =
    new CreditCard(
      deb.txnID, deb.dateTime, deb.source, deb.amount * (-1), debt - deb.amount, balance + deb.amount
    )
}

object CreditCard {
  def apply(deb: CheckingTransaction): CreditCard =
    new CreditCard(deb.txnID, deb.dateTime, deb.vendor, deb.amount, deb.amount,
      CreditCards.getCreditLine(deb.vendor) - deb.amount)
}