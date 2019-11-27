/** Extension methods provide a nice syntax for typeclasses
  * * Removes need for dangerous implicit conversions
  * * Emphasize intent over mechanism
  * * Better error messages than generated from implicits
  * * Implicit conversions will go away
  * See https://docs.microsoft.com/en-us/dotnet/csharp/programming-guide/delegates */
@main def Typeclasses =
  trait SemiGroup[T] with
    def (x: T) combine (y: T): T

  trait Monoid [T] extends SemiGroup[T] with
    def unit: T

  given Monoid[String] with
    def (x: String) combine (y: String) = x.concat(y)
    def unit = ""

  def sum[T: Monoid](xs: List[T]): T =
    xs.foldLeft(summon[Monoid[T]].unit)(_.combine(_))

  def test: Unit = println("""sum("a", "b", "c"): """ + sum(List("a", "b", "c")))

  test
