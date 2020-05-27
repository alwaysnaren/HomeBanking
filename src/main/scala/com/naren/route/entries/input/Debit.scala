package com.naren.route.entries.input

import com.naren.route.constants.KeyWords.DEBIT

case class Debit(
                  stream: String,
                  category: String,
                  vendor: String,
                  accountName: String,
                  amount: Double
                ) extends Entry(DEBIT)
