package com.naren.route.data

import com.naren.route.dataStructure._
import com.naren.route.constants.FileSystem._
import com.naren.route.constants.Months.JAN
import com.naren.route.dataType.{Investment, Transaction}
import com.naren.route.constants.KeyWords._
import com.naren.route.dataType.Accounts.{Checking, CreditCard}
import com.naren.route.dataType.TransactionTypes.{CCtransaction, CheckingTransaction, Deposit}

import scala.reflect.ClassTag

object Reader {

  val md = Book(MASTER,PATH)
  val yb = YearBook(YEAR_BOOK, PATH)
  lazy val prevYB: YearBook = YearBook(PREV_FILE_NAME, PATH)
  lazy val prevYBexists: Boolean = prevYB.file.exists()

  /** Transactions data*/
  lazy val transactionPage: Page[Transaction] = yb.getPage[Transaction](MONTH)
  lazy val prevTP: Page[Transaction] = {
    if(MONTH != JAN) {
      yb.getPage[Transaction](PREV_MONTH)
    } else {
      prevYB.getPage[Transaction](PREV_MONTH)
    }
  }
  def lastTransaction: Transaction = {
    val lastRowNum = transactionPage.getLastRowNum
    val row: Transaction =
      if(lastRowNum > 0) transactionPage.getRow(lastRowNum)
      else prevTP.getRow(prevTP.getLastRowNum)
    row
  }

//  /** Deposit data */
//  lazy val depositPage: Page[Deposit] = yb.getPage(DEPOSIT)
//  def latestDT: Option[Deposit] = {
//    val row = depositPage.getLatestRow
//    if(row.isDefined) row
//    else if(prevYBexists) {
//      val previousDP: Page[Deposit] = prevYB.getPage(DEPOSIT)
//      val prevRow = previousDP.getLatestRow
//      if(prevRow.isDefined) prevRow
//      else None
//    }
//    else None
//  }
//
//  /** Investment data */
//  lazy val investmentPage: Page[Investment] = yb.getPage(INVESTMENT)
//  lazy val investPage: InvestPage = InvestPage(investmentPage)
//  def latestInvestOf(assetID: Long): Option[Investment] = {
//    val row = investPage.getLatestRowOf(assetID)
//    if(row.isDefined) row
//    else if(prevYBexists) {
//      val previousIP: Page[Investment] = prevYB.getPage(INVESTMENT)
//      val row = None //previousIP.getLatestRowOf(assetID)
//      if(row.isDefined) Some(row.get) else None
//    } else None
//  }
//
  /** Checking Transactions data */
  def ctPage: Page[CheckingTransaction] = yb.getPage[CheckingTransaction](DEBIT)
//  def latestCT: Option[CheckingTransaction] = {
//    val row = ctPage.getLatestRow
//    if(row.isDefined) row
//    else if(prevYBexists) {
//      val previousDP: Page[CheckingTransaction] = prevYB.getPage(DEPOSIT)
//      val prevRow = previousDP.getLatestRow
//      if(prevRow.isDefined) prevRow
//      else None
//    }
//    else None
//  }
//
//  /** Credit card Transactions */
//  def ccPage: Page[CCtransaction] = yb.getPage(CREDIT_CARD)
//  def latestCC: Option[CCtransaction] = {
//    val row = ccPage.getLatestRow
//    if(row.isDefined) row
//    else if(prevYBexists) {
//      val previousCCP: Page[CCtransaction] = prevYB.getPage(CREDIT_CARD)
//      val prevRow = previousCCP.getLatestRow
//      if(prevRow.isDefined) prevRow
//      else None
//    }
//    else None
//  }

//  /** Credit card accounts */
//  def ccAccount(name: String): Page[CreditCard] = yb.getPage[CreditCard](name)
//  def latestCCArec(name: String): Option[CreditCard] = {
//    val rec = ccAccount(name).getLatestRow
//    if(rec.isDefined) rec
//    else {
//      if(prevYBexists) {
//        val prevCCA = prevYB.getPage[CreditCard](name)
//        val rec = prevCCA.getLatestRow
//        if(rec.isDefined) rec
//        else None
//      } else None
//    }
//  }
//
//  /** Checking accounts */
//  def checkingAccount(name: String): Page[Checking] = yb.getPage[Checking](name)
//  def latestCArec(name: String): Option[Checking] = {
//    val rec = checkingAccount(name).getLatestRow
//    if(rec.isDefined) rec
//    else {
//      if(prevYBexists) {
//        val prevCA = prevYB.getPage[Checking](name)
//        val rec = prevCA.getLatestRow
//        if(rec.isDefined) rec
//        else None
//      } else None
//    }
//  }

//  def accLatestRec[N <: Record[N]: ClassTag](name:String): Option[N] = {
//    val rec = yb.getPage[N](name).getLatestRow
//    if(rec.isDefined) rec
//    else {
//      if(prevYBexists) {
//        val prevCA = prevYB.getPage[N](name)
//        val rec = prevCA.getLatestRow
//        if(rec.isDefined) rec
//        else None
//      } else None
//    }
//  }

}
