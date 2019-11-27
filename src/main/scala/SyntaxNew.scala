/** See https://dotty.epfl.ch/blog/2019/08/30/18th-dotty-milestone-release.html */

@main def SyntaxNew =
  println("Hello from SyntaxNew")

  def test: Unit =
    val xs = 0 to 3
    val xsFiltered = for x <- xs if x > 1 yield x
    for
      x <- xsFiltered
      y <- xsFiltered
    do println(s"$x * $y = ${x * y}")

  test

/** To run, type `runMain testIf Monday` */
@main def testIf(day: String) =
  if day == "Sunday" || day == "Saturday"
  then println("Today is a weekend, hooray!")
  else println(s"Today is a workday.")

  /** To run, type `runMain testWhile 3` */
@main def testWhile(bound: Int) =
  var x = 0

  def incrementX() =
    x += 1
    println(x)

  while x <= bound do incrementX()
