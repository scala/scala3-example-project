
/**
  * Automatic Tupling of Function Params: http://dotty.epfl.ch/docs/reference/auto-parameter-tupling.html
  */
object AutoParamTupling {

  def test: Unit = {

    /**
      * In order to get thread safety, you need to put @volatile before lazy vals.
      * http://dotty.epfl.ch/docs/reference/changed/lazy-vals.html
      */
    @volatile lazy val xs: List[String] = List("d", "o", "t", "t", "y")

    /**
      * Current behaviour in Scala 2.12.2 :
      * error: missing parameter type
      * Note: The expected type requires a one-argument function accepting a 2-Tuple.
      * Consider a pattern matching anonymous function, `{ case (s, i) =>  ... }`
      */
    xs.zipWithIndex.map((s, i) => println(s"$i: $s"))

  }
}