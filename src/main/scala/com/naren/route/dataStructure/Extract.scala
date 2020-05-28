package com.naren.route.dataStructure

import com.naren.route.dataType.Accounts.{Checking, CreditCard}
import com.naren.route.dataType._
import com.naren.route.dataType.TransactionTypes.{CCtransaction, CheckingTransaction, Deposit}
import com.naren.route.entries.{CCaccount, CheckingAccount, House}
import com.naren.route.utils.Implicits.DoubleOps

trait Extract[T] {
  def apply(row: Array[String]): T
}

object Extract {

  implicit val checkingAccount: Extract[CheckingAccount] =
    (row: Array[String]) => {
      new CheckingAccount(
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
      new CCaccount(
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
      new Transaction(
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
      new Deposit(
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
      new Checking (
        row(0).toLong,
        row(1),
        row(2),
        row(3).toDouble.format,
        row(4).toDouble.format
      )
    }


  implicit val creditCard: Extract[CreditCard] =
    (row: Array[String]) => {
      new CreditCard (
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
      new CCtransaction(
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
      new Car(
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
      new Entertainment(
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
      new Food(
        row(0).toLong,
        row(1),
        row(2),
        row(3),
        row(4).toLong,
        row(5).toDouble.format,
        row(6).toDouble.format
      )
    }

  implicit val investment: Extract[Investment] =
    (row: Array[String]) => {
      new Investment(
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
      new KeyValues(
        row(0),
        row(1).split(',')
      )
    }

  implicit val loan: Extract[Loan] =
    (row: Array[String]) => {
      new Loan(
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
      new Services(
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
      new Shopping(
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
      new Travel(
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
      new House(
        row(0).toLong,
        row(1),
        row(2),
        row(3),
        row(4).toDouble.format,
        row(5).toDouble.format,
        row(6).toDouble.format,
        row(7),
        row(8).toLong,
        row(9).toDouble.format
      )
    }

  implicit val CheckingTransaction: Extract[CheckingTransaction] =
    (row: Array[String]) => {
      new CheckingTransaction(
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
      new Summary(
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


