package com.naren.route.scalafx.dialogs

import scalafx.Includes._
import scalafx.scene.control._
import com.naren.route.constants.Autofills.checkingAccounts
import com.naren.route.scalafx.skins.Alerts
import scalafx.scene.layout.GridPane
import com.naren.route.scalafx.skins.DateTime.datePicker
import com.naren.route.scalafx.skins.Labels._
import com.naren.route.utils.IDgenerator
import scalafx.scene.Node
import com.naren.route.utils.Implicits.{DoubleOps, StringOps}
import com.naren.route.constants.KeyWords.DEBIT
import com.naren.route.constants.Pages.CHECKING_ACCOUNTS
import com.naren.route.constants.pages.{CheckingAccounts, Fetch}
import com.naren.route.dataType.TransactionTypes.CheckingTransaction
import com.naren.route.entries.CheckingAccount

object DebitEntry {
  val stream,source,purpose,vendor,amount = new TextField()
  val accountName: ChoiceBox[String] = new ChoiceBox[String](checkingAccounts) {
    tooltip = Tooltip("Account")
    selectionModel().select("Select")
  }

  val dialog: Dialog[CheckingTransaction] = addDialog[CheckingTransaction](DEBIT)

  dialog.dialogPane().content = new GridPane {
    hgap = 5
    vgap = 5

    add(DATE_PICKER,0,0)
    add(datePicker,1,0)
    add(STREAM,0,1)
    add(stream,1,1)
    add(SOURCE,0,2)
    add(source,1,2)
    add(PURPOSE,0,3)
    add(purpose,1,3)
    add(VENDOR,0,4)
    add(vendor,1,4)
    add(AMOUNT,0,5)
    add(amount,1,5)
    add(ACCOUNT,0,6)
    add(accountName,1,6)
  }

  val okButton: Node = dialog.dialogPane().lookupButton(ButtonType.OK)

  okButton.disable <== (stream.text.isEmpty || source.text.isEmpty || vendor.text.isEmpty ||
    amount.text.isEmpty)

  def showAndWait(): Option[CheckingTransaction] = {
    dialog.resultConverter = button => {
      if(button == ButtonType.OK) {
        val res = Alerts.confirmation("Adding Debit Entry",
          "Confirming to add payment thru checking account")
          .showAndWait()
        res match {
          case Some(ButtonType.OK) => {
            val dateCreated = datePicker.getValue.toString
            //val accID = CheckingAccounts.getAccID(accountName.getValue)
            val accID =
              Fetch.value[CheckingAccount,Long](accountName.getValue,CHECKING_ACCOUNTS,"nickName","accountID")
            CheckingTransaction(IDgenerator.TransactionID(dateCreated), dateCreated.appendTime, stream.text(),
              source.text(), purpose.text(), vendor.text(), amount.text().toDouble.format,
              accID, amount.text().toDouble.format)
          }
          case _ => null
        }
      } else null
    }
    val result = dialog.showAndWait()
    result match {
      case Some(CheckingTransaction(a, b, c, d, e, f, g, h, i)) => Some(CheckingTransaction(a, b, c, d, e, f, g, h, i))
      case _ => None
    }
  }
}
