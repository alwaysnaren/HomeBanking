package com.naren.route.dataStructure

import java.io.{File, FileInputStream, FileOutputStream}

import com.naren.route.utils.Implicits.StringOps
import org.apache.poi.ss.usermodel._
import org.apache.poi.xssf.usermodel.{XSSFRow, XSSFSheet, XSSFWorkbook}

import scala.reflect.ClassTag
import scala.reflect.runtime.universe.{MethodSymbol, TypeTag, typeOf}

class Database(file: File) extends XSSFWorkbook {

  def fieldsOf[T: TypeTag]: Array[String] = typeOf[T].members.collect {
    case m: MethodSymbol if m.isCaseAccessor => m.name.toString
  }.toArray.reverse

  val exists: Boolean = file.exists()

  var fileIn: FileInputStream = if(exists) new FileInputStream(file) else null
  var wb: XSSFWorkbook = if(exists) new XSSFWorkbook(fileIn) else null
  var HSTYLE: CellStyle = if(exists) wb.createCellStyle() else null
  var BOLD: Font = if(exists) wb.createFont() else null

  def setUp(): Unit = {
    fileIn = new FileInputStream(file)
    wb = new XSSFWorkbook(fileIn)
    HSTYLE = wb.createCellStyle()
    BOLD = wb.createFont()
    BOLD.setBold(true)
    HSTYLE.setFont(BOLD)
    HSTYLE.setAlignment(HorizontalAlignment.CENTER)
    HSTYLE.setShrinkToFit(true)
    HSTYLE.setWrapText(true)
    HSTYLE.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.getIndex)
    HSTYLE.setFillPattern(FillPatternType.SOLID_FOREGROUND)
  }

  def getPage[S <: Record[S]: ClassTag: Extract](name: String): Page[S] = Page[S](name,this)

//  override def getSheet(name: String): XSSFSheet =
//    getXSSFSheets.filter(s => s.getSheetName == name)(0)

  def createPage[P: TypeTag](name: String): Unit = {
    val page = wb.createSheet(name)
    val fields = fieldsOf[P]
    val row = page.createRow(0)
    createHeader(page,row,fields)
    push()
  }

  private def createHeader(sheet: XSSFSheet, row: XSSFRow, header: Array[String]): Unit = {
    for (c <- header.indices) {
      val cell = row.createCell(c)
      cell.setCellStyle(HSTYLE)
      cell.setCellValue(header(c).firstCaps)
      sheet.autoSizeColumn(c)
    }
  }

  def createPage(name: String, header: Array[String]): Unit = {
    val page = wb.createSheet(name)
    val row = page.createRow(0)
    createHeader(page,row,header)
    push()
  }

  def push(): Unit = {
    val fileout = new FileOutputStream(file)
    wb.write(fileout)
    fileout.close()
  }

  def createEmptyDB(file: File): Unit = {
    val fileout = new FileOutputStream(file)
    this.createSheet("ReplaceMe")
    this.write(fileout)
    fileout.close()
  }

  def createEmptyDB(file: File, pages: Array[String]): Unit = None
  def createEmptyDB(file: File, pages: Array[String], headers: Array[Array[String]]): Unit = None
}
