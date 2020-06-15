package com.naren.route.scalafx.skins


import scalafx.scene.control.{RadioButton, ToggleGroup}


object Radios {

  lazy val tg = new ToggleGroup()

  val YES: RadioButton = new RadioButton("Yes") {
    toggleGroup = tg
  }

  val NO: RadioButton = new RadioButton("No") {
    toggleGroup = tg
  }


}
