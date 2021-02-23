package selenium

import org.openqa.selenium.{By, Keys}

import java.util.concurrent.TimeUnit

class gettingHereTest extends abstractTest {
    val host="http://localhost:9000/gettingThere"

  "The getting here page" should "allow the user to switch between each cinemas details" in {
    go to (host)
    pageTitle should be ("QA Cinemas : Getting Here")
    assert(webDriver.findElementById("pills-1-tab").isDisplayed)
    webDriver.findElement(By.id("pills-1-tab")).sendKeys(Keys.ENTER)
    webDriver.findElementById("cinema-1").getAttribute("style") should be ("color: #D8D4F2")
    click on xpath("/html/body/div/div[2]/ul/li[2]/button")
    webDriver.findElementById("cinema-2").getAttribute("style") should be ("color: #D8D4F2")
    click on xpath("/html/body/div/div[2]/ul/li[3]/button")
    webDriver.findElementById("cinema-3").getAttribute("style") should be ("color: #D8D4F2")
  }
}
