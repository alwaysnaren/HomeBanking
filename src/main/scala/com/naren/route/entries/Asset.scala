package com.naren.route.entries

import com.naren.route.dataStructure.Record

case class Asset(
                assetID: Long,
                dateTime: String,
                commodity: String,
                costPrice: Double,
                nickName: String
                ) extends Record[Asset]