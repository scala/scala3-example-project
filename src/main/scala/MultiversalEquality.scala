import scala.language.strictEquality

/**
  * Multiversal Equality: https://dotty.epfl.ch/docs/reference/contextual/multiversal-equality.html
  * scala.Eq definition: https://github.com/lampepfl/dotty/blob/master/library/src/scala/Eql.scala
  */
  object MultiversalEquality extends App {

  def test: Unit = {
    // Values of types Int and String cannot be compared with == or !=,
    // unless we add the derived delegate instance like:
    given Eql[Int, String] = Eql.derived
    println("3 == \"3\": " + (3 == "3"))

    // By default, all numbers are comparable, because of;
    // implicit def eqlNumber: Eql[Number, Number] = derived
    println("3 == 5.1: " + (3 == 5.1))

    // By default, all Sequences are comparable (except Array), because of;
    // implicit def eqlSeq[T, U](implicit eq: Eql[T, U]): Eql[GenSeq[T], GenSeq[U]] = derived
    // This fails, why?
    println("List(1, 2) == Vector(1, 2)): " + (List(1, 2) == Vector(1, 2)))

    class A(a: Int)
    class B(b: Int)

    val a = new A(4)
    val b = new B(4)

    // Two arrays in Scala do not compare equal so this is illegal code that must not compile?
    //println("Array(1, 2) == Array(1, 2): " + (Array(1, 2) == Array(1, 2)))

    // scala.language.strictEquality is enabled, therefore we need some extra delegate instances
    // to compare instances of A and B.
    given Eql[A, B] = Eql.derived
    given Eql[B, A] = Eql.derived

    println("a != b: " + (a != b))
    println("b == a: " + (b == a))
  }

  test
}
