package com.pizzeria.common.data.entity

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import slick.collection.heterogeneous.HNil

case class LinkItemImpl(
  id:        String,
  implValue: String,
  impl:      String
) extends BaseEntity

class LinkItemImpls(tag: Tag) extends BaseTable[LinkItemImpl](tag, Some("public"), "link_value_impls") {

  def id = column[String]("id", O.PrimaryKey)
  def implValue = column[String]("impl_value")
  def impl = column[String]("impl")

  override def * : ProvenShape[LinkItemImpl] = {
    (
      id ::
      implValue ::
      impl ::
      HNil
    ).mapTo[LinkItemImpl]
  }

}