package com.mikelalvarezgo.apolo.domain.error


import cats.data.{NonEmptyList, Validated, ValidatedNel}
import cats.implicits.catsSyntaxTry
import com.mikelalvarezgo.apolo.domain.error.Validation.Validation
final case class ValidationErrorException(
  validationErrors: NonEmptyList[ValidationError],
  id: Option[String]
) extends Exception {
  override def getMessage: String =
    (List(message) ++ fields.map { case (k, v) => s"$k=$v" }).mkString(", ")
  def message: String = "Validation went wrong"
  def fields: Map[String, String] =
    List(Some("errors" -> validationErrors.toList.mkString(",")), id.map("id" -> _)).flatten.toMap
}

object ValidationErrorException {
  def getOrThrow[T](validation: Validation[T]): T =
    validation.valueOr(
      validationErrors => throw ValidationErrorException(validationErrors, id = None)
    )
  def getOrThrowWithId[T](id: String, validation: Validation[T]): T =
    validation.valueOr(
      validationErrors => throw ValidationErrorException(validationErrors, id = Some(id))
    )
}
