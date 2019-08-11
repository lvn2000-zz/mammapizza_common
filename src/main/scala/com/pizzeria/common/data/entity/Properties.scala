package com.pizzeria.common.data.entity

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import slick.collection.heterogeneous.HNil

case class Property(
  id:           String,
  title:        String,
  parent:       Option[String] = None,
  group:        String,
  typeValue:    String,
  defaultValue: Option[String] = None,
  isMandatory:  Boolean        = false
) extends BaseEntity

class Properties(tag: Tag) extends BaseTable[Property](tag, Some("public"), "properties") {

  def id = column[String]("id", O.PrimaryKey)
  def title = column[String]("title")
  def parent = column[Option[String]]("parent")
  def group = column[String]("group")
  def typeValue = column[String]("type_value")
  def defaultValue = column[Option[String]]("default_value")
  def isMandatory = column[Boolean]("mandatory")

  override def * : ProvenShape[Property] = {
    (
      id ::
      title ::
      parent ::
      group ::
      typeValue ::
      defaultValue ::
      isMandatory ::
      HNil
    ).mapTo[Property]
  }

}