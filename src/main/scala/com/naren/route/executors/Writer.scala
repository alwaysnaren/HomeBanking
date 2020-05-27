package com.naren.route.executors

import com.naren.route.constants.FileSystem.{MASTER, PATH, YEAR_BOOK}
import com.naren.route.constants.KeyWords._
import com.naren.route.constants.pages.{CheckingAccounts, CreditCards}
import com.naren.route.data.Reader
import com.naren.route.dataStructure.{Book, Page, YearBook}
import com.naren.route.dataType.Accounts.{Checking, CreditCard}
import com.naren.route.dataType._
import com.naren.route.dataType.TransactionTypes.{CCtransaction, CheckingTransaction, Deposit}


object Writer {

  val md = Book(MASTER,PATH)
  val yb = YearBook(YEAR_BOOK, PATH)
  /** Adds transaction to a checking account */
  def debToCheckingAcc(deb: CheckingTransaction): Unit = {
    val checkingAccount = CheckingAccounts.getName(deb.accountID)
    val page = yb.getPage[Checking](checkingAccount)
    val latestCArec = page.getLastRecIfExists
    val newRec = {
      if(latestCArec.isDefined) {
        val rec = latestCArec.get
        rec.fromDeb(deb)
      }
      else Checking(deb)
    }
    page.addRecord(newRec)
  }

  /** Adds transaction to a creditCard account */
  def debToCreditAcc(deb: CheckingTransaction): Unit = {
    val ccAccount = deb.vendor
    val page = yb.getPage[CreditCard](ccAccount)
    val latestCCrec = page.getLastRecIfExists
    val newRec = {
      if(latestCCrec.isDefined) {
        val rec = latestCCrec.get
        rec.fromDeb(deb)
      }
      else CreditCard(deb)
    }
    page.addRecord(newRec)
  }

  /** Adds Checking Transactions */
  def recordCT(deb: CheckingTransaction): Unit = {
    val ctPage = yb.getPage[CheckingTransaction](DEBIT)
    val latestCT: Option[CheckingTransaction] = ctPage.getLastRecIfExists
    if(latestCT.isDefined) deb.tillDate += latestCT.get.tillDate
    ctPage.addRecord(deb)
  }

  /** Adds Deposit Transactions */
  def recordDT(dep: Deposit): Unit = {
    val dtPage = yb.getPage[Deposit](DEPOSIT)
    val latestDT: Option[Deposit] = dtPage.getLastRecIfExists
    if(latestDT.isDefined) dep.tillDate += latestDT.get.tillDate
    dtPage.addRecord(dep)
  }

/** CC transactions start */

  /** Adds CreditCard Transactions */
  def recordCCT(cct: CCtransaction): Unit = {
    val cctPage = yb.getPage[CCtransaction](CREDIT_CARD)
    val latestCCT: Option[CCtransaction] = cctPage.getLastRecIfExists
    if(latestCCT.isDefined) cct.tillDate += latestCCT.get.tillDate
    cctPage.addRecord(cct)
  }

  /** Adds CCtransaction to CAR  */
  def addCarFromCC(cc:CCtransaction): Unit = {
    val page = yb.getPage[Car](CAR)
    val latestCarRec = page.getLastRecIfExists
    val newRec = {
      if(latestCarRec.isDefined) {
        val rec = latestCarRec.get
        rec.fromCC(cc)
      }
      else Car(cc)
    }
    page.addRecord(newRec)
  }

  /** Adds CCtransaction to CAR  */
  def addEntertainmentFromCC(cc:CCtransaction): Unit = {
    val page = yb.getPage[Entertainment](ENTERTAINMENT)
    val latestCarRec = page.getLastRecIfExists
    val newRec = {
      if(latestCarRec.isDefined) {
        val rec = latestCarRec.get
        rec.fromCC(cc)
      }
      else Entertainment(cc)
    }
    page.addRecord(newRec)
  }

  /** Adds CCtransaction to FOOD  */
  def addFoodFromCC(cc:CCtransaction): Unit = {
    val page = yb.getPage[Food](FOOD)
    val latestCarRec = page.getLastRecIfExists
    val newRec = {
      if(latestCarRec.isDefined) {
        val rec = latestCarRec.get
        rec.fromCC(cc)
      }
      else Food(cc)
    }
    page.addRecord(newRec)
  }

  /** Adds CCtransaction to TRAVEL  */
  def addTravelFromCC(cc:CCtransaction): Unit = {
    val page = yb.getPage[Travel](TRAVEL)
    val latestRec = page.getLastRecIfExists
    val newRec = {
      if(latestRec.isDefined) {
        val rec = latestRec.get
        rec.fromCC(cc)
      }
      else Travel(cc)
    }
    page.addRecord(newRec)
  }

  /** Adds CCtransaction to Shopping  */
  def addShoppingFromCC(cc:CCtransaction): Unit = {
    val page = yb.getPage[Shopping](SHOPPING)
    val latestRec = page.getLastRecIfExists
    val newRec = {
      if(latestRec.isDefined) {
        val rec = latestRec.get
        rec.fromCC(cc)
      }
      else Shopping(cc)
    }
    page.addRecord(newRec)
  }

  /** Adds CCtransaction to SERVICES  */
  def addServicesFromCC(cc:CCtransaction): Unit = {
    val page = yb.getPage[Services](SERVICES)
    val latestRec = page.getLastRecIfExists
    val newRec = {
      if(latestRec.isDefined) {
        val rec = latestRec.get
        rec.fromCC(cc)
      }
      else Services(cc)
    }
    page.addRecord(newRec)
  }

//  def addatreamFromCC[N <: Services](cc: CCtransaction, name: String): Unit = {
//    val page = yb.getPage[N](name)
//    val latestRec = page.getLastRecIfExists
//    val newRec = {
//      if(latestRec.isDefined) {
//        val rec = latestRec.get
//        rec.fromCC(cc)
//      }
//    }
//  }

/** CC transactions end */

  /** Adds Loan Transaction*/
  def recordLoanPayment(deb: CheckingTransaction): Unit = {
    val page = yb.getPage[Loan](LOAN)

  }


}
