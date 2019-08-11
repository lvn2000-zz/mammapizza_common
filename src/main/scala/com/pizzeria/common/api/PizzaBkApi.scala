package com.pizzeria.common.api

import java.sql.Timestamp

import com.pizzeria.common.data.entity.{Item, ItemImpl, Property}
import com.pizzeria.common.utils.CommonSettings

trait PizzaMessage

trait PizzaRequestMessage extends PizzaMessage

trait PizzaResponseMessage extends PizzaMessage

case class ItemWithProperties(item: Item, properties: Vector[Property]) extends PizzaMessage
case class PropValue(property: Property, value: Any, valueType: CommonSettings.typePropertyData.Value) extends PizzaMessage
case class ImplItemWithProperties(itemImpl: ItemImpl, properties: Vector[PropValue]) extends PizzaMessage

case class ItemRequest(keyItems: Vector[String]) extends PizzaRequestMessage
case class ItemResponse(items: Vector[Item]) extends PizzaResponseMessage

case class CheckMessages() extends PizzaMessage
case class StartWork() extends PizzaMessage
case class StopWork() extends PizzaMessage

case class Pizza(id: String, title: String) extends PizzaMessage
case class PizzaType(idPizza: String, idType: String) extends PizzaMessage
case class Ingredient(id: String, title: String) extends PizzaMessage
case class PizzaPrice(
  idPizza:     String,
  priceSizeL:  BigDecimal,
  priceSizeM:  BigDecimal,
  priceSizeXL: BigDecimal,
  dateChange:  Timestamp
) extends PizzaMessage
//case class PizzaIngredient(idPizza: String, idIngredient: String) extends PizzaMessage
case class TypePizza(id: String, title: String) extends PizzaMessage

case class FilterRecords(filter: Map[String, String]) extends PizzaMessage

case class PizzaFull(
  pizza:       Pizza,
  typePizza:   Option[PizzaType]          = None,
  ingredients: Option[Vector[Ingredient]] = None,
  pricePizza:  Option[PizzaPrice]         = None
) extends PizzaMessage

case class PizzaListRequest(filters: Option[Vector[FilterRecords]]) extends PizzaRequestMessage
case class PizzaListResponse(pizzas: Vector[PizzaFull]) extends PizzaResponseMessage

