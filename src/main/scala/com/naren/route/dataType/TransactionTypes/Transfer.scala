package com.naren.route.dataType.TransactionTypes

import com.naren.route.dataStructure.Record

case class Transfer(
                     txnID: Long,
                     dateTime: String,
                     fromAcc: Long,
                     toAcc: Long,
                     amount: Double,
                     var tillDate: Double
                   ) extends Record[Transfer]{

}
