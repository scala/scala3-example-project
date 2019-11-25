object ExtensionClasses extends App {
  case class Circle(x: Double, y: Double, radius: Double)

  // New Scala 3 way:
  def (c: Circle) circumference: Double = c.radius * math.Pi * 2

  val value = Circle(0, 0, 0.5).circumference
  println(s"circumference = $value")

  // Scala 2 way
  //implicit class CircleOps(circle: Circle) extends AnyVal {
  //  def circumference = circle.radius * Pi * 2
  //}
}
