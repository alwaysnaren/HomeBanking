package com.naren.route.constants.pages

import com.naren.route.constants.FileSystem.{MASTER, PATH}
import com.naren.route.dataStructure.{Book, Page}
import com.naren.route.entries.House
import com.naren.route.constants.Pages.HOUSES

object Houses extends Page[House](HOUSES,Book(MASTER,PATH)){

//  def houseArray: Array[House] = getRows.map(House(_))
//  def getAssetID(key: String): Long = houseArray.find(h => h.nickName == key).get.assetID

}
