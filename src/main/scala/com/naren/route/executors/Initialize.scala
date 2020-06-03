package com.naren.route.executors

import com.naren.route.constants.FileSystem.{MASTER, PATH, YEAR_BOOK}
import com.naren.route.dataStructure.{Book, YearBook}

object Initialize {
  def md = Book(MASTER,PATH)
  def yb = YearBook(YEAR_BOOK, PATH)
}
