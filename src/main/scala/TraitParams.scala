/** Trait Parameters: https://dotty.epfl.ch/docs/reference/other-new-features/trait-parameters.html */
object TraitParams extends App {
  trait Base(val msg: String)

  class A extends Base("Hello")
  class B extends Base("Dotty")

  /** @param msgs varargs union type (sequence of A or B) */
  private def printMessages1(msgs: (A | B)*): Unit = println(msgs.map(_.msg).mkString(" ") + "!")


  case class C(override val msg: String) extends Base(msg)
  case class D(override val msg: String) extends Base(msg)

  private def printMessages2(msgs: (C | D)*): Unit = println(msgs.map(_.msg).mkString(" ") + "!")

  def test: Unit = {
    printMessages1(new A, new B)
    printMessages2(C("Goodbye"), D("cruel world"))
  }

  test
}
