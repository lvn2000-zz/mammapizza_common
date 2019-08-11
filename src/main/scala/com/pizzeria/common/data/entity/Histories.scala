package com.pizzeria.common.data.entity

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import slick.collection.heterogeneous.HNil
import java.sql.Timestamp

case class History(
  id:             String,
  idItemProperty: String,
  idItemImpl:     String,
  title:          Option[String]      = None,
  parent:         Option[String]      = None,
  aValue:         Option[String]      = None,
  cValue:         Option[String]      = None,
  bValue:         Option[Array[Byte]] = None,
  implValue:      Option[String]      = None,
  dateChange:     Timestamp
) extends BaseEntity

class Histories(tag: Tag) extends BaseTable[History](tag, Some("public"), "histories") {

  def id = column[String]("id", O.PrimaryKey)
  def idItemProperty = column[String]("id_item_property")
  def idItemImpl = column[String]("id_item_impl")
  def title = column[Option[String]]("title")
  def parent = column[Option[String]]("parent")
  def aValue = column[Option[String]]("avalue")
  def cValue = column[Option[String]]("cvalue")
  def bValue = column[Option[Array[Byte]]]("bvalue")
  def implValue = column[Option[String]]("impl_value")
  def dateChange = column[Timestamp]("datechange")

  override def * : ProvenShape[History] = {
    (
      id ::
      idItemProperty ::
      idItemImpl ::
      title ::
      parent ::
      aValue ::
      cValue ::
      bValue ::
      implValue ::
      dateChange ::
      HNil
    ).mapTo[History]
  }

}