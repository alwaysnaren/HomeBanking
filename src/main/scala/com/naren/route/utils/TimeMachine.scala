package com.naren.route.utils

import java.time.{LocalDate, LocalDateTime, LocalTime}
import java.time.format.DateTimeFormatter

object TimeMachine {

  def getDateTime: String = LocalDateTime.now()
    .format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss"))

  def getDTLong: Long = LocalDateTime.now()
    .format(DateTimeFormatter.ofPattern("MMddYYYYhhmmss")).toLong

  def getTime: String = LocalTime.now()
    .format(DateTimeFormatter.ofPattern("hh:mm:ss"))

  def getTimeLong: Long = LocalTime.now()
    .format(DateTimeFormatter.ofPattern("hhmmss")).toLong

  def getDateLong: String = LocalDate.now()
    .format(DateTimeFormatter.ofPattern("MMddYYYY"))
}