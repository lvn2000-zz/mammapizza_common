package com.pizzeria.common.data.entity

import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._


abstract class BaseTable[T](tag: Tag, schemaName: Option[String], name: String) extends Table[T](tag, schemaName, name)