package com.naren.route.dataType

import com.naren.route.dataStructure.Record
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.Implicits.XSSFRowOps


case class KeyValues(key: String, values: Array[String]) extends Record[KeyValues]
