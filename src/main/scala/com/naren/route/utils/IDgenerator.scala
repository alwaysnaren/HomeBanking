package com.naren.route.utils

import scala.util.Random

object IDgenerator {

  def randInt: Int = Random.nextInt(8999)

  def CheckingID(date: String): Long = {
    val dateNum = date.filterNot(_ == '-').toInt
    ("13" + s"$dateNum" + s"$randInt".padTo(4,5)).toLong
  }
  def CreditCardID(date: String): Long = {
    val dateNum = date.filterNot(_ == '-').toInt
    ("17" + s"$dateNum" + s"$randInt".padTo(4,9)).toLong
  }
  def TransactionID(date: String): Long = {
    val dateNum = date.filterNot(_ == '-').toInt
    ("19" + s"$dateNum" + s"$randInt".padTo(4,7)).toLong
  }
  def AssetID(date: String): Long = {
    val dateNum = date.filterNot(_ == '-').toInt
    ("15" + s"$dateNum" + s"$randInt".padTo(4,3)).toLong
  }

}