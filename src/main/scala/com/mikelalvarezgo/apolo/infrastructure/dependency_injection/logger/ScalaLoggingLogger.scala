package com.mikelalvarezgo.apolo.infrastructure.dependency_injection.logger

import com.mikelalvarezgo.apolo.domain.contract.Logger
import com.typesafe.scalalogging
import org.slf4j.LoggerFactory


final case class ScalaLoggingLogger(name: String) extends Logger {
  private val logger: scalalogging.Logger = scalalogging.Logger(name)

  override def info(msg: String, params: Map[String, Any]): Unit =
    logger.info(toMessage(msg, params))
  override def info(msg: String, cause: Throwable): Unit = logger.info(msg, cause)
  override def info(msg: String): Unit                   = logger.info(msg)
  def warning(msg: String, params: Map[String, Any]): Unit =
    logger.warn(toMessage(msg, params))
  def warning(msg: String, cause: Throwable): Unit = logger.warn(msg, cause)
  def warning(msg: String): Unit                   = logger.warn(msg)
  def error(msg: String, params: Map[String, Any]): Unit =
    logger.error(toMessage(msg, params))
  override def error(msg: String, cause: Throwable): Unit = logger.error(msg, cause)
  def error(msg: String): Unit                   = logger.error(msg)
  def debug(msg: String, cause: Throwable): Unit = logger.debug(msg, cause)

  private def toMessage(msg: String, params: Map[String, Any]): String =
    msg + ": " + params.map { case (k, v) => s"$k=$v" }.mkString(", ")
}

