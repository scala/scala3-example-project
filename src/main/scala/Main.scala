object Main {
  def main(args: Array[String]): Unit = {
    runExample("Typeclasses")(Typeclasses.test)
    runExample("Trait Params")(TraitParams.test)
    runExample("Enum Types")(EnumTypes.test)
    runExample("Context Queries 1")(ContextQueries1.test)
    runExample("Context Queries 2")(ContextQueries2.test)
    runExample("Implied Instances")(ImpliedInstances.test)
    runExample("Conversion")(Conversion.test)
    runExample("OpaqueTypes 1")(OpaqueTypes1.test)
    runExample("OpaqueTypes 2")(OpaqueTypes2.test)
    runExample("Union Types")(UnionTypes.test)
    runExample("Intersection Types")(IntersectionTypes.test)
    runExample("Type Lambda")(TypeLambdas.test)
    runExample("Multiversal Equality")(MultiversalEquality.test)
    runExample("Named Type Arguments")(NamedTypeArguments.test)
    runExample("Auto Param Tupling")(AutoParamTupling.test)
    runExample("Structural Types")(StructuralTypes.test)
    runExample("Pattern Matching")(PatternMatching.test)
  }

  private def runExample(name: String)
                        (f: => Unit) = {
    println(Console.MAGENTA + s"$name example:" + Console.RESET)
    f
    println()
  }
}
