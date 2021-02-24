package selenium


class HomePageTest extends abstractTest {

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

