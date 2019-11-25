/**
  * Intersection Types: https://dotty.epfl.ch/docs/reference/new-types/intersection-types.html
  */
object IntersectionTypes extends App {
  sealed trait X {
    def x: Double
    def tpe: X
  }

  sealed trait Y {
    def y: Double
    def tpe: Y
  }

  // The compiler treats P and PP as equivalent and interchangeable types
  type P = Y & X
  type PP = X & Y

  final case class Point(x: Double, y: Double) extends X with Y {
    override def tpe: X & Y = ???
  }

  def test: Unit = {
    def euclideanDistance(p1: P, p2: P) = {
      Math.sqrt(Math.pow(p2.y - p1.y, 2) + Math.pow(p2.x - p1.x, 2))
    }

    val p1: P = Point(3, 4)
    val p2: PP = Point(6, 8)
    println(euclideanDistance(p1, p2))
  }
}
