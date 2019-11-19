object Typeclasses extends App {
  trait SemiGroup[T] {
    def (x: T) combine (y: T): T
  }

  trait Monoid [T] extends SemiGroup[T] {
    def unit: T
  }

  given Monoid[String] {
    def (x: String) combine (y: String) = x.concat(y)
    def unit = ""
  }

  def sum[T: Monoid](xs: List[T]): T =
    xs.foldLeft(summon[Monoid[T]].unit)(_.combine(_))

  def test: Unit = {
    println("""sum("a", "b", "c"): """ + sum(List("a", "b", "c")))
  }
}
