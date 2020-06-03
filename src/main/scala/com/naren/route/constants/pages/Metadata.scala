package com.naren.route.constants.pages

import com.naren.route.constants.FileSystem.{MASTER, PATH}
import com.naren.route.constants.Pages.LOOKUPS
import com.naren.route.dataStructure.{Book, Page}
import com.naren.route.dataType.KeyValues

object Metadata extends Page[KeyValues](LOOKUPS,Book(MASTER,PATH)){

//  def lookUps: Array[KeyValues] = KeyValues(getRows)
//  def getValues(key: String): Array[String] = lookUps.find(k => k.key == key).get.values

}
