/** Union types (aka sum types): https://dotty.epfl.ch/docs/reference/new-types/union-types.html */
object UnionTypes extends App {
  sealed trait Division
  final case class DivisionByZero(msg: String) extends Division
  final case class Success(double: Double) extends Division

  // Type aliases can use union types
  type DivisionResult = DivisionByZero | Success

  sealed trait List[+A]
  final case class Cons[+A](h: A, t: List[A]) extends List[A]
  final class Empty extends List[Nothing] {
    override def toString: String = "Empty"
  }

  private def safeDivide(a: Double, b: Double): DivisionResult =
    if (b == 0) DivisionByZero("DivisionByZeroException") else Success(a / b)

  private def either(division: Division) = division match {
    case DivisionByZero(m) => Left(m)
    case Success(d) => Right(d)
  }

  def test: Unit = {
    val divisionResultSuccess: DivisionResult = safeDivide(4, 2)

    // Commutative
    val divisionResultFailure: Success | DivisionByZero = safeDivide(4, 0)

    // Calling `either` function with union typed value.
    println("either(divisionResultSuccess): " + either(divisionResultSuccess))

    // Calling `either` function with union typed value.
    println("either(divisionResultFailure): " + either(divisionResultFailure))

    val list: Cons[Int] | Empty = Cons(1, Cons(2, Cons(3, Empty())))
    println("list: " + list)

    val emptyList: Empty | Cons[Any] = Empty()
    println("emptyList: " + emptyList)
  }

  test
}
