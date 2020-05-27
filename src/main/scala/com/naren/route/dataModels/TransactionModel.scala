//package data.Reader
//
//import com.naren.route.constants.Pages._
//import com.naren.route.scalafx.dialogs._
//import com.naren.route.constants.FileSystem.PATH
//import com.naren.route.entries.{CCaccount, CheckingAccount}
//import com.naren.route.constants.KeyWords._
//import com.naren.route.constants.pages.Houses
//import com.naren.route.dataType.{Loan, Services, Transaction}
//import com.naren.route.data.Reader
//import com.naren.route.dataStructure.Book
//import com.naren.route.dataType.Accounts.{Checking, CreditCard}
//import com.naren.route.executors.Writer
//import com.naren.route.scalafx.skins.Alerts
//
//class TransactionModel {
//
//  def onLoanEntry(): Unit = {
//
//  }
//
//  def onTransferEntry(): Unit = {
//
//  }
//
//  def onAddStock(): Unit = {
//
//  }
//
//  def onCcEntry(): Unit = {
//    val result = CCentry.showAndWait()
//    result match {
//      case Some(cc) => {
//
//        Writer.recordCCT(cc)
//
//        val latestTr =
//          if(Reader.lastTransaction != null) Reader.lastTransaction.fromCC(cc)
//          else Transaction(cc)
//        Reader.transactionPage.addRecord(latestTr)
//
//        cc.stream match {
//          case CAR => Writer.addCarFromCC(cc)
//          case ENTERTAINMENT => Writer.addEntertainmentFromCC(cc)
//          case SHOPPING => Writer.addShoppingFromCC(cc)
//          case FOOD => Writer.addFoodFromCC(cc)
//          case SERVICES => Writer.addServicesFromCC(cc)
//          case TRAVEL => Writer.addTravelFromCC(cc)
//          case _ => None
//        }
//      }
//      case _ => None
//    }
//  }
//
//  def onDebitEntry(): Unit = {
//    val result = DebitEntry.showAndWait()
//    result match {
//      case Some(deb) => {
//        Writer.debToCheckingAcc(deb)
//        Writer.recordCT(deb)
//
//        val latestTr =
//          if(Reader.lastTransaction != null) Reader.lastTransaction.fromDeb(deb)
//          else Transaction(deb)
//        Reader.transactionPage.addRecord(latestTr)
//
//        deb.stream match {
//          case CREDIT_CARD => Writer.debToCreditAcc(deb)
//          case SERVICES => {
//            val srPage = Reader.yb.getPage[Services](SERVICES)
//            val latestSR = srPage.getLastRecIfExists
//            val newRec = latestSR.get.fromDeb(deb)
//            srPage.addRecord(newRec)
//          }
//          case LOAN => {
//            val lnPage = Reader.yb.getPage[Loan](LOAN)
//          }
//          case INVESTMENT => None
//          case _ => None
//        }
//      }
//      case _ => None
//    }
//  }
//
//  /** write to Deposit and Transaction page
//    * write to Investment page if stream is same */
//  def onDepositEntry(): Unit = {
//    val result = DepositEntry.showAndWait()
//    result match {
//      case Some(dep) => {
//
//        Writer.recordDT(dep)
//
//        val latestTr =
//          if(Reader.lastTransaction != null) Reader.lastTransaction.fromDep(dep)
//          else Transaction(dep)
//        Reader.transactionPage.addRecord(latestTr)
//
////        dep.stream match {
////          case INVESTMENT => {
////            val id = Houses.getAssetID(dep.source)
////            val lastRow = Reader.investPage.getLatestRowOf(id)
////            if(lastRow.isDefined){
////              val newRow = lastRow.get.fromDeposit(dep)
////              Reader.investmentPage.addRecord(newRow)
////            } else {
////              Alerts.failure("Adding Deposit Failed",
////                s"No previous investment for this ${dep.source}, please check.")
////              return
////            }
////          }
////          case _ => None
////        }
//      }
//      case _ => None
//    }
//
//  }
//
//  def onAddHouse(): Unit = {
//    val result = AddHouse.showAndWait()
//    result match {
//      case Some(house) => {
//        Houses.addRecord(house)
//        Alerts.information(SUCCESS,s"Congrats!! ${house.address} is added")
//          .showAndWait()
//      }
//    }
//
//  }
//
//  def onAddChecking(): Unit = {
//    val result = AddChecking.showAndWait()
//    result match {
//      case Some(debit) => {
//        val debitACCs = Reader.md.getPage[CheckingAccount](CHECKING_ACCOUNTS)
//        debitACCs.addRecord(debit)
//        Reader.yb.createPage[Checking](debit.nickName)
//        Alerts.information(SUCCESS,s"New checking account from ${debit.bankName} is added")
//          .showAndWait()
//      }
//      case _ => None
//    }
//  }
//
//  def onAddCreditCard(): Unit = {
//    val result = AddCreditcard.showAndWait()
//    result match {
//      case Some(ccAccount) => {
//        val ccAccs = Reader.md.getPage[CCaccount](CREDIT_CARDS)
//        ccAccs.addRecord(ccAccount)
//        Reader.yb.createPage[CreditCard](ccAccount.nickName)
//        Alerts.information(SUCCESS,s"New creditCard from ${ccAccount.bankName} is added")
//          .showAndWait()
//      }
//      case _ => None
//    }
//  }
//
//  def onCreatePage(): Unit = {
//    val result = CreatePage.showAndWait()
//    result match {
//      case Some(cp) => {
//        val db = Book(cp.fileName,PATH)
//        db.createPage(cp.pageName,cp.header)
//        Alerts.information(SUCCESS,s"${cp.pageName} created in ${cp.fileName}")
//          .showAndWait()
//      }
//      case _ => None
//    }
//  }
//
//  def onAddNewKey(): Unit = None
//
//}
