package com.mikelalvarezgo.apolo.domain

import com.mikelalvarezgo.apolo.domain.error.Validation.Validation

trait Request {
  type Response
}
trait Query extends Request

trait Command extends Request {
  override type Response = Unit
}
trait UseCase[T[_], R <: Request] {
  def execute(r: R): Validation[T[R]]
}
