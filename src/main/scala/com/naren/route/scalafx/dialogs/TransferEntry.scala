package com.naren.route.scalafx.dialogs

import com.naren.route.constants.Autofills.checkingAccounts
import com.naren.route.constants.KeyWords.LOAN
import com.naren.route.dataType.TransactionTypes.Transfer
import com.naren.route.entries.{AssetLoans, CheckingAccount}
import com.naren.route.scalafx.skins.Alerts
import com.naren.route.scalafx.skins.DateTime.datePicker
import com.naren.route.scalafx.skins.Labels._
import com.naren.route.utils.{IDgenerator, Implicits}
import scalafx.Includes._
import scalafx.scene.Node
import scalafx.scene.control._
import scalafx.scene.layout.GridPane
import Implicits.{DoubleOps, StringOps}
import com.naren.route.constants.Pages.CHECKING_ACCOUNTS
import com.naren.route.constants.pages.Fetch
import com.naren.route.scalafx.dialogs.DebitEntry.accountName


object TransferEntry {

  val amount = new TextField()
  val fromAcc: ChoiceBox[String] = new ChoiceBox[String](checkingAccounts) {
    tooltip = Tooltip("Account")
    selectionModel().select("Select")
  }
  val toAcc: ChoiceBox[String] = new ChoiceBox[String](checkingAccounts) {
    tooltip = Tooltip("Account")
    selectionModel().select("Select")
  }
  val dialog: Dialog[Transfer] = addDialog(LOAN)

  dialog.dialogPane().content = new GridPane {
    hgap = 5
    vgap = 5

    add(DATE_PICKER,0,0)
    add(datePicker,1,0)
    add(FROM_ACC,0,1)
    add(fromAcc,1,1)
    add(TO_ACC,0,2)
    add(toAcc,1,2)
    add(AMOUNT,1,3)
    add(amount,0,3)

  }

  val okButton: Node = dialog.dialogPane().lookupButton(ButtonType.OK)

  okButton.disable <== amount.text.isEmpty

  def showAndWait(): Option[Transfer] = {
    dialog.resultConverter = button => {
      if (button == ButtonType.OK) {
        val res = Alerts.confirmation("Adding a Transfer", "Confirming to add Transfer")
          .showAndWait()
        res match {
          case Some(ButtonType.OK) => {
            val trnAmount = amount.text().toDouble.format
            val fromAccID =
              Fetch.value[CheckingAccount,Long](fromAcc.getValue, CHECKING_ACCOUNTS,"nickName","accountID")
            val toAccID =
              Fetch.value[CheckingAccount,Long](toAcc.getValue, CHECKING_ACCOUNTS,"nickName","accountID")
            val dateCreated = datePicker.getValue.toString
            Transfer(IDgenerator.TransactionID(dateCreated),dateCreated.appendTime,fromAccID,
              toAccID,trnAmount,trnAmount)
          }
          case _ => null
        }
      } else null
    }
    val result = dialog.showAndWait()
    result match {
      case Some(Transfer(a,b,c,d,e,f)) => Some(Transfer(a,b,c,d,e,f))
      case _ => None
    }
  }
}
