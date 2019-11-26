/** Opaque types aliases provide type abstraction without *any* runtime overhead.
See https://dotty.epfl.ch/docs/reference/other-new-features/opaques.html */
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

object OpaqueTypes2 extends App {
  object Access {
    opaque type Permissions = Int
    opaque type PermissionChoice = Int
    /** `Permission`'s upper bound is `Permissions & PermissionChoice`.
      * Thus `Permission` is universally known to be a subtype of `Permissions` and `PermissionChoice`. */
    opaque type Permission <: Permissions & PermissionChoice = Int

    def (x: Permissions) & (y: Permissions): Permissions = x & y
    def (x: PermissionChoice) | (y: PermissionChoice): PermissionChoice = x | y
    def (x: Permissions) is (y: Permissions) = (x & y) == y
    def (x: Permissions) isOneOf (y: PermissionChoice) = (x & y) != 0

    val NoPermission: Permission = 0
    val ReadOnly: Permission = 1
    val WriteOnly: Permission = 2
    val ReadWrite: Permissions = ReadOnly | WriteOnly
    val ReadOrWrite: PermissionChoice = ReadOnly | WriteOnly
  }

  def test: Unit = {
    import Access._

    case class Item(rights: Permissions)

    val x = Item(ReadOnly)
    assert( ! x.rights.is(ReadWrite) )
    assert( x.rights.isOneOf(ReadOrWrite) )
  }

  test
}
