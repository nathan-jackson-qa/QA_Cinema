package selenium

import org.scalatest._
import org.scalatest.matchers._
import org.scalatestplus.selenium._

trait abstractTest extends flatspec.AnyFlatSpec with should.Matchers with HtmlUnit {
  org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)
    .asInstanceOf[ch.qos.logback.classic.Logger]
    .setLevel(ch.qos.logback.classic.Level.ERROR)
}
