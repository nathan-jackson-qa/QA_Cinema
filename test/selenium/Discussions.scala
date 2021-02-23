package selenium

class Discussions extends abstractTest {

  val host = "http://localhost:9000/discussions"

  //Test1
  "The Cinema Home Page" should "have the correct title" in {
    go to (host)
    pageTitle should be ("QA Cinemas : Home")
  }
}
