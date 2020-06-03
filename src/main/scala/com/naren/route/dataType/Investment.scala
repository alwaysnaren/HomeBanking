//package com.naren.route.dataType
//
//import org.apache.poi.xssf.usermodel.XSSFRow
//import com.naren.route.utils.Implicits.{DoubleOps, XSSFRowOps}
//import com.naren.route.constants.KeyWords.DEPOSIT
//import com.naren.route.dataStructure.Record
//import com.naren.route.dataType.TransactionTypes.Deposit
//
//
//case class Investment(
//                       txnID: Long,
//                       dateTime: String,
//                       transactionType: String,
//                       assetID: Long,
//                       stream: String,
//                       vendor: String,
//                       amount: Double,
//                       accountID: Long,
//                       invested: Double,
//                       returns: Double)
//  extends Record[Investment]{
//
//  def fromDeposit(dep: Deposit): Investment = {
//    new Investment(
//      dep.txnID,dep.dateTime,DEPOSIT,assetID,dep.purpose,dep.vendor,dep.amount,dep.accountID,
//      invested,returns+dep.amount
//    )
//  }
//}
