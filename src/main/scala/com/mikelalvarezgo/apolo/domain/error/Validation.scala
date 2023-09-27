package com.mikelalvarezgo.apolo.domain.error

import scala.concurrent.Future
import scala.util.Try

import cats.data.{NonEmptyList, Validated, ValidatedNel}
import cats.implicits.catsSyntaxTry

object Validation {
  type Validation[ValidType] = ValidatedNel[ValidationError, ValidType]
  type ValidationFuture      = Validation[Future[_]]
  type ValidationFutureT[T]  = Validation[Future[T]]

  def fromTry[A](ve: ValidationError, t: Try[A]): Validation[A] =
    t.toValidated.leftMap(_ => NonEmptyList.one(ve))

  implicit final class ValidationValidIdSyntax[A](val a: A) extends AnyVal {
    def validValidation[B]: Validation[A] = Validated.Valid(a)
  }
  implicit final class ValidationInvalidIdSyntax(val e: ValidationError) extends AnyVal {
    def invalidValidation[A]: Validation[A] = Validated.invalidNel(e)
  }
}
