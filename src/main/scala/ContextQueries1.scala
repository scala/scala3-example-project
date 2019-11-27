
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

/** Context Queries (Formerly known as Implicit Function Types):
  * - Implicit functions are functions that only have implicit parameters.
  * - Their types are implicit function types.
  * - http://dotty.epfl.ch/docs/reference/contextual/implicit-function-types.html,
  * - Old syntax: https://www.scala-lang.org/blog/2016/12/07/implicit-function-types.html */
@main def ContextQueries1 =
  object context with
    // type alias Contextual
    type Contextual[T] = (given ExecutionContext) => T

    // sum is expanded to sum(x, y)(ctx)
    def asyncSum(x: Int, y: Int): Contextual[Future[Int]] = Future(x + y)

    def asyncMult(x: Int, y: Int)
                 (given ctx: ExecutionContext): Contextual[Future[Int]] = Future(x * y)

  object parse with
    type Parseable[T] = (given Delegates.StringParser[T]) => Try[T]

    def sumStrings(x: String, y: String): Parseable[Int] = {
      val parser = implicitly[Delegates.StringParser[Int]]
      val tryA = parser.parse(x)
      val tryB = parser.parse(y)

      for {
        a <- tryA
        b <- tryB
      } yield a + b
    }

  def test: Unit =
    import ExecutionContext.Implicits.global

    context.asyncSum(3, 4).foreach(x => println("asyncSum: " + x))
    context.asyncMult(3, 4).foreach(x => println("asyncMult: " + x))

    println("""parse.sumStrings("3", "4"): """ + parse.sumStrings("3", "4"))
    println("""parse.sumStrings("3", "a"): """ + parse.sumStrings("3", "a"))

  test
