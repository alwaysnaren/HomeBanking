package com.naren.route.dataType

import com.naren.route.dataStructure.Record
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.XSSFRowOps


case class KeyValues(key: String, values: Array[String]) extends Record[KeyValues]

object KeyValues extends Record[KeyValues] {

  def apply(row: XSSFRow): KeyValues = {
    val records = row.toStrArray
    new KeyValues(
      records(0),
      records(1).split(',')
    )
  }

  def apply(rows: Array[XSSFRow]): Array[KeyValues] = rows.map(KeyValues(_))
}