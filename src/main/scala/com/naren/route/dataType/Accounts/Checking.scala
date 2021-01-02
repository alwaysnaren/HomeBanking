package com.naren.route.dataType.Accounts

import com.naren.route.dataStructure.Record
import com.naren.route.dataType.TransactionTypes.{CheckingTransaction, Deposit, Transfer}


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

  def fromDep(dep: Deposit): Checking =
    new Checking(
      dep.txnID, dep.dateTime, dep.vendor, dep.amount, balance + dep.amount
    )

  def fromTransferred(trn: Transfer): Checking =
    new Checking(
      trn.txnID,trn.dateTime,trn.toAcc.toString,trn.amount,balance - trn.amount
    )

  def fromTransfer(trn: Transfer): Checking =
    new Checking(
      trn.txnID,trn.dateTime,trn.fromAcc.toString,trn.amount,balance + trn.amount
    )

}

object Checking extends {
  def apply(deb: CheckingTransaction): Checking =
    new Checking(deb.txnID, deb.dateTime, deb.vendor, deb.amount, deb.amount*(-1))

  def apply(dep:Deposit): Checking =
    new Checking(dep.txnID, dep.dateTime, dep.vendor, dep.amount, dep.amount)
}
