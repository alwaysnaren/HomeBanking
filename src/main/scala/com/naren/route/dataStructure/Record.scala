package com.naren.route.dataStructure

import com.naren.route.utils.Extract

import scala.collection.mutable.ArrayBuffer
import scala.reflect.runtime.universe.{MethodSymbol, TypeTag, typeOf}


abstract class Record[T: TypeTag: Extract](implicit ex: Extract[T]) {

  def fields: List[String] = typeOf[T].members.collect {
    case m: MethodSymbol if m.isCaseAccessor => m.name.toString
  }.toList.reverse

  def getColumnsCount: Int = fields.length

  def toArray: Array[Any] = {
    var buff = ArrayBuffer[Any]()
    for(f <- this.getClass.getDeclaredFields) {
      f.setAccessible(true)
      buff += f.get(this)
    }
    buff.toArray
  }

  def as[T]: T = asInstanceOf[T]

}