package com.naren.route.scalafx.dialogs

import com.naren.route.entries.House
import scalafx.scene.control.{Dialog, TextField}
import com.naren.route.constants.KeyWords.HOUSE
import scalafx.Includes._
import scalafx.scene.control._
import com.naren.route.scalafx.skins.Alerts
import scalafx.scene.layout._
import com.naren.route.scalafx.skins.DateTime.datePicker
import com.naren.route.scalafx.skins.Labels._
import com.naren.route.scalafx.skins.Radios._
import com.naren.route.utils.IDgenerator
import scalafx.scene.Node
import com.naren.route.utils.Implicits.{DoubleOps, StringOps}
import scalafx.event.ActionEvent
import scalafx.geometry.HPos
import scalafx.scene.layout.Priority.Always

object AddHouse {
  val column0Constraint: ColumnConstraints = new ColumnConstraints {
    fillWidth = true
    //halignment = HPos.Center
    hgrow = Priority.Always
    minWidth = 300
  }
  val column1Constraint: ColumnConstraints = new ColumnConstraints {
    //halignment = HPos.Right
    hgrow = Priority.Never
    minWidth = 80
    maxWidth = 100
  }
  val rectangleRowsConstraint: RowConstraints = new RowConstraints {
    vgrow = Priority.Always
    prefHeight = Region.USE_COMPUTED_SIZE
  }

  val address,nickName,costPrice,closingCosts,downPayment,lender,loanAmount,interestRate = new TextField()
  val dialog: Dialog[House] = addDialog(HOUSE)

//  val choice: HBox = new HBox(5) {
//    children = Seq(YES,NO)
//  }

  val sceneContent: GridPane = new GridPane {
    hgap = 5
    vgap = 5

    hgrow = Priority.Always
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
    //add(LOAN,0,5)
    //add(choice,1,5)

  }

  dialog.dialogPane().content = sceneContent

//  YES.onAction = (e: ActionEvent) => {
//
//    sceneContent.vgrow = Always
//    sceneContent.hgrow = Always
//    sceneContent.add(DOWN_PAYMENT,0,6)
//    sceneContent.add(downPayment,1,6)
//    sceneContent.add(LENDER,0,7)
//    sceneContent.add(lender,1,7)
//    sceneContent.add(LOAN_AMOUNT,0,8)
//    sceneContent.add(loanAmount,1,8)
//    sceneContent.add(INTEREST_RATE,0,9)
//    sceneContent.add(interestRate,1,9)
//  }

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
              address.text(), costPrice.text().toDouble.format, closingCosts.text().toDouble.format)
          }
          case _ => null
        }
      } else null
    }
    val result = dialog.showAndWait()
    result match {
      case Some(House(a,b,c,d,e,f)) => Some(House(a,b,c,d,e,f))
      case _ => None
    }
  }

}
