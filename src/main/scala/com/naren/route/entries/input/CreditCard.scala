package com.naren.route.entries.input

import com.naren.route.constants.KeyWords.CREDIT_CARD

case class CreditCard(
                       stream: String,
                       category: String,
                       vendor: String,
                       accountName: String,
                       amount: Double
                     ) extends Entry(CREDIT_CARD){

}
