package com.naren.route.scalafx.skins

import java.time.{LocalDate, LocalDateTime}

import scalafx.scene.control.DatePicker

object DateTime {

  val datePicker = new DatePicker(LocalDate.now()) {
    layoutX = 20
    layoutY = 20
  }
}
