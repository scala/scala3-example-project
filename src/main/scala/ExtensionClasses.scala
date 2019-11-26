/** See https://dotty.epfl.ch/docs/reference/contextual/extension-methods-new.html */
object ExtensionClasses1 extends App {
  case class Circle(x: Double, y: Double, radius: Double)

  // New Scala 3 way, using an extension method:
  def (c: Circle) circumference: Double = c.radius * math.Pi * 2

  val circle = Circle(0, 0, 0.5)
  println(s"circumference = ${ circle.circumference }")

  assert(circle.circumference == circumference(circle))

  // Old Scala 2 way, using an implicit class:
  //implicit class CircleOps(circle: Circle) extends AnyVal {
  //  def circumference = circle.radius * Pi * 2
  //}
}

object ExtensionClasses2 extends App {
  trait StringSeqOps {
    def (xs: Seq[String]) longestStrings = {
      val maxLength = xs.map(_.length).max
      xs.filter(_.length == maxLength)
    }
  }

  given ops1: StringSeqOps

  println(s"""List("here", "is", "a", "list").longestStrings: """ + List("here", "is", "a", "list").longestStrings)
}
