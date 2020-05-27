package com.naren.route.entries.input

import com.naren.route.constants.KeyWords.TRANSFER

case class Transfer(
                 purpose: String = "",
                 fromACC: String,
                 toACC: String,
                 amount: Double
               ) extends Entry(TRANSFER) {

}
