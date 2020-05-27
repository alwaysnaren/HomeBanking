package com.naren.route.utils

import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFRow
import com.naren.route.utils.TimeMachine._
import scala.collection.mutable.ArrayBuffer

object Implicits {

  /** String */
  implicit class StringOps(str: String) {

    def firstCaps: String = str.head.toUpper + str.tail

    def toMonthNum: Int = {
      if(str.startsWith("0")) str.tail.toInt
      else str.toInt
    }

    def appendTime: String = str + " " + getTime

  }

  /** Double */
  implicit class DoubleOps(n:Double) {

    def format: Double = {
      val numArr = n.toString.split('.')
      val decimal = numArr(1)
      val newDecimal = shrink(decimal.toLong)
      s"${numArr(0)}.$newDecimal".toDouble
    }

    private def shrink(a: Long): Int = {
      var temp = a
      while (temp/100 != 0) {
        if(temp%10 <= 5) temp /= 10
        else temp = (temp/10) + 1
      }
      temp.toInt
    }
  }

  /** XSSFRow */

  implicit class XSSFRowOps(row: XSSFRow) {
    def toStrArray: Array[String] = {
      var buff = ArrayBuffer[String]()
      for(i <- 0 until row.getLastCellNum) {
        buff += row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).toString
      }
      buff.toArray
    }
  }

  implicit class ObjectOps[N <: Object](obj: N) {
    def apply[T](name: String, args: AnyRef*): Any = {
      val clas = args.map(_.getClass)
      val meth = obj.getClass.getMethod(name, clas: _*)
      meth.invoke(obj,args: _*).asInstanceOf[T]
    }
  }

  def f[T >: Null <: AnyRef : Manifest]: Unit = None //T
  // same as above
  def f1[T >: Null <: AnyRef](implicit ev: Manifest[T]): Unit = None //T
}
