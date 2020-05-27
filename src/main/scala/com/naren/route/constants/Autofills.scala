package com.naren.route.constants

import Pages._
import com.naren.route.constants.pages.Metadata
import scalafx.collections.ObservableBuffer

object Autofills {
  lazy val checkingAccounts: ObservableBuffer[String] = {
    val buff = ObservableBuffer[String]()
    for(acc <- Metadata.getValues(CHECKING_ACCOUNTS)) buff += acc
    buff
  }

  lazy val ccCards: ObservableBuffer[String] = {
    val buff = ObservableBuffer[String]()
    for(acc <- Metadata.getValues(CREDIT_CARDS)) buff += acc
    buff
  }



}
