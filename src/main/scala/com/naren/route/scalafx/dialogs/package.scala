package com.naren.route.scalafx

import scalafx.scene.control.Dialog
import scalafx.Includes._
import com.naren.route.scalafx.skins.Buttons.confirmButtons

package object dialogs {

  def addDialog[R](name: String): Dialog[R] = new Dialog[R] {
    initOwner(null)
    title = s"Add $name"
    headerText = "Enter details"
    dialogPane().buttonTypes = confirmButtons
  }

}
