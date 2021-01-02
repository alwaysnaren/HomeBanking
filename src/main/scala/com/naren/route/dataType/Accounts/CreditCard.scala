package com.naren.route.dataType.Accounts

import com.naren.route.constants.pages.{CreditCards, Fetch}
import com.naren.route.dataStructure.Record
import com.naren.route.dataType.TransactionTypes.CheckingTransaction
import com.naren.route.entries.CCaccount
import com.naren.route.constants.KeyWords.{CREDIT_CARDS,NICKNAME,CREDIT_LINE}


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
      deb.txnID, deb.dateTime, deb.accountID.toString, deb.amount * (-1),
      debt - deb.amount, balance + deb.amount
    )
}

object CreditCard {
  def apply(deb: CheckingTransaction): CreditCard =
    new CreditCard(deb.txnID, deb.dateTime, deb.vendor, deb.amount * (-1), deb.amount,
      Fetch.value[CCaccount,Int](deb.vendor,CREDIT_CARDS,NICKNAME,CREDIT_LINE) * (-1))
}