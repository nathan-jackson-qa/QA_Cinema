package selenium

import org.openqa.selenium.{By, Keys}

import java.util.concurrent.TimeUnit

class gettingHereTest extends abstractTest {
    val host="http://localhost:9000/gettingThere"

  "The getting here page" should "allow the user to switch between each cinemas details" in {
    go to (host)
    pageTitle should be ("QA Cinemas : Getting Here")
    click on xpath("/html/body/div/div[2]/ul/li[1]/button")
    webDriver.findElementById("cinema-1").getAttribute("style") should be ("color: #D8D4F2")
    click on xpath("/html/body/div/div[2]/ul/li[2]/button")
    webDriver.findElementById("cinema-2").getAttribute("style") should be ("color: #D8D4F2")
    click on xpath("/html/body/div/div[2]/ul/li[3]/button")
    webDriver.findElementById("cinema-3").getAttribute("style") should be ("color: #D8D4F2")
  }

  "The getting here page" should "have google maps embedded with the location of the cinema displayed" in {
    go to (host)
    click on xpath("/html/body/div/div[2]/ul/li[1]/button")
    assert(webDriver.findElementByXPath("//*[@id=\"pills-1\"]/div[2]/iframe").getAttribute("src").contains("maps"))
    click on xpath("/html/body/div/div[2]/ul/li[2]/button")
    assert(webDriver.findElementByXPath("//*[@id=\"pills-1\"]/div[2]/iframe").getAttribute("src").contains("maps"))
    click on xpath("/html/body/div/div[2]/ul/li[3]/button")
    assert(webDriver.findElementByXPath("//*[@id=\"pills-1\"]/div[2]/iframe").getAttribute("src").contains("maps"))
  }

}
