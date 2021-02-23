package selenium
import org.scalatest._
import org.scalatest.matchers._
import org.scalatestplus.selenium._

class homePage extends flatspec.AnyFlatSpec with should.Matchers with HtmlUnit {

  val host = "http://localhost:9000/"

  //Test1
  "The Cinema Home Page" should "have the correct title" in {
    go to (host)
    pageTitle should be ("QA Cinemas : Home")
  }

  //Test2
  "The Cinema Home Page buttons" should "alternate between new movies and upcoming movies" in {
    go to (host)
    click on xpath("//label[contains(.,'New Movies')]")
    assert(webDriver.findElementById("notReleased").isDisplayed)
    click on xpath("//label[contains(.,'Current Movies')]")
    assert(webDriver.findElementById("released").isDisplayed)
  }
}
