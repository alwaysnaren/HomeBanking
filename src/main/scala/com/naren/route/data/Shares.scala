package com.naren.route.data

case class Shares(
                  name: String,
                  assetID: String,
                  cost: Double,
                  purchaseDate: String
                 ) extends Asset(assetType = "Shares"){

}
