import scala.util.{Success, Try}

/**
  * Implicit By-Name Parameters:
  * - http://dotty.epfl.ch/docs/reference/implicit-by-name-parameters.html
  */
object ImplicitParams {

  sealed trait StringParser[A] {
    def parse(s: String): Try[A]
  }

  object StringParser {

    def apply[A](implicit parser: StringParser[A]): StringParser[A] = parser

    private def baseParser[A](f: String ⇒ Try[A]): StringParser[A] = new StringParser[A] {
      override def parse(s: String): Try[A] = f(s)
    }

    implicit val stringParser: StringParser[String] = baseParser(Success(_))
    implicit val intParser: StringParser[Int] = baseParser(s ⇒ Try(s.toInt))

    implicit def optionParser[A](implicit parser: => StringParser[A]): StringParser[Option[A]] = new StringParser[Option[A]] {
      override def parse(s: String): Try[Option[A]] = s match {
        case "" ⇒ Success(None) // implicit parser not used.
        case str ⇒ parser.parse(str).map(x ⇒ Some(x)) // implicit parser is evaluated at here
      }
    }
  }

  def test: Unit = {
    println(implicitly[StringParser[Option[Int]]].parse("21"))
    println(implicitly[StringParser[Option[Int]]].parse(""))
    println(implicitly[StringParser[Option[Int]]].parse("21a"))

    println(implicitly[StringParser[Option[Int]]](StringParser.optionParser[Int](StringParser.intParser)).parse("42"))
  }
}
