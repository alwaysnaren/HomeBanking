package com.naren.route.entries

import com.naren.route.dataStructure.Record

case class AssetLoans(
                assetID: Long,
                purchaseDate: String,
                commodity: String,
                nickName: String,
                costPrice: Double,
                downPayment: Double,
                loanAmount: Double,
                interestRate: Double,
                lender: String
                ) extends Record[AssetLoans]