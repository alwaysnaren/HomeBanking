package com.naren.route.scalafx.dialogs

import com.naren.route.entries.House
import scalafx.scene.control.{Dialog, TextField}
import com.naren.route.constants.KeyWords.HOUSE
import scalafx.Includes._
import scalafx.scene.control._
import com.naren.route.scalafx.skins.Alerts
import scalafx.scene.layout.GridPane
import com.naren.route.scalafx.skins.DateTime.datePicker
import com.naren.route.scalafx.skins.Labels._
import com.naren.route.utils.IDgenerator
import scalafx.scene.Node
import com.naren.route.utils.Implicits.{DoubleOps, StringOps}

object AddHouse {

  val address,nickName,costPrice,closingCosts,downPayment,lender,loanAmount,interestRate = new TextField()
  val dialog: Dialog[House] = addDialog(HOUSE)
  dialog.dialogPane().content = new GridPane {
    hgap = 5
    vgap = 5

    add(DATE_PICKER,0,0)
    add(datePicker,1,0)
    add(NICK_NAME,0,1)
    add(nickName,1,1)
    add(ADDRESS,0,2)
    add(address,1,2)
    add(COST_PRICE,0,3)
    add(costPrice,1,3)
    add(CLOSING_COSTS,0,4)
    add(closingCosts,1,4)
    add(DOWN_PAYMENT,0,5)
    add(downPayment,1,5)
    add(LENDER,0,6)
    add(lender,1,6)
    add(LOAN_AMOUNT,0,7)
    add(loanAmount,1,7)
    add(INTEREST_RATE,0,8)
    add(interestRate,1,8)
  }

  val okButton: Node = dialog.dialogPane().lookupButton(ButtonType.OK)

  okButton.disable <== (address.text.isEmpty || costPrice.text.isEmpty)

  def showAndWait(): Option[House] = {
    dialog.resultConverter = button => {
      if (button == ButtonType.OK) {
        val res = Alerts.confirmation("Adding House", "Confirming to add House")
          .showAndWait()
        res match {
          case Some(ButtonType.OK) => {
            val dateCreated = datePicker.getValue.toString
            House(IDgenerator.AssetID(dateCreated), nickName.text(), dateCreated.appendTime,
              address.text(), costPrice.text().toDouble.format, closingCosts.text().toDouble.format,
              downPayment.text().toDouble.format, lender.text(), loanAmount.text().toDouble.format,
              interestRate.text().toDouble.format)
          }
          case _ => null
        }
      } else null
    }
    val result = dialog.showAndWait()
    result match {
      case Some(House(a,b,c,d,e,f,g,h,i,j)) => Some(House(a,b,c,d,e,f,g,h,i,j))
      case _ => None
    }
  }

}
