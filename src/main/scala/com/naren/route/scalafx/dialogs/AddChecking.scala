package com.naren.route.scalafx.dialogs

import com.naren.route.entries.CheckingAccount
import com.naren.route.scalafx.skins.Buttons.confirmButtons
import com.naren.route.scalafx.skins.DateTime.datePicker
import com.naren.route.utils.Implicits.StringOps
import scalafx.scene.control.{Alert, ButtonType, Dialog, TextField}
import scalafx.Includes._
import scalafx.scene.layout.GridPane
import com.naren.route.scalafx.skins.Labels._
import com.naren.route.utils.IDgenerator
import scalafx.scene.Node
import scalafx.scene.control.Alert.AlertType

object AddChecking {

  val name,bank,address,accountNumber,routingNumber = new TextField()

  val dialog: Dialog[CheckingAccount] = new Dialog[CheckingAccount] {
    initOwner(null)
    title = "Add Debit account"
    headerText = "Enter details"
  }

  dialog.dialogPane().buttonTypes = confirmButtons

  dialog.dialogPane().content = new GridPane {
    hgap = 5
    vgap = 5

    add(NICK_NAME,0,0)
    add(name,1,0)
    add(BANK_NAME,0,1)
    add(bank,1,1)
    add(ADDRESS,0,2)
    add(address,1,2)
    add(ACCOUNT_NUMBER,0,3)
    add(accountNumber,1,3)
    add(ROUTING_NUMBER,0,4)
    add(routingNumber,1,4)
    add(DATE_PICKER,0,5)
    add(datePicker,1,5)
  }

  val okButton: Node = dialog.dialogPane().lookupButton(ButtonType.OK)

  okButton.disable <== (name.text.isEmpty || bank.text.isEmpty || accountNumber.text.isEmpty)

  def showAndWait(): Option[CheckingAccount] = {
    dialog.resultConverter = button => {
      if(button == ButtonType.OK) { /** If OK, converting result to DebitAccount */
        val alert = new Alert(AlertType.Confirmation) {
          initOwner(null)
          title = "Confirmation"
          headerText = "Adding Debit account"
          contentText = "Confirming to add the account?"
        }
        val res = alert.showAndWait()
        res match {
          case Some(ButtonType.OK) => {
            val dateCreated = datePicker.getValue.toString
            CheckingAccount(IDgenerator.CheckingID(dateCreated), name.text(), bank.text(), address.text(),
              accountNumber.text().toLong, routingNumber.text().toLong,
              datePicker.getValue.toString.appendTime, true)
          }
          case _ => null
        }
      } else null
    }
    val result = dialog.showAndWait()
    result match {
      case Some(CheckingAccount(s,a,b,x,c,d,e,f)) => Some(CheckingAccount(s,a,b,x,c,d,e,f))
      case _ => None
    }
  }
}
