package com.mikelalvarezgo.apolo

import com.mikelalvarezgo.apolo.domain.contract.Logger
import com.mikelalvarezgo.apolo.infrastructure.dependency_injection.logger.ScalaLoggingLogger

object ApoloStarter extends App {

  given logger: Logger = ScalaLoggingLogger("apolo")

  logger.info("Apolo App Successfully Started!!")

}
