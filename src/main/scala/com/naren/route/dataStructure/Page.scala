package com.naren.route.dataStructure

import com.naren.route.utils.Extract
import com.naren.route.utils.Implicits.ObjectOps
import com.naren.route.utils.Implicits.{DoubleOps, StringOps, XSSFRowOps}
import org.apache.poi.xssf.usermodel.{XSSFRow, XSSFSheet}

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag


case class Page[N <: Record[N]: ClassTag](name: String, parent: Database = null)(implicit ex: Extract[N]) {

  def xssfSheet: XSSFSheet = parent.wb.getSheet(name)

  /** Invoked/used only on YearBooks */
  lazy val prevYBpage: Option[Page[N]] = {
    val yb = parent.asInstanceOf[YearBook]
    val prevYB = yb.prevYB
    if(prevYB.isDefined) {
      val page = prevYB.get.getPage[N](name)
      Option(page)
    } else None
  }

  def getRow(rowNum: Int): N = {
    if(rowNum > 0 && rowNum <= getLastRowNum) {
      val row = xssfSheet.getRow(rowNum).toStrArray
      ex(row)
    } else null.asInstanceOf[N]
  }

  def getLastRowNum: Int = xssfSheet.getLastRowNum
  def getLatestRow: Option[N] =
    if(getLastRowNum > 0) Some(getRow(getLastRowNum))
    else None

  def getLastRecIfExists: Option[N] = {
    if(getLastRowNum > 0) Some(getRow(getLastRowNum))
    else getRecFromPrevYBpage
  }

  def getRecFromPrevYBpage: Option[N] =
    if(prevYBpage.isDefined) prevYBpage.get.getLatestRow else None

  def getlastRecFromkey(key: Any, find: String): Option[N] =
    getAllRows.filter(_(find) == key).lastOption

  def getRecFromKeyIfExists(key: Any, filter: String): Option[N] = {
    var page = Option(this)
    while(page.isDefined) {
      val rec = page.get.getlastRecFromkey(key,filter)
      if(rec.isDefined) return rec
      else page = page.get.prevYBpage
    }
    None
  }

  def getAllRows: Array[N] = {
    var buff = ArrayBuffer[N]()
    for(i <- 1 to getLastRowNum) buff += getRow(i)
    buff.toArray
  }

  def getRows: Array[XSSFRow] = {
    var buff = ArrayBuffer[XSSFRow]()
    for(i <- 1 to getLastRowNum) {
      buff += xssfSheet.getRow(i)
    }
    buff.toArray
  }

  def addRecord(record: N): Unit = {
    val newRow = record.toArray
    val row = xssfSheet.createRow(getLastRowNum+1)
    for(c <- newRow.indices) {
      val cell = row.createCell(c)
      newRow(c) match {
        case t: String => cell.setCellValue(t.toString.firstCaps)
        case t: Int => cell.setCellValue(t.toInt)
        case t: Double => cell.setCellValue(t.toDouble.format)
        case t: Boolean => cell.setCellValue(t.asInstanceOf[Boolean])
        case t: Float => cell.setCellValue(t.toFloat)
        case _ => cell.setCellValue(newRow(c).toString)
      }
      xssfSheet.autoSizeColumn(c)
    }
    parent.push()
  }

}