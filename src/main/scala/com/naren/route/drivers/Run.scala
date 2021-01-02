package com.naren.route.drivers

import com.naren.route.dataStructure.{Book, YearBook}
import com.naren.route.constants.FileSystem._
import com.naren.route.entries.CheckingAccount
import com.naren.route.constants.Pages.CHECKING_ACCOUNTS
import com.naren.route.constants.pages.Fetch
import com.naren.route.constants.KeyWords._

object Run extends App {

  for(page <- Fetch.column[CheckingAccount,String](CHECKING_ACCOUNTS,"nickName")){
    println(page.toString)
  }




//  val valu = naren("name")
//  println(valu.getClass)
}
