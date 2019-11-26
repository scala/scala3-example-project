import scala.util.{Success, Try}

/** Delegates (Implied Instances):
  * - https://dotty.epfl.ch/docs/reference/contextual/delegates.html*/
object Delegates extends App {
  sealed trait StringParser[A] {
    def parse(s: String): Try[A]
  }

  object StringParser {
    def apply[A](given parser: StringParser[A]): StringParser[A] = parser

    private def baseParser[A](f: String ⇒ Try[A]): StringParser[A] =
      new StringParser[A] {
        override def parse(s: String): Try[A] = f(s)
      }

    given stringParser: StringParser[String] = baseParser(Success(_))
    given intParser: StringParser[Int] = baseParser(s ⇒ Try(s.toInt))

    given optionParser[A](given parser: => StringParser[A]): StringParser[Option[A]] =
      new StringParser[Option[A]] {
        override def parse(s: String): Try[Option[A]] = s match {
          case "" ⇒ Success(None) // implicit parser not used.
          case str ⇒ parser.parse(str).map(Some(_)) // implicit parser is evaluated here
        }
      }
  }

  def test: Unit = {
    val spoi = implicitly[StringParser[Option[Int]]]
    println(spoi.parse("21"))
    println(spoi.parse(""))
    println(spoi.parse("21a"))
    println(StringParser.optionParser[Int].parse("42"))
  }

  test
}
