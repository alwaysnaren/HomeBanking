package com.naren.route.scalafx.dialogs

import com.naren.route.entries.CCaccount
import com.naren.route.scalafx.skins.Buttons.confirmButtons
import scalafx.Includes._
import scalafx.scene.control.{Alert, ButtonType, Dialog, TextField}
import scalafx.scene.layout.GridPane
import com.naren.route.scalafx.skins.Labels._
import com.naren.route.scalafx.skins.DateTime.datePicker
import com.naren.route.utils.IDgenerator
import scalafx.scene.Node
import scalafx.scene.control.Alert.AlertType

object AddCreditcard {

  val name,bank,creditLine,ccNumber,expiryInfo,cvv = new TextField()

  val dialog: Dialog[CCaccount] = new Dialog[CCaccount] {
    initOwner(null)
    title = "Add new CreditCard"
    headerText = "Enter details"
  }

  dialog.dialogPane().buttonTypes = confirmButtons

  dialog.dialogPane().content = new GridPane {
    vgap = 20
    hgap = 20

    add(NICK_NAME,0,0)
    add(name,1,0)
    add(BANK_NAME,0,1)
    add(bank,1,1)
    add(CREDIT_LINE,0,2)
    add(creditLine,1,2)
    add(ACCOUNT_NUMBER,0,3)
    add(ccNumber,1,3)
    add(EXPIRY_INFO,0,4)
    add(expiryInfo,1,4)
    add(CVV,0,5)
    add(cvv,1,5)
    add(DATE_PICKER,0,6)
    add(datePicker,1,6)
  }

  val okButton: Node = dialog.dialogPane().lookupButton(ButtonType.OK)
  okButton.disable <== (name.text.isEmpty || bank.text.isEmpty || ccNumber.text.isEmpty
    || expiryInfo.text.isEmpty || cvv.text.isEmpty)

  def showAndWait(): Option[CCaccount] = {
    dialog.resultConverter = button => {
      if(button == ButtonType.OK) {
        val alert = new Alert(AlertType.Confirmation) {
          initOwner(null)
          title = "Confirmation"
          headerText = "Adding CreditCard"
          contentText = "Confirming to add the account?"
        }
        val res = alert.showAndWait()
        res match {
          case Some(ButtonType.OK) => {
            val dateCreated = datePicker.getValue.toString
            CCaccount(IDgenerator.CreditCardID(dateCreated), name.text(), bank.text(),
              creditLine.text().toInt, ccNumber.text().toLong, expiryInfo.text(),
              cvv.text(),datePicker.getValue.toString, true)
          }
          case _ => null
        }
      } else null
    }
    val result = dialog.showAndWait()
    result match {
      case Some(CCaccount(s,a,b,x,c,d,e,f,g)) => Some(CCaccount(s,a,b,x,c,d,e,f,g))
      case _ => None
    }
  }

}
