package com.naren.route.drivers

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.layout.VBox
import com.naren.route.scalafx.skins.Buttons._
import data.Reader.TransactionModel

object HomeBanking extends JFXApp{

  Thread.currentThread().setUncaughtExceptionHandler(
    (_: Thread, ex: Throwable) => {
      ex.printStackTrace()
      new Alert(AlertType.Error) {
        initOwner(owner)
        title = "Startup Exception"
        headerText = "Exception: " + ex.getClass
        contentText = Option(ex.getMessage).getOrElse("")
      }.showAndWait()
    }
  )

  val vBox: VBox = new VBox{
    children = Seq(
      DepositEntry,
      DebitEntry,
      CcEntry,
      LoanEntry,
      TransferEntry,
      CreatePage,
      AddCC,
      AddChecking,
      AddHouse,
      AddStock
    )
  }

  val model = new TransactionModel

  DepositEntry.onAction = _ => model.onDepositEntry()
  DebitEntry.onAction = _ => model.onDebitEntry()
  CcEntry.onAction = _ => model.onCcEntry()
  LoanEntry.onAction = _ => model.onLoanEntry()
  TransferEntry.onAction = _ => model.onTransferEntry()
  AddChecking.onAction = _ => model.onAddChecking()
  CreatePage.onAction = _ => model.onCreatePage()
  AddCC.onAction = _ => model.onAddCreditCard()
  AddHouse.onAction = _ => model.onAddHouse()
  AddStock.onAction = _ => None

  /** Main view*/
  stage = new JFXApp.PrimaryStage {
    maximized = true
    scene = new Scene(1024,800) {
      title = "Home Banking"
      root = vBox
    }
  }

}
