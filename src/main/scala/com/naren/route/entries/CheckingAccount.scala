package com.naren.route.entries

import com.naren.route.dataStructure.Record

case class CheckingAccount(
                        accountID: Long,
                        nickName: String,
                        bankName: String,
                        address: String,
                        accountNumber: Long,
                        routingNumber: Long,
                        dateCreated: String,
                        active: Boolean
                       ) extends Record[CheckingAccount]


