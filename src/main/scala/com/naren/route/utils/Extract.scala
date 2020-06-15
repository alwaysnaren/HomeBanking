package com.naren.route.utils

import com.naren.route.dataType.Accounts.{Checking, CreditCard}
import com.naren.route.dataType.TransactionTypes.{CCtransaction, CheckingTransaction, Deposit}
import com.naren.route.dataType._
import com.naren.route.dataType.investments.Nest
import com.naren.route.entries._
import com.naren.route.utils.Implicits.DoubleOps

trait Extract[T] {
  def apply(row: Array[String]): T
}

object Extract {

  implicit val checkingAccount: Extract[CheckingAccount] =
    (row: Array[String]) => {
      CheckingAccount(
        row(0).toLong,
        row(1),
        row(2),
        row(3),
        row(4).toLong,
        row(5).toLong,
        row(6),
        row(7).toBoolean
      )
    }

  implicit val ccAccount: Extract[CCaccount] =
    (row: Array[String]) => {
      CCaccount(
        row(0).toLong,
        row(1),
        row(2),
        row(3).toInt,
        row(4).toLong,
        row(5),
        row(6),
        row(7),
        row(8).toBoolean
      )
    }

  implicit val transaction: Extract[Transaction] =
    (row: Array[String]) => {
      Transaction(
        row(0).toLong,
        row(1),
        row(2),
        row(3),
        row(4),
        row(5),
        row(6).toDouble.format,
        row(7).toLong,
        row(8).toDouble.format,
        row(9).toDouble.format,
        row(10).toDouble.format,
        row(11).toDouble.format,
      )
    }

  implicit val deposit: Extract[Deposit] =
    (row: Array[String])=> {
      Deposit(
        row(0).toLong,
        row(1),
        row(2),
        row(3),
        row(4),
        row(5),
        row(6).toDouble.format,
        row(7).toLong,
        row(8).toDouble.format
      )
    }


  implicit val checking: Extract[Checking] =
    (row: Array[String]) => {
      Checking (
        row(0).toLong,
        row(1),
        row(2),
        row(3).toDouble.format,
        row(4).toDouble.format
      )
    }


  implicit val creditCard: Extract[CreditCard] =
    (row: Array[String]) => {
      CreditCard (
        row(0).toLong,
        row(1),
        row(2),
        row(3).toDouble.format,
        row(4).toDouble.format,
        row(5).toDouble.format
      )
    }

  implicit val ccTransaction: Extract[CCtransaction] =
    (row: Array[String]) => {
      CCtransaction(
        row(0).toLong,
        row(1),
        row(2),
        row(3),
        row(4),
        row(5),
        row(6).toDouble.format,
        row(7).toLong,
        row(8).toDouble.format
      )
    }


  implicit val car: Extract[Car] =
    (row: Array[String]) => {
      Car(
        row(0).toLong,
        row(1),
        row(2),
        row(3),
        row(4).toLong,
        row(5).toDouble.format,
        row(6).toDouble.format
      )
    }


  implicit val entertainment: Extract[Entertainment] =
    (row: Array[String]) => {
      Entertainment(
        row(0).toLong,
        row(1),
        row(2),
        row(3),
        row(4).toLong,
        row(5).toDouble.format,
        row(6).toDouble.format
      )
    }

  implicit val food: Extract[Food] =
    (row: Array[String]) => {
      Food(
        row(0).toLong,
        row(1),
        row(2),
        row(3),
        row(4).toLong,
        row(5).toDouble.format,
        row(6).toDouble.format
      )
    }

  implicit val assetLoans: Extract[AssetLoans] =
    (row: Array[String]) => {
      AssetLoans(
        row(0).toLong,
        row(1),
        row(2),
        row(3),
        row(4).toDouble.format,
        row(5).toDouble.format,
        row(6).toDouble.format,
        row(7).toDouble.format,
        row(8)
      )
    }

  implicit val nest: Extract[Nest] =
    (row: Array[String]) => {
      Nest(
        row(0).toLong,
        row(1),
        row(2),
        row(3).toLong,
        row(4),
        row(5),
        row(6).toDouble.format,
        row(7).toLong,
        row(8).toDouble.format,
        row(9).toDouble.format
      )
    }

  implicit val keyvalues: Extract[KeyValues] =
    (row: Array[String]) => {
      KeyValues(
        row(0),
        row(1).split(',')
      )
    }

  implicit val loan: Extract[Loan] =
    (row: Array[String]) => {
      Loan(
        row(0).toLong,
        row(1),
        row(2),
        row(3),
        row(4).toLong,
        row(5),
        row(6).toDouble.format,
        row(7).toLong,
        row(8).toDouble.format
      )
    }

  implicit val services: Extract[Services] =
    (row: Array[String]) => {
      Services(
        row(0).toLong,
        row(1),
        row(2),
        row(3),
        row(4),
        row(5).toDouble.format,
        row(6).toLong,
        row(7).toDouble.format
      )
    }

  implicit val shopping: Extract[Shopping] =
    (row: Array[String]) => {
      Shopping(
        row(0).toLong,
        row(1),
        row(2),
        row(3),
        row(4),
        row(5).toLong,
        row(6).toDouble.format,
        row(7).toDouble.format
      )
    }

  implicit val travel: Extract[Travel] =
    (row: Array[String]) => {
      Travel(
        row(0).toLong,
        row(1),
        row(2),
        row(3),
        row(4),
        row(5).toLong,
        row(5).toDouble.format,
        row(6).toDouble.format
      )
    }

  implicit val house: Extract[House] =
    (row: Array[String]) => {
      House(
        row(0).toLong,
        row(1),
        row(2),
        row(3),
        row(4).toDouble.format,
        row(5).toDouble.format
      )
    }

  implicit val checkingTransaction: Extract[CheckingTransaction] =
    (row: Array[String]) => {
      CheckingTransaction(
        row(0).toLong,
        row(1),
        row(2),
        row(3),
        row(4),
        row(5),
        row(6).toDouble.format,
        row(7).toLong,
        row(8).toDouble.format
      )
    }

  implicit val summary: Extract[Summary] =
    (row: Array[String]) => {
      Summary(
        row(0).toLong,
        row(1).toInt,
        row(2).toDouble.format,
        row(3).toDouble.format,
        row(4).toDouble.format,
        row(5).toDouble.format,
        row(6).toDouble.format,
        row(7).toDouble.format,
        row(8).toDouble.format,
        row(9).toDouble.format,
        row(10).toDouble.format,
        row(11).toDouble.format,
        row(12).toDouble.format,
        row(13).toDouble.format,
        row(14).toDouble.format,
        row(15).toDouble.format,
        row(16).toDouble.format,
        row(17).toDouble.format,
        row(18).toDouble.format
      )
    }

}


