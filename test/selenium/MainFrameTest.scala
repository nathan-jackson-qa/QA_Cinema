package selenium

class MainFrameTest extends abstractTest {

  val host = "http://localhost:9000/about"

  //Test1
  "The Header brand image" should "link to the home page" in {
    go to (host)
    click on cssSelector(".navbar-brand > img")
    pageTitle should be ("QA Cinemas : Home")
  }

  //Test2
  "The navbar" should "link to all the corresponding pages" in {
    go to (host)
    //Home
    click on linkText("Home")
    pageTitle should be ("QA Cinemas : Home")
    //Out Now
    click on xpath("/html/body/nav/div/div/ul/li[2]/a")
    pageTitle should be ("QA Cinemas : Out Now")
    //Coming Soon
    click on xpath("/html/body/nav/div/div/ul/li[3]/a")
    pageTitle should be ("QA Cinemas : Coming Soon")
    //Opening Times
    click on xpath("/html/body/nav/div/div/ul/li[4]/a")
    pageTitle should be ("QA Cinemas : Opening Times")
    //Screens
    click on xpath("/html/body/nav/div/div/ul/li[5]/a")
    pageTitle should be ("QA Cinemas : Screens")
    //Getting There
    click on xpath("/html/body/nav/div/div/ul/li[6]/a")
    pageTitle should be ("QA Cinemas : Getting Here")
    //Classifications
    click on xpath("/html/body/nav/div/div/ul/li[7]/a")
    pageTitle should be ("QA Cinemas : Classifications")
    //Discussions
    click on xpath("/html/body/nav/div/div/ul/li[8]/a")
    pageTitle should be ("QA Cinemas : Discussion")
  }

  //Test3
  "The footer" should "link to all the corresponding pages" in {
    go to (host)
    //Home
    click on xpath("/html/body/footer/div/div/div[1]/h5/a")
    pageTitle should be ("QA Cinemas : About Page")
    //Out Now
    click on xpath("/html/body/footer/div/div/div[2]/h5[1]/a")
    pageTitle should be ("QA Cinemas : Contact Page")
  }

  //Test=3
  "The search bar" should "present all movies related the keyword" in {
    go to (host)
    textField(id("searchMovies")).value = "Bond"
    click on id("SearchBtn")
    pageTitle should be ("QA Cinemas : Search Results")
    assertResult(webDriver.findElementByXPath("/html/body/div/div/div/div[2]/div/h4").getText){"No Time to Die"}
  }

}
