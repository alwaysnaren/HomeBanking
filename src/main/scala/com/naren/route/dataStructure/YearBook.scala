package com.naren.route.dataStructure

import java.io.File

import com.naren.route.constants.KeyWords._
import com.naren.route.constants.FileSystem.{DEFAULT_PAGES, PATH, SEPERATOR}
import com.naren.route.constants.pages.{CheckingAccounts, CreditCards, Fetch}
import com.naren.route.dataType.Accounts.{Checking, CreditCard}
import com.naren.route.dataType.TransactionTypes.{CCtransaction, CheckingTransaction, Deposit, Transfer}
import com.naren.route.dataType._
import com.naren.route.dataType.investments.Nest
import com.naren.route.entries.{CCaccount, CheckingAccount}
import com.naren.route.fileSystem.Path
import com.naren.route.scalafx.skins.Alerts
import org.apache.poi.xssf.usermodel.{XSSFSheet, XSSFWorkbook}
import scalafx.scene.control.ButtonType

import scala.collection.mutable.ArrayBuffer

case class YearBook(name: String, path: Path) extends Database(new File(path.dir+SEPERATOR+name) ) {

  val fullPath: String = path.dir+SEPERATOR+name
  val file = new File(fullPath)
  lazy val prevYB: Option[YearBook] = {
    val year = name.substring(4,8).toInt - 1
    val fName = s"ACC_$year.xlsx"
    val file = new File(fullPath+fName)
    if(file.exists()) Some(YearBook(fName)) else None
  }


  if(!exists) {
    val res = Alerts.confirmation("Creating a Yearbook",s"Create $fullPath?")
        .showAndWait()
    res match {
      case Some(ButtonType.OK) => {
        createEmptyDB(file)
        createEmptyDB(file, DEFAULT_PAGES)
        for(page <- Fetch.column[CheckingAccount,String](CHECKING_ACCOUNTS,"nickName"))
          createPage[Checking](page.toString)
        for(page <- Fetch.column[CCaccount,String](CREDIT_CARDS,"nickName"))
          createPage[CreditCard](page.toString)

        Alerts.information("File created!!",fullPath).showAndWait()
      }
      case _ => None
    }
  }

  lazy val wbc = new XSSFWorkbook(file)

  def getXSSFSheets: ArrayBuffer[XSSFSheet] = {
    var buff: ArrayBuffer[XSSFSheet] = ArrayBuffer()
    for(i <- 0 until wbc.getNumberOfSheets) yield {
      buff += wbc.getSheetAt(i)
    }
    buff
  }

  override def createEmptyDB(file: File, pages: Array[String]): Unit = {
    setUp()
    for(page <- pages) {
      page match {
        case DEPOSIT => createPage[Deposit](page)
        case CREDIT_CARD => createPage[CCtransaction](page)
        case DEBIT => createPage[CheckingTransaction](page)
        case CAR => createPage[Car](page)
        case FOOD => createPage[Food](page)
        case SHOPPING => createPage[Shopping](page)
        case TRAVEL => createPage[Travel](page)
        case NEST => createPage[Nest](page)
        case ENTERTAINMENT => createPage[Entertainment](page)
        case SUMMARY => createPage[Summary](page)
        case SERVICES => createPage[Services](page)
        case TRANSFER => createPage[Transfer](page)
        case _ => createPage[Transaction](page)
      }
    }
  }


}

object YearBook {

  def apply(name: String): YearBook = new YearBook(name, PATH)

}

