package com.naren.route.scalafx.dialogs

import com.naren.route.entries.PageInfo
import com.naren.route.scalafx.skins.Buttons.confirmButtons
import scalafx.scene.control.{Alert, ButtonType, Dialog, TextField}
import scalafx.Includes._
import scalafx.scene.layout.GridPane
import com.naren.route.scalafx.skins.Labels._
import scalafx.scene.Node
import scalafx.scene.control.Alert.AlertType

object CreatePage {

  val fileName,pageName,header = new TextField()

  val dialog: Dialog[PageInfo] = new Dialog[PageInfo] {
    initOwner(null)
    title = "Add a Page"
    headerText = "Enter Details"
  }

  dialog.dialogPane().buttonTypes = confirmButtons
  val okButton: Node = dialog.dialogPane().lookupButton(ButtonType.OK)

  dialog.dialogPane().content = new GridPane {
    hgap = 10
    vgap = 10

    add(FILE_NAME,0,0)
    add(fileName,1,0)
    add(PAGE_NAME,0,1)
    add(pageName,1,1)
    add(HEADER,0,2)
    add(header,1,2)
  }

  okButton.disable <== (fileName.text.isEmpty || pageName.text.isEmpty)

  def showAndWait(): Option[PageInfo] = {
    dialog.resultConverter = button => {
      if(button == ButtonType.OK) {
        val alert = new Alert(AlertType.Confirmation) {
          initOwner(null)
          title = "Confirmation"
          headerText = "Adding new Page"
          contentText = "Confirming to add the page?"
        }

        val res = alert.showAndWait()
        res match {
          case Some(ButtonType.OK) => {
            PageInfo(fileName.text(),pageName.text(),header.text().split(','))
          }
          case _ => null
        }
      } else null
    }

    val result = dialog.showAndWait()

    result match {
      case Some(PageInfo(a,b,c)) => Some(PageInfo(a,b,c))
      case _ => None
    }
  }

}
