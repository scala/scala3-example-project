package com.mikelalvarezgo.apolo.domain.error

trait DomainError extends Throwable {
  def message: String

  override def getMessage: String = message
}
