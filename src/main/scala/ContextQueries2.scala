/** This example combines opaque aliases, implicit function types, and extension methods to provide
  * a zero-overhead abstraction at runtime.
  * See http://dotty.epfl.ch/docs/reference/contextual/implicit-function-types.html#example-postconditions */
object PostConditions
  opaque type WrappedResult[T] = T

  def result[T](given r: WrappedResult[T]): T = r

  /** This extension method accepts an implicit function type called `condition`,
    * which receives the given instance of type `WrappedResult[T]``.
    * The given `WrappedResult` instance is passed to the result method.
    * `WrappedResult` is an opaque type alias, so its values need not be boxed or unboxed.
    * Because `ensuring` is added as an extension method, its argument also does not need boxing. */
  def [T](x: T) ensuring(condition: (given WrappedResult[T]) => Boolean): T =
    assert(condition(given x))
    x

@main def ContextQueries2 =
  import PostConditions.{ensuring, result}

  def test: Unit =
    val s = List(1, 2, 3).sum.ensuring(result == 6)
    println("Yes, the list sums to 6.")

  test
