/** Opaque types aliases provide type abstraction without *any* runtime overhead.
  * They replace Scala 2 value types.
  * See https://dotty.epfl.ch/docs/reference/other-new-features/opaques.html */
object OpaqueTypes1 extends App {
  object Logarithms {
    opaque type Logarithm = Double

    object Logarithm {
      // These are the ways to lift to the logarithm type
      def apply(d: Double): Logarithm = math.log(d)

      def safe(d: Double): Option[Logarithm] =
        if (d > 0.0) Some(math.log(d)) else None
    }

    // Extension methods define opaque types' public APIs
    given logarithmOps: {
      def (x: Logarithm) toDouble: Double = math.exp(x)
      def (x: Logarithm) + (y: Logarithm): Logarithm = Logarithm(math.exp(x) + math.exp(y))
      def (x: Logarithm) * (y: Logarithm): Logarithm = Logarithm(x + y)
    }
  }

  def test: Unit = {
    import Logarithms._

    println("Logarithm(1.0) * Logarithm(2.0): " + (Logarithm(1.0) * Logarithm(2.0)))
    println("Logarithm(1.0) + Logarithm(2.0): " + (Logarithm(1.0) + Logarithm(2.0)))
  }

  test
}
