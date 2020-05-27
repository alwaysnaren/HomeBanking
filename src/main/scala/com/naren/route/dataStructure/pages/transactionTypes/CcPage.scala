package com.naren.route.dataStructure.pages.transactionTypes

import com.naren.route.dataStructure.{Database, Page}
import com.naren.route.dataType.TransactionTypes.CCtransaction
import com.naren.route.constants.KeyWords.CREDIT_CARD

class CcPage(rows: Array[CCtransaction], parent: Database)
  extends Page[CCtransaction](CREDIT_CARD){
}
