package com.pizzeria.common.data.entity

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import slick.collection.heterogeneous.HNil

case class ItemImpl(
  id:     String,
  idItem: String,
  title:  String,
  parent: Option[String] = None,
  group:  Option[String] = None
) extends BaseEntity

class ItemImpls(tag: Tag) extends BaseTable[ItemImpl](tag, Some("public"), "item_impls") {

  def id = column[String]("id", O.PrimaryKey)
  def idItem = column[String]("id_item")
  def title = column[String]("title")
  def parent = column[Option[String]]("parent")
  def group = column[Option[String]]("group")

  override def * : ProvenShape[ItemImpl] = {
    (
      id ::
      idItem ::
      title ::
      parent ::
      group ::
      HNil
    ).mapTo[ItemImpl]
  }

}