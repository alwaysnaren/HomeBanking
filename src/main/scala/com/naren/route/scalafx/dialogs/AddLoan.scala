package com.naren.route.scalafx.dialogs

import com.naren.route.constants.KeyWords.LOAN
import com.naren.route.entries.AssetLoans
import com.naren.route.scalafx.skins.Alerts
import com.naren.route.scalafx.skins.DateTime.datePicker
import com.naren.route.scalafx.skins.Labels._
import scalafx.Includes._
import com.naren.route.utils.IDgenerator
import scalafx.scene.Node
import scalafx.scene.control.{ButtonType, Dialog, TextField}
import scalafx.scene.layout.GridPane
import com.naren.route.utils.Implicits.{DoubleOps, StringOps}

object AddLoan {

  val commodity,nickName,costPrice,vendor,downPayment,loanAmount,lender,interestRate =
    new TextField()
  val dialog: Dialog[AssetLoans] = addDialog(LOAN)

  dialog.dialogPane().content = new GridPane {
    hgap = 5
    vgap = 5

    add(DATE_PICKER,0,0)
    add(datePicker,1,0)
    add(COMMODITY,0,1)
    add(commodity,1,1)
    add(NICK_NAME,0,2)
    add(nickName,1,2)
    add(COST_PRICE,0,3)
    add(costPrice,1,3)
    add(DOWN_PAYMENT,0,4)
    add(downPayment,1,4)
    add(LOAN_AMOUNT,0,5)
    add(loanAmount,1,5)
    add(INTEREST_RATE,0,6)
    add(interestRate,1,6)
    add(LENDER,0,7)
    add(lender,1,7)
  }

  val okButton: Node = dialog.dialogPane().lookupButton(ButtonType.OK)

  okButton.disable <== (costPrice.text.isEmpty || loanAmount.text.isEmpty || interestRate.text.isEmpty
    || lender.text.isEmpty)

  def showAndWait(): Option[AssetLoans] = {
    dialog.resultConverter = button => {
      if (button == ButtonType.OK) {
        val res = Alerts.confirmation("Adding a Loan", "Confirming to add Loan")
          .showAndWait()
        res match {
          case Some(ButtonType.OK) => {
            val dateCreated = datePicker.getValue.toString
            AssetLoans(IDgenerator.AssetID(dateCreated), dateCreated.appendTime, commodity.text(),
              nickName.text(), costPrice.text().toDouble.format, downPayment.text().toDouble.format,
              loanAmount.text().toDouble.format, interestRate.text().toDouble.format, lender.text())
          }
          case _ => null
        }
      } else null
    }
    val result = dialog.showAndWait()
    result match {
      case Some(AssetLoans(a,b,c,d,e,f,g,h,i)) => Some(AssetLoans(a,b,c,d,e,f,g,h,i))
      case _ => None
    }
  }
}
