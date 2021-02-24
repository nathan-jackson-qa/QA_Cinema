package selenium

class ContactPageTest extends abstractTest{
  val host = "http://localhost:9000/contact"

  //Test1
  "The contact Page - Successful" should "to allow the user to input an email and send it off" in {
    go to (host)
    pageTitle should be ("QA Cinemas : Contact Page")
    textField(name("name")).value = "Chetan Pardeep"
    emailField(name("email")).value = "cpardeep@qa.com"
    textField(name("subject")).value = "Ref=12339"
    textField(name("message")).value = "Hi, I would like to cancel my booking. Kind regard Chetan Pardeep"
    click on xpath("/html/body/div/form/div/div/div/div/input[1]")
    webDriver.findElementByXPath("/html/body/div/form/div/div/div/div/h4").getText should be ("Successful!!")
  }

  //Test2
  "The contact Page" should "to allow the user to input an email and reset the values" in {
    go to (host)
    pageTitle should be ("QA Cinemas : Contact Page")
    textField(name("name")).value = "Chetan Pardeep"
    emailField(name("email")).value = "cpardeep@qa.com"
    textField(name("subject")).value = "Ref=12339"
    textField(name("message")).value = "Hi, I would like to cancel my booking. Kind regard Chetan Pardeep"
    click on xpath("/html/body/div/form/div/div/div/div/input[2]")
    textField(name("name")).value should be ("")
    emailField(name("email")).value should be ("")
    textField(name("subject")).value should be ("")
    textField(name("message")).value should be ("")
  }
}
