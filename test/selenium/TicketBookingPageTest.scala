package selenium

class TicketBookingPageTest extends abstractTest{

  val host = "http://localhost:9000"

  //Test1
  "The List gallery" should "allow us to pick a movie" in {
    go to (host+"/listingGallery")
    click on xpath("/html/body/div/div[11]/div/div[2]/div/div/a")
    pageTitle should be ("QA Cinemas : Shrek")
  }

  //Test2
  "The movie page" should "allow us to select a time and date to book a movie ticket" in {
    go to (host+"/listingGallery/1")
    dateField(name("date")).value = "2021-02-28"
    click on xpath("//*[@id=\"time\"]")
    pageTitle should be ("QA Cinemas : Ticket Booking")
    textField(id("date")).value should be ("2021-02-28")
    textField(id("time")).value should be ("11:00")
  }

  //Test4
  "The Booking page" should "allow us to input all fields and book the ticket and then navigate to checkout page" in {
    go to (host+"/ticketBooking?date=2021-02-28&id=1&title=Shrek&time=11%3A00")
    textField(id("name_of_person")).value = "Sam Williams"
    numberField(id("numOfAdult")).value = "5"
    numberField(id("numOfChild")).value = "2"
    singleSel(id("concessions")).value = "7.99"
    singleSel(id("cinema_id")).value = "2"
    singleSel(id("deluxe")).value = "true"
    click on xpath("/html/body/div/form/div[1]/div/div/div/input")
    pageTitle should be ("QA Cinemas : Checkout")
    webDriver.findElementByXPath("/html/body/div/div/div/table/tbody[2]/tr[1]/td").getText should be ("Sam Williams")
    webDriver.findElementByXPath("/html/body/div/div/div/table/tbody[2]/tr[5]/td").getText should be ("£87.42")
    click on xpath("/html/body/div/div/div/div/a")
    pageTitle should be ("QA Cinemas : Home")
  }

  //Test4
  "The booking" should "allow us to cancel the booking" in {
    go to (host+"/ticketBooking?date=2021-02-28&id=1&title=Shrek&time=11%3A00")
    click on xpath("/html/body/div/form/div[1]/div/div/div/a")
    pageTitle should be ("QA Cinemas : Home")
  }



}
