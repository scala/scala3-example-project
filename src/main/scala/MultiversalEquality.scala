import scala.language.strictEquality

/**
  * Multiversal Equality: https://dotty.epfl.ch/docs/reference/contextual/multiversal-equality.html
  * scala.Eq definition: https://github.com/lampepfl/dotty/blob/master/library/src/scala/Eql.scala
  */
object MultiversalEquality {

  def test: Unit = {

    // Values of types Int and String cannot be compared with == or !=,
    // unless we add a custom implicit like:
    implicit def eqIntString: Eql[Int, String] = Eql.derived
    println(3 == "3")

    // By default, all numbers are comparable, because of;
    // implicit def eqNumber : Eq[Number, Number] = Eq
    println(3 == 5.1)

    // By default, all Sequences are comparable, because of;
    // implicit def eqSeq[T, U](implicit eq: Eq[T, U]): Eq[Seq[T], Seq[U]] = Eq
    println(List(1, 2) == Vector(1, 2))

    class A(a: Int)
    class B(b: Int)

    val a = new A(4)
    val b = new B(4)

    // scala.language.strictEquality is enabled, therefore we need some extra implicits
    // to compare instances of A and B.
    implicit def eqAB: Eql[A, B] = Eql.derived
    implicit def eqBA: Eql[B, A] = Eql.derived

    println(a != b)
    println(b == a)
  }
}
