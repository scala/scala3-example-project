
/**
  * Implicit Conversions: http://dotty.epfl.ch/docs/reference/changed/implicit-conversions.html
  */
object ImplicitConversion {

  case class IntWrapper(a: Int) extends AnyVal
  case class DoubleWrapper(b: Double) extends AnyVal

  def convert[T, U](x: T)(implicit converter: ImplicitConverter[T, U]): U = converter(x)

  implicit val IntWrapperToDoubleWrapper: ImplicitConverter[IntWrapper, DoubleWrapper] = new ImplicitConverter[IntWrapper, DoubleWrapper] {
    override def apply(i: IntWrapper): DoubleWrapper = new DoubleWrapper(i.a.toDouble)
  }

  def useConversion(implicit f: ImplicitConverter[IntWrapper, DoubleWrapper]) = {
    val y: IntWrapper = new IntWrapper(4)
    val x: DoubleWrapper = y
    x
  }

  /* Not working anymore.
    def useConversion(implicit f: A => B) = {
      val y: A = ...
      val x: B = a    // error under Dotty
    }
   */

  def test: Unit = {
    println(useConversion)
    println(convert(new IntWrapper(42)))
  }



}