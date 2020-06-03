package com.naren.route.constants

import Pages._
import com.naren.route.constants.pages.{Fetch, Metadata}
import com.naren.route.dataType.KeyValues
import scalafx.collections.ObservableBuffer

object Autofills {
  lazy val checkingAccounts: ObservableBuffer[String] = {
    val buff = ObservableBuffer[String]()
    //for(acc <- Metadata.getValues(CHECKING_ACCOUNTS)) buff += acc
    for(acc <- Fetch.value[KeyValues,Array[String]](CHECKING_ACCOUNTS,LOOKUPS,"key","values"))
      buff += acc
    buff
  }

  lazy val ccCards: ObservableBuffer[String] = {
    val buff = ObservableBuffer[String]()
    //for(acc <- Metadata.getValues(CREDIT_CARDS)) buff += acc
    for(acc <- Fetch.value[KeyValues,Array[String]](CREDIT_CARDS,LOOKUPS,"key","values"))
      buff += acc
    buff
  }



}
