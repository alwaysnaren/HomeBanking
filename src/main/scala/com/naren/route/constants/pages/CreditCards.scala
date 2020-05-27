package com.naren.route.constants.pages

import com.naren.route.dataStructure.{Book, Page}
import com.naren.route.entries.CCaccount
import com.naren.route.constants.Pages.CREDIT_CARDS
import com.naren.route.constants.FileSystem.{MASTER,PATH}

object CreditCards extends Page[CCaccount](CREDIT_CARDS, Book(MASTER,PATH)){

  def accArray: Array[CCaccount] = getRows.map(CCaccount(_))
  def getAccID(key: String): Long = accArray.find(c => c.nickName == key).get.accountID
  def nickNames: Array[String] = accArray.map(_.nickName)
  def getName(key: Long): String = accArray.find(cc => cc.accountID == key).get.nickName
  def getCreditLine(key: String): Int = accArray.find(cc => cc.nickName == key).get.creditLine
}
