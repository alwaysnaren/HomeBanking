package com.naren.route.dataStructure

import java.io.File

import com.naren.route.constants.FileSystem.SEPERATOR
import com.naren.route.fileSystem.Path

case class Book(name: String, path: Path) extends Database(new File(path.dir+SEPERATOR+name)) {

  val file = new File(path.dir+SEPERATOR+name)
  if(!file.exists()) createEmptyDB(file)

}