package selenium

import org.scalatest.flatspec
import org.scalatest.matchers.should
import org.scalatestplus.selenium.HtmlUnit

trait abstractTest extends flatspec.AnyFlatSpec with should.Matchers with HtmlUnit {
  org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)
    .asInstanceOf[ch.qos.logback.classic.Logger]
    .setLevel(ch.qos.logback.classic.Level.ERROR)
}
