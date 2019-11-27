import scala.language.implicitConversions

case class IntWrapper(a: Int) extends AnyVal
case class DoubleWrapper(b: Double) extends AnyVal

/** Conversions: http://dotty.epfl.ch/docs/reference/contextual/conversions.html */
@main def Conversion =
  def convert[T, U](t: T)
                   (given converter: Conversion[T, U]): U = converter(t)

  given IntWrapperToDoubleWrapper: Conversion[IntWrapper, DoubleWrapper] =
    new Conversion[IntWrapper, DoubleWrapper] with
      override def apply(i: IntWrapper): DoubleWrapper = new DoubleWrapper(i.a.toDouble)
    
  def useConversion(given f: Conversion[IntWrapper, DoubleWrapper]) =
    val y: IntWrapper = new IntWrapper(4)
    val x: DoubleWrapper = y
    x

  def test: Unit =
    println(useConversion)
    println(convert(new IntWrapper(42)))

  test
