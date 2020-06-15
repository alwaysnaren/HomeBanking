package com.naren.route.executors

import com.naren.route.constants.FileSystem
import com.naren.route.constants.KeyWords._
import com.naren.route.constants.Pages.CHECKING_ACCOUNTS
import com.naren.route.constants.pages.Fetch
import com.naren.route.dataStructure._
import com.naren.route.dataType.Accounts.{Checking, CreditCard}
import com.naren.route.dataType._
import com.naren.route.dataType.TransactionTypes.{CCtransaction, CheckingTransaction, Deposit}
import com.naren.route.dataType.investments.Nest
import com.naren.route.entries.{AssetLoans, CheckingAccount, House}


object Writer {

  val md: Book = Initialize.md
  val yb: YearBook = Initialize.yb

/** New Loan transactions start */

  def writeToLoans(loan: AssetLoans, id: Long): Unit = {
    val page = yb.getPage[Loan](LOANS)
    val entry = Loan(loan, id)
    page.addRecord(entry)
  }

  def writeToDebt(loan: AssetLoans, id: Long): Unit = {
    val page = yb.getPage[CCtransaction](CREDIT_CARD)
    val rec = page.getLastRecIfExists.get
    val entry = rec.fromAssetLoan(loan,id)
    page.addRecord(entry)
  }

  def writeToTransactions(loan: AssetLoans, id: Long): Unit = {
    val page = yb.getPage[Transaction](FileSystem.MONTH)
    val rec = page.getLastRecIfExists.get
    val entry = rec.fromAssetLoan(loan,id)
    page.addRecord(entry)
  }

/** New Loan transactions end */

/** Deposit transactions start */

  /** Adds Deposit Transactions */
  def recordDeposit(dep: Deposit): Unit = {
    val dtPage = yb.getPage[Deposit](DEPOSIT)
    val latestDT: Option[Deposit] = dtPage.getLastRecIfExists
    if(latestDT.isDefined) dep.tillDate += latestDT.get.tillDate
    dtPage.addRecord(dep)
  }

  /** Add deposit to Nest */
  def depToNest(dep: Deposit): Unit = {
    val id = Fetch.value[House,Long](dep.source,HOUSE,"nickName","assetID")  //Houses.getAssetID(dep.source)
    val page = yb.getPage[Nest](NEST)
    val lastRow = page.getRecFromKeyIfExists(id, "assetID").get
    val newRow = lastRow.fromDep(dep)
    page.addRecord(newRow)
  }

  /** Adds deposit to a checking account */
  def depToCheckingAcc(dep:Deposit): Unit = {
    val checkingAccount =
      Fetch.value[CheckingAccount, String](dep.accountID,CHECKING_ACCOUNTS,"nickName","accountID")  //CheckingAccounts.getName(dep.accountID)
    val page = yb.getPage[Checking](checkingAccount)
    val latestCArec = page.getLastRecIfExists
    val newRec = {
      if(latestCArec.isDefined) {
        val rec = latestCArec.get
        rec.fromDep(dep)
      }
      else Checking(dep)
    }
    page.addRecord(newRec)
  }

/** Deposit transactions end */

/** Debit transactions start */

  /** Adds debit to Nest */
  def debToNest(deb: CheckingTransaction, id: Long): Unit = {
    val page = yb.getPage[Nest](NEST)
    val lastRec = page.getRecFromKeyIfExists(id, ASSET_ID)
    val newRec = lastRec.get.fromDeb(deb)
    page.addRecord(newRec)
  }

  /** Adds debit to Loan */
  def debToLoan(deb: CheckingTransaction, id: Long): Unit = {
    val page = yb.getPage[Loan](LOAN)
    val latestRec = page.getRecFromKeyIfExists(id,ASSET_ID)
    val newRec = latestRec.get.fromDeb(deb)
    page.addRecord(newRec)
  }

  /** Adds debit to a checking account */
  def debToCheckingAcc(deb: CheckingTransaction): Unit = {
    val checkingAccount =
      Fetch.value[CheckingAccount, String](deb.accountID,CHECKING_ACCOUNTS,NICKNAME,ACCOUNT_ID)//CheckingAccounts.getName(deb.accountID)
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

/** Debit transactions end */

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

/** CC transactions end */



}
