trait Base {
  def message: String
}
class A extends Base {
  def message: String = "Hello"
}
class B extends Base {
  def message: String = "Dotty!"
}

object Hello {
  // Union types only exist in Dotty, so there's no chance that this will
  // accidentally be compiled with Scala 2
  def printMessages(msgs: (A | B)*) = println(msgs.map(_.message).mkString(" "))
  def main(args: Array[String]): Unit = {
    printMessages(new A, new B)

    // Sanity check the classpath: this won't run if the dotty jar is not present.
    val x: Int => Int = z => z
    x(1)
  }
}
