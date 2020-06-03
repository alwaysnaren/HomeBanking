package com.naren.route.entries.input

import com.naren.route.constants.KeyWords.LOAN

case class Loan(
                 txnID: Long,
                 dateTime: String,
                 commodity: String,
                 nickName: String,
                 lender: String,
                 amount: Double
               ) extends Entry(LOAN) {

}
