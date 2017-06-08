import dotty.DottyPredef.{eqAny => _, _}

/**
  * Multiversal Equality: http://dotty.epfl.ch/docs/reference/multiversal-equality.html
  * Dotty Predef: https://github.com/lampepfl/dotty/blob/master/library/src/dotty/DottyPredef.scala
  */
object MultiversalEquality {

  def test: Unit = {

    // Cannot compile: Values of types Int and String cannot be compared with == or !=
    implicit def eqIntString: Eq[Int, String] = Eq
    println(3 == "3")

    // As default all numbers are comparable, because of;
    // implicit def eqNumber : Eq[Number, Number] = Eq
    println(3 == 5.1)

    // As default all Sequences are comparable, because of;
    // implicit def eqSeq[T, U](implicit eq: Eq[T, U]): Eq[Seq[T], Seq[U]] = Eq
    println(List(1, 2) == Vector(1, 2))

    class A(a: Int)
    class B(b: Int)

    val a = new A(4)
    val b = new B(4)

    /**
      * Error: Values of types A and B cannot be compared with == or !=
      * We need to put implicit equality (for A to B) in the context if we want to compare A and B,
      * since we exclude eqAny from DottyPredef
      */
    implicit def eqAB: Eq[A, B] = Eq
    println(a != b)

    implicit def eqBA: Eq[B, A] = Eq
    println(b == a)

  }


}