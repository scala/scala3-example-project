@main def OpaqueTypes2 =
  object Access with
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

  def test: Unit =
    import Access._

    case class Item(rights: Permissions)

    val x = Item(ReadOnly)
    assert( ! x.rights.is(ReadWrite) )
    assert( x.rights.isOneOf(ReadOrWrite) )

  test
