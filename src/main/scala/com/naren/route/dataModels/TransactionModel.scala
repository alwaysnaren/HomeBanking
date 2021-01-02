package data.Reader

import com.naren.route.scalafx.dialogs._
import com.naren.route.constants.FileSystem.PATH
import com.naren.route.entries.{AssetLoans, CCaccount, CheckingAccount}
import com.naren.route.constants.KeyWords._
import com.naren.route.constants.pages.{Fetch, Houses}
import com.naren.route.dataType.{Loan, Services, Transaction}
import com.naren.route.dataStructure.Book
import com.naren.route.dataType.Accounts.{Checking, CreditCard}
import com.naren.route.dataType.TransactionTypes.Transfer
import com.naren.route.executors.{Reader, Writer}
import com.naren.route.scalafx.skins.Alerts
import com.naren.route.utils.IDgenerator
import scalafx.scene.control.ButtonType

class TransactionModel {

  /** Make entry to AssetLoans in Master
    * Make entry to loans page
    * Make entry to credit card page z                                                                                      fr*/
  def onLoanEntry(): Unit = {
    val result = AddLoan.showAndWait()
    result match {
      case Some(loan) => {
        val aPage = Reader.md.getPage[AssetLoans](ASSET_LOANS)
        aPage.addRecord(loan)

        val id = IDgenerator.TransactionID(loan.purchaseDate.split(" ").head)
        Writer.writeToLoans(loan,id)
        Writer.writeToDebt(loan,id)
        Writer.writeToTransactions(loan,id)
      }
      case _ => None
    }
  }

  def onTransferEntry(): Unit = {
    val result = TransferEntry.showAndWait()
    result match {
      case Some(trn) => {
        Writer.recordTransfer(trn)
        Writer
      }
      case _ => None
    }
  }

  def onAddStock(): Unit = {

  }

  def onCcEntry(): Unit = {
    val result = CCentry.showAndWait()
    result match {
      case Some(cc) => {

        Writer.recordCCT(cc)

        val latestTr =
          if(Reader.lastTransaction != null) Reader.lastTransaction.fromCC(cc)
          else Transaction(cc)
        Reader.transactionPage.addRecord(latestTr)

        cc.stream match {
          case CAR => Writer.addCarFromCC(cc)
          case ENTERTAINMENT => Writer.addEntertainmentFromCC(cc)
          case SHOPPING => Writer.addShoppingFromCC(cc)
          case FOOD => Writer.addFoodFromCC(cc)
          case SERVICES => Writer.addServicesFromCC(cc)
          case TRAVEL => Writer.addTravelFromCC(cc)
          case _ => None
        }
      }
      case _ => None
    }
  }

  def onDebitEntry(): Unit = {
    val result = DebitEntry.showAndWait()
    result match {
      case Some(deb) => {
        Writer.debToCheckingAcc(deb)
        Writer.recordCT(deb)

        val latestTr =
          if(Reader.lastTransaction != null) Reader.lastTransaction.fromDeb(deb)
          else Transaction(deb)
        Reader.transactionPage.addRecord(latestTr)

        deb.stream.toLowerCase() match {
          case CREDIT_CARD => Writer.debToCreditAcc(deb)
          case SERVICES => {
            val srPage = Reader.yb.getPage[Services](SERVICES)
            val latestSR = srPage.getLastRecIfExists
            val newRec = latestSR.get.fromDeb(deb)
            srPage.addRecord(newRec)
          }
          case LOAN => {
            val page = Reader.yb.getPage[Loan](LOAN)
            val id = Fetch.value[AssetLoans,Long](deb.source,ASSET_LOANS,NICKNAME,ASSET_ID)
            deb.source match {
              case NEST => Writer.debToNest(deb,id)
              case _ => None
            }
            Writer.debToLoan(deb,id)
          }
          case _ => None
        }
      }
      case _ => None
    }
  }

  /** write to Deposit and Transaction page
    * write to Nest page if stream is Nest */
  def onDepositEntry(): Unit = {
    val result = DepositEntry.showAndWait()
    result match {
      case Some(dep) => {

        Writer.recordDeposit(dep)
        Writer.depToCheckingAcc(dep)

        val latestTr =
          if(Reader.lastTransaction != null) Reader.lastTransaction.fromDep(dep)
          else Transaction(dep)
        Reader.transactionPage.addRecord(latestTr)

        dep.stream match {
          case NEST => Writer.depToNest(dep)
          case _ => None
        }
      }
      case _ => None
    }

  }

  def onAddHouse(): Unit = {
    val result = AddHouse.showAndWait()
    result match {
      case Some(house) => {
        Houses.addRecord(house)
        val res = Alerts.confirmation(SUCCESS,s"Congrats!! ${house.address} is added," +
          s"Did you get a loan and want to add it now?")
          .showAndWait()
        res match {
          case Some(ButtonType.Yes) =>
        }
      }
      case _ => None
    }
  }

  def onAddChecking(): Unit = {
    val result = AddChecking.showAndWait()
    result match {
      case Some(debit) =>
        val page = Reader.md.getPage[CheckingAccount](CHECKING_ACCOUNTS)
        page.addRecord(debit)
        Reader.yb.createPage[Checking](debit.nickName)
        Alerts.information(SUCCESS,s"New checking account from ${debit.bankName} is added")
          .showAndWait()
      case _ => None
    }
  }

  def onAddCreditCard(): Unit = {
    val result = AddCreditcard.showAndWait()
    result match {
      case Some(ccAccount) => {
        val ccAccs = Reader.md.getPage[CCaccount](CREDIT_CARDS)
        ccAccs.addRecord(ccAccount)
        Reader.yb.createPage[CreditCard](ccAccount.nickName)
        Alerts.information(SUCCESS,s"New creditCard from ${ccAccount.bankName} is added")
          .showAndWait()
      }
      case _ => None
    }
  }

  def onCreatePage(): Unit = {
    val result = CreatePage.showAndWait()
    result match {
      case Some(cp) => {
        val db = Book(cp.fileName,PATH)
        db.createPage(cp.pageName,cp.header)
        Alerts.information(SUCCESS,s"${cp.pageName} created in ${cp.fileName}")
          .showAndWait()
      }
      case _ => None
    }
  }

  def onAddNewKey(): Unit = None

}
