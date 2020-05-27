package com.naren.route.entries.input

import com.naren.route.constants.KeyWords.LOAN

case class Loan(
                 txnID: Long,
                 loanFor: String,
                 accountName: String,
                 amount: Double
               ) extends Entry(LOAN) {

}
