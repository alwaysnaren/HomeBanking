package com.naren.route.constants.pages

import com.naren.route.dataStructure.{Book, Record}
import com.naren.route.executors.Initialize
import com.naren.route.utils.Extract
import com.naren.route.utils.Implicits.ObjectOps

import scala.reflect.ClassTag

object Fetch {

  val md: Book = Initialize.md

  def value[N <: Record[N]: ClassTag,C](key: Any, name: String, find: String, field: String)
                                       (implicit ex: Extract[N]): C = {
    val page = md.getPage[N](name)
    page.getAllRows.find(_(find) == key).get(field).asInstanceOf[C]
  }

  def column[N <: Record[N]: ClassTag](name: String, field: String)(implicit ex: Extract[N]): Array[Any] = {
    val page = md.getPage[N](name)
    page.getAllRows.map(_(field))
  }

}
