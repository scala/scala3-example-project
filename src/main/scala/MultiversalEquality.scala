/**
  * Multiversal Equality: http://dotty.epfl.ch/docs/reference/multiversal-equality.html
  * scala.Eq definition: https://github.com/lampepfl/dotty/blob/master/library/src/scala/Eq.scala
  */
object MultiversalEquality {

  def test: Unit = {

    // Cannot compile: Values of types Int and String cannot be compared with == or !=
    implicit def eqIntString: Eq[Int, String] = Eq
    println(3 == "3")

    // By default, all numbers are comparable, because of;
    // implicit def eqNumber : Eq[Number, Number] = Eq
    println(3 == 5.1)

    // By default, all Sequences are comparable, because of;
    // implicit def eqSeq[T, U](implicit eq: Eq[T, U]): Eq[Seq[T], Seq[U]] = Eq
    println(List(1, 2) == Vector(1, 2))
  }
}
