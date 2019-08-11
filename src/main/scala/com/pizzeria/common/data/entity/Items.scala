package com.pizzeria.common.data.entity

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import slick.collection.heterogeneous.HNil

case class Item(
  id:     String,
  title:  String,
  parent: Option[String] = None,
  group:  String
) extends BaseEntity

class Items(tag: Tag) extends BaseTable[Item](tag, Some("public"), "items") {

  def id = column[String]("id", O.PrimaryKey)
  def title = column[String]("title")
  def parent = column[Option[String]]("parent")
  def group = column[String]("group")

  override def * : ProvenShape[Item] = {
    (
      id ::
      title ::
      parent ::
      group ::
      HNil
    ).mapTo[Item]
  }

}