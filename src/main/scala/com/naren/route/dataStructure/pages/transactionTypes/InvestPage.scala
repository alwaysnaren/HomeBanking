//package com.naren.route.dataStructure.pages.transactionTypes
//
//import com.naren.route.constants.KeyWords.INVESTMENT
//import com.naren.route.dataStructure.{Database, Page}
//import com.naren.route.dataType.Investment
//
//class InvestPage(rows: Array[Investment], parent: Database)
//  extends Page(INVESTMENT) {
//
//  lazy val firstRecord: Investment = rows.head
//  def lastRow: Investment = rows.last
//
//  def getLatestRowOf(key: Long): Option[Investment] = rows.filter(i => i.assetID == key).lastOption
//
//}
//
//object InvestPage {
////  def apply(page: Page[Investment]): InvestPage = {
////    val invs = page.getAllRows
////    new InvestPage(invs,page.parent)
////  }
//}
