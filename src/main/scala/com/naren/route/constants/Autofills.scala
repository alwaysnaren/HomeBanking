package com.naren.route.constants

import Pages._
import KeyWords.NICKNAME
import com.naren.route.constants.pages.{Fetch, Metadata}
import com.naren.route.dataType.KeyValues
import com.naren.route.entries.{CCaccount, CheckingAccount}
import scalafx.collections.ObservableBuffer

object Autofills {
  lazy val checkingAccounts: ObservableBuffer[String] = {
    val buff = ObservableBuffer[String]()
    //for(acc <- Metadata.getValues(CHECKING_ACCOUNTS)) buff += acc
    for(acc <- Fetch.column[CheckingAccount,String](CHECKING_ACCOUNTS,NICKNAME))
      buff += acc
    buff
  }

  lazy val ccCards: ObservableBuffer[String] = {
    val buff = ObservableBuffer[String]()
    //for(acc <- Metadata.getValues(CREDIT_CARDS)) buff += acc
    for(acc <- Fetch.column[CCaccount,String](CREDIT_CARDS,NICKNAME))
      buff += acc
    buff
  }



}
