package com.naren.route.constants.pages

import com.naren.route.constants.FileSystem.{MASTER, PATH}
import com.naren.route.constants.Pages.CHECKING_ACCOUNTS
import com.naren.route.dataStructure.{Book, Page}
import com.naren.route.entries.CheckingAccount

object CheckingAccounts extends Page[CheckingAccount](CHECKING_ACCOUNTS,Book(MASTER,PATH)){

//  def accArray: Array[CheckingAccount] = getRows.map(CheckingAccount(_))
//  def getAccID(key: String): Long = accArray.find(c => c.nickName == key).get.accountID
//  def nickNames: Array[String] = accArray.map(_.nickName)
//  def getName(key: Long): String = accArray.find(ck => ck.accountID == key).get.nickName

}
