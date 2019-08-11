package com.pizzeria.common.data.entity

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import slick.collection.heterogeneous.HNil

case class Group(
  id:     String,
  title:  String,
  parent: Option[String] = None,
  cType:  String
) extends BaseEntity

class Groups(tag: Tag) extends BaseTable[Group](tag, Some("public"), "groups") {

  def id = column[String]("id", O.PrimaryKey)
  def title = column[String]("title")
  def parent = column[Option[String]]("parent")
  def cType = column[String]("ctype")

  override def * : ProvenShape[Group] = {
    (
      id ::
      title ::
      parent ::
      cType ::
      HNil
    ).mapTo[Group]
  }

}