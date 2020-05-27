package com.naren.route.scalafx.skins

import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType

object Alerts {

  def confirmation(header: String, content: String): Alert =
    new Alert(AlertType.Confirmation) {
      initOwner(null)
      title = "Confirmation"
      headerText = header
      contentText = content
    }


  def information(header: String, content: String): Alert =
    new Alert(AlertType.Information) {
      initOwner(null)
      title = "Information"
      headerText = header
      contentText = content
    }

  def failure(header: String, content: String): Alert =
    new Alert(AlertType.Error) {
      initOwner(null)
      title = "Failure"
      headerText = header
      contentText = content
    }
}
