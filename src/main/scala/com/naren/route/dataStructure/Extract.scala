package com.naren.route.dataStructure

import com.naren.route.dataType.Accounts.{Checking, CreditCard}
import com.naren.route.dataType._
import com.naren.route.dataType.TransactionTypes.{CCtransaction, CheckingTransaction, Deposit}
import com.naren.route.entries.{CCaccount, CheckingAccount, House}
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.{DoubleOps, XSSFRowOps}

trait Extract[T] {
  def apply(row:XSSFRow): T
}

object Extract {

  implicit val checkingAccount: Extract[CheckingAccount] = new Extract[CheckingAccount] {
    def apply(row: XSSFRow): CheckingAccount = {
      val records = row.toStrArray
      new CheckingAccount(
        records(0).toLong,
        records(1),
        records(2),
        records(3),
        records(4).toLong,
        records(5).toLong,
        records(6),
        records(7).toBoolean
      )
    }
  }

  implicit val ccAccount: Extract[CCaccount] = new Extract[CCaccount] {
    def apply(row: XSSFRow): CCaccount = {
      val records = row.toStrArray
      new CCaccount(
        records(0).toLong,
        records(1),
        records(2),
        records(3).toInt,
        records(4).toLong,
        records(5),
        records(6),
        records(7),
        records(8).toBoolean
      )
    }
  }

  implicit val transaction: Extract[Transaction] = new Extract[Transaction] {
    def apply(row: XSSFRow): Transaction = {
      val records = row.toStrArray
      new Transaction(
        records(0).toLong,
        records(1),
        records(2),
        records(3),
        records(4),
        records(5),
        records(6).toDouble.format,
        records(7).toLong,
        records(8).toDouble.format,
        records(9).toDouble.format,
        records(10).toDouble.format,
        records(11).toDouble.format,
      )
    }
  }

  implicit val deposit: Extract[Deposit] = new Extract[Deposit] {
    override def apply(row: XSSFRow): Deposit = ???
  }

  implicit val checking: Extract[Checking] = new Extract[Checking] {
    override def apply(row: XSSFRow): Checking = ???
  }

  implicit val creditCard: Extract[CreditCard] = new Extract[CreditCard] {
    override def apply(row: XSSFRow): CreditCard = ???
  }

  implicit val ccTransaction: Extract[CCtransaction] = new Extract[CCtransaction] {
    override def apply(row: XSSFRow): CCtransaction = ???
  }

  implicit val car: Extract[Car] = new Extract[Car] {
    override def apply(row: XSSFRow): Car = ???
  }

  implicit val entertainment: Extract[Entertainment] = new Extract[Entertainment] {
    override def apply(row: XSSFRow): Entertainment = ???
  }

  implicit val food: Extract[Food] = new Extract[Food] {
    override def apply(row: XSSFRow): Food = ???
  }

  implicit val investment: Extract[Investment] = new Extract[Investment] {
    override def apply(row: XSSFRow): Investment = ???
  }

  implicit val keyvalues: Extract[KeyValues] = new Extract[KeyValues] {
    override def apply(row: XSSFRow): KeyValues = ???
  }

  implicit val loan: Extract[Loan] = new Extract[Loan] {
    override def apply(row: XSSFRow): Loan = ???
  }

  implicit val services: Extract[Services] = new Extract[Services] {
    override def apply(row: XSSFRow): Services = ???
  }

  implicit val shopping: Extract[Shopping] = new Extract[Shopping] {
    override def apply(row: XSSFRow): Shopping = ???
  }

  implicit val travel: Extract[Travel] = new Extract[Travel] {
    override def apply(row: XSSFRow): Travel = ???
  }

  implicit val house: Extract[House] = new Extract[House] {
    override def apply(row: XSSFRow): House = ???
  }

  implicit val CheckingTransaction: Extract[CheckingTransaction] = new Extract[CheckingTransaction] {
    override def apply(row: XSSFRow): CheckingTransaction = ???
  }

}


