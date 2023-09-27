package com.mikelalvarezgo.apolo.domain.contract

trait Logger {
  def info(msg: String, params: Map[String, Any]): Unit
  def info(msg: String, throwable: Throwable): Unit
  def info(msg: String): Unit
  def warning(msg: String, params: Map[String, Any]): Unit
  def warning(msg: String, throwable: Throwable): Unit
  def warning(msg: String): Unit
  def error(msg: String, params: Map[String, Any]): Unit
  def error(msg: String, throwable: Throwable): Unit
  def error(msg: String): Unit
  def debug(msg: String, throwable: Throwable): Unit
}
