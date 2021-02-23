package selenium

import org.scalatest.flatspec
import org.scalatest.matchers.should
import org.scalatestplus.selenium.HtmlUnit

trait abstractTest extends flatspec.AnyFlatSpec with should.Matchers with HtmlUnit {

}
