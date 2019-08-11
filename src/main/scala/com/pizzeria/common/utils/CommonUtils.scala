package com.pizzeria.common.utils

import java.sql.Timestamp
import java.time.Instant

import scala.collection.{Iterable, Map, mutable}
import scala.concurrent.ExecutionContext
//import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Random, Success, Try}

import org.nustaq.serialization.FSTConfiguration
import org.slf4j.LoggerFactory

import com.pizzeria.common.api.PizzaMessage

class CommonUtils {

  private val logger = LoggerFactory.getLogger(this.getClass)

  val conf = FSTConfiguration.createUnsafeBinaryConfiguration()
  conf.setForceSerializable(true)
  conf.setShareReferences(false)

  def generateUUID(lengthUUID: Int = 20): String = {
    (1 to lengthUUID).foldLeft("")((a, _) ⇒ Random.nextInt(9).toString.concat(a))
  }

  def serialiseFutures[A](listFutures: Iterable[Future[A]])(implicit ec: ExecutionContext): Future[Vector[A]] = {
    listFutures.foldLeft(Future(Vector.empty[A])) {
      (previousFuture: Future[Vector[A]], next: Future[A]) ⇒
        for {
          previousResults ← previousFuture
          next ← next
        } yield previousResults :+ next
    }
  }

  def serialiseFutures[A, B](listFutures: Map[Future[A], B])(implicit ec: ExecutionContext): Future[Map[A, B]] = {
    listFutures.foldLeft(Future(collection.mutable.Map.empty[A, B])) {
      (previousFuture: Future[mutable.Map[A, B]], next: (Future[A], B)) ⇒
        for {
          previousResults ← previousFuture
          next: (A, B) ← next._1.map(v ⇒ (v, next._2))
        } yield previousResults += next
    }.map(m ⇒ collection.immutable.Map(m.toList: _*))
  }

  //date time

  def convertTimestampTostring(dt: Timestamp): String = {
    Instant.ofEpochMilli(dt.getTime).toString
  }

  def convertStringToTimestamp(cTmstp: String): Timestamp = {
    timeToTimestamp(Instant.parse(cTmstp))
  }

  def timeStampToDate(tmstp: Timestamp): Instant = {
    Instant.ofEpochMilli(tmstp.getTime)
  }

  def timeToTimestamp(d: Instant): Timestamp = {
    new Timestamp(d.toEpochMilli())
  }

  def deserVal(value: Array[Byte]): Option[PizzaMessage] = {

    Try {
      val deserObj = conf.asObject(value)
      deserObj.asInstanceOf[PizzaMessage]
    } match {
      case Success(v) ⇒ Some(v)
      case Failure(ex) ⇒
        val logMsg = s"Error in getting value ${ex.getMessage()}"
        logger.error(logMsg)
        None
    }

  }

  def serlzVal(pzMessage: PizzaMessage): Option[Array[Byte]] = {

    Try { conf.asByteArray(pzMessage) } match {
      case Success(v) ⇒ Some(v)
      case Failure(ex) ⇒
        val logMsg = s"Error in serialization of ${PrettyPrint.prettyPrint(pzMessage)}"
        logger.error(logMsg)
        None
    }
  }

}

object CommonUtils {
  def apply() = new CommonUtils()
}
