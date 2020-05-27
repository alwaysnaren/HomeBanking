package com.naren.route.scalafx.skins

import com.naren.route.constants.KeyWords.{DEPOSIT, CREDIT_CARD, DEBIT, LOAN, TRANSFER}
import scalafx.scene.control.{Button, ButtonType}

object Buttons {

  lazy val confirmButtons: Seq[ButtonType] = Seq(ButtonType.OK, ButtonType.Cancel)

  val AddHouse: Button = new Button {
    text = "Add House"
  }

  val AddStock: Button = new Button {
    text = "Add Stock"
  }

  val AddCC: Button = new Button {
    text = "Add CC"
  }

  val AddChecking: Button = new Button {
    text = "AddChecking"
  }

  val CreatePage: Button = new Button {
    text = "CreatePage"
  }

  val DepositEntry: Button = new Button {
    text = DEPOSIT
  }

  val DebitEntry: Button = new Button {
    text = DEBIT
  }

  val CcEntry: Button = new Button {
    text = CREDIT_CARD
  }

  val LoanEntry: Button = new Button {
    text = LOAN
  }

  val TransferEntry: Button = new Button {
    text = TRANSFER
  }
}
