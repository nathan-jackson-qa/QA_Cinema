package selenium

class NewReleasesPageTest extends abstractTest{
  val host = "http://localhost:9000/comingSoon"

  //Test1
  "The contact Page - Successful" should "to allow the user to input an email and send it off" in {
    go to (host)
    var x = webDriver.findElementByXPath("/html/body/div/div[2]/div/div[2]/div/h4").getText
    click on xpath("/html/body/div/div[2]/div/div[2]/div/div/a")
    pageTitle should be (s"QA Cinemas : $x")
    webDriver.findElementByXPath("/html/body/div/div/div/div[2]/div/h4").getText should be (x)
  }
}
