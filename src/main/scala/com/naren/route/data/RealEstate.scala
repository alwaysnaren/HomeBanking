package com.naren.route.data

case class RealEstate(name: String,
                      assetID: String,
                      cost: Double,
                      address: String,
                      loanIssued: Boolean,
                      loaner: String,
                      purchaseDate: String
                     )
  extends Asset(assetType = "RealEstate"){

}
