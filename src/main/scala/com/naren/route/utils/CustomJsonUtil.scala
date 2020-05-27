package com.naren.route.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

object CustomJsonUtil {

  val mapper = new ObjectMapper with ScalaObjectMapper
  mapper.registerModule(DefaultScalaModule)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)

  def toJson(value: Map[Symbol, Any]): String = toJson(value map { case (k,v) => k -> v})

  def toJson(value: Any): String = mapper.writeValueAsString(value)

  def toMap[B](json: String)(implicit m: Manifest[B]): Map[String,B] = fromJson[Map[String,B]](json)

  def toList[B](json: String)(implicit m: Manifest[B]): List[B] = fromJson[List[B]](json)

  def fromJson[B](json: String)(implicit m: Manifest[B]): B = mapper.readValue[B](json)

}
