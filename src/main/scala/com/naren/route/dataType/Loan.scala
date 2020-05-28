package com.naren.route.dataType

import com.naren.route.dataStructure.Record

case class Loan(
                 txnID: Long,
                 dateTime: String,
                 TransactionType: String,
                 commodity: String,
                 assetId: Long,
                 vendor: String,
                 amount: Double,
                 accountID: Long,
                 tillDate: Double
               ) extends Record[Loan]
