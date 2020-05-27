package com.naren.route.drivers

import com.naren.route.dataStructure.{Book, YearBook}
import com.naren.route.constants.FileSystem._
import com.naren.route.entries.CheckingAccount
import com.naren.route.constants.Pages.CHECKING_ACCOUNTS


object Run extends App {

  val yb = Book(MASTER,PATH)
  val page = yb.getPage[CheckingAccount](CHECKING_ACCOUNTS)
  val rows = page.getAllRows.toList
  println(rows)


//  val valu = naren("name")
//  println(valu.getClass)
}
