package selenium

class DiscussionsPageTest extends abstractTest {

  val host = "http://localhost:9000/discussions"

  //Test1
  "The Discussions Page" should "have the correct title when loading" in {
    go to (host)
    pageTitle should be ("QA Cinemas : Discussion")
    xpath("/html/body/div/div[1]/div/h1")
    assert(webDriver.findElementByXPath("/html/body/div/div[1]/div/h1").isDisplayed)
  }

  "When user clicks review button it" should "load the review page" in {
    go to (host)
    assert(webDriver.findElementByXPath("/html/body/div/div[2]/a").isDisplayed)
    click on xpath("/html/body/div/div[2]/a")
    pageTitle should be ("QA Cinemas : Review Form")
  }

  "The review form - SUCCESSFUL" should "allow the user to input review and submit and it should be successful" in {
    go to (host+"/form")
    pageTitle should be ("QA Cinemas : Review Form")
    textField(id("title")).value = "Shrek 2"
    singleSel(id("rating")).value = "5"
    textField(id("description")).value = "Shrek 2 was great, but very childish"
    //textArea("description").value = "Shrek 2 was great, but very childish"
    click on id("submitForm")
    pageTitle should be ("QA Cinemas : Moderated Review")
    assert("Your review was: SUCCESSFUL" == webDriver.findElementByXPath("/html/body/div/div[1]/div/h1").getText)
  }

  "The review form - UNSUCCESSFUL" should "allow the user to input review and submit and it should be unsuccessful" in {
    go to (host+"/form")
    pageTitle should be ("QA Cinemas : Review Form")
    textField(id("title")).value = "Shrek 2"
    singleSel(id("rating")).value = "1"
    textField(id("description")).value = "Shrek was shit"
    //textArea("description").value = "Shrek 2 was great, but very childish"
    click on id("submitForm")
    pageTitle should be ("QA Cinemas : Moderated Review")
    assert("Your review was: UNSUCCESSFUL" == webDriver.findElementByXPath("/html/body/div/div[1]/div/h1").getText)
  }
}
