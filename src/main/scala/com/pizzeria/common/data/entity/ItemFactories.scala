package com.pizzeria.common.data.entity

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import slick.collection.heterogeneous.HNil

case class ItemFactory(
  id:           String,
  idItem:       String,
  idProperty:   String,
  title:        String,
  parent:       Option[String] = None
) extends BaseEntity

class ItemFactories(tag: Tag) extends BaseTable[ItemFactory](tag, Some("public"), "item_factories") {

  def id = column[String]("id", O.PrimaryKey)
  def idItem = column[String]("id_item")
  def idProperty = column[String]("id_property")
  def title = column[String]("title")
  def parent = column[Option[String]]("parent")

  override def * : ProvenShape[ItemFactory] = {
    (
      id ::
      idItem ::
      idProperty ::
      title ::
      parent ::
      HNil
    ).mapTo[ItemFactory]
  }

}