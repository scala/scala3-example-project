package com.mikelalvarezgo.apolo.infrastructure.dependency_injection

import com.mikelalvarezgo.apolo.domain.Request
import com.mikelalvarezgo.apolo.domain.error.Validation.ValidationFutureT

trait Dispatcher {
  private type ==>[A, B] = PartialFunction[A, B]

  def dispatch: Request ==> ValidationFutureT[Unit]
}
