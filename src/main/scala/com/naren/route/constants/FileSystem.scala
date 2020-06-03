package com.naren.route.constants

import com.naren.route.fileSystem.Path
import com.naren.route.constants.Months._
import com.naren.route.constants.KeyWords._
import com.naren.route.dataStructure.YearBook
import com.naren.route.utils.TimeMachine
import com.naren.route.utils.Implicits.StringOps

object FileSystem {

  val PATH = new Path("D:\\SundayIndian\\Next\\Projects\\TrackSpendings\\prod\\temp")
  lazy val SEPERATOR = "\\"
  lazy val DEFAULT_PAGES = Array(
    SUMMARY,JAN,FEB,MAR,APR,MAY,JUN,JUL,AUG,SEP,OCT,NOV,DEC,DEPOSIT,CREDIT_CARD,DEBIT,CAR,FOOD,
    NEST,SHOPPING,SERVICES,TRAVEL,ENTERTAINMENT,LOANS
  )

  def DATE_TIME: String = TimeMachine.getDateTime
  def YEAR: Int = DATE_TIME.substring(6,10).toInt

  def YEAR_BOOK:String = s"Acc_$YEAR.xlsx"
  val MASTER: String = "Master.xlsx"
  lazy val PREV_FILE_NAME: String = s"ACC_${YEAR-1}.xlsx"
  def MONTH_NUM: Int = DATE_TIME.substring(0,2).toMonthNum
  def MONTH: String = monthName(MONTH_NUM)
  lazy val PREV_MONTH: String = {
    if(MONTH_NUM > 1) monthName(MONTH_NUM - 1)
    else monthName(12)
  }


}
