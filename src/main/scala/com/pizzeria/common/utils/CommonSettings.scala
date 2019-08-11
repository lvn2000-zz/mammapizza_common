package com.pizzeria.common.utils

object CommonSettings {

  object typePropertyData extends Enumeration {
    type typePropertyData = Value

    val varcharProp = Value("avalue") //String
    val textProp = Value("cvalue") //String
    val numberProp = Value("nvalue") //BigDecimal
    val intProp = Value("ivalue") //Int
    val timeProp = Value("tvalue") //Timestamp
    val blobProp = Value("bvalue") //Array[Byte]
  }

  object typeGroupData extends Enumeration {
    type typeGroupData = Value

    val itemGroup = Value("itemGroup")
    val propertyGroup = Value("propertyGroup")
  }

  val dateTimeFormat = "yyyy-MM-dd HH:mm:ss" //all date values are used as Timestamp in application

}