Testing: Line coverage 92% 
Jira Board(https://team-1606236407721.atlassian.net/jira/software/projects/QAC/boards/8/roadmap)
# QA Cinemas Group Project
QA Cinemas is a website for a fictional cinema chain known as 'QA Cinemas'. It was created as part of a group project using primarily Scala and features a fully functional website that is populated by information extracted from a database and enables users to book tickets and post reviews on whatever they choose.
## Getting Started
In order to initialize and host the website after installing, you first need to find the application.conf file and change the mysqlDB properties to match a database you host. On this database you will need a table structure similar to the ERD which is featured in the Documentation folder of this repository.

### Prerequisites
The only requirement for this project is to have the scala build tool installed on your system, this will allow you to perform the 'sbt run' and 'sbt test' functions which are necessary for this program as well as more which aren't.

### Running
With your database setup and connected using the .conf file, with sbt installed, in order to run the web application all you need to do is use the 'sbt run' command on your command line in the location of your project folder.
```
C:\Users\Me\Project_Location\QA_Cinema> sbt run
```
When you receive the confirmation message in the terminal saying the server is running, you can open and use the website by opening localhost:9000 on your browser, which should greet you with the website homepage.
## Testing
For testing this project there are 19 selenium tests and 21 backend scala tests. In order for the selenium tests to be carried out correctly you need to ensure that the server is up and running. With the website running all you need to do to run all the tests is to again open the command line in your project location and type 'sbt test'.
```
C:\Users\Me\Project_Location\QA_Cinema> sbt test
```
You will then see all the tests being carried out and after all have run, an overview of the results listing how many passed and how many failed.
## Authors

* **Chetan Pardeep** - *Product Owner* - [QAcpardeep](https://github.com/QAcpardeep)
* **Ashley Cameron** - *Scrum Master* - [AshleyCameron1994](https://github.com/AshleyCameron1994)
* **Nathan Jackson** - *Developer* - [nathan-jackson-qa](https://github.com/nathan-jackson-qa)
* **Carl Angeles** - *Developer* -[QA-CarlA](https://github.com/QA-CarlA)
* **Sam Williams** - *Developer* - [QA-SamWilliams](https://github.com/QA-SamWilliams)



## Acknowledgments
Profanity filter - Areeb Beigh - https://github.com/areebbeigh/profanityfilter/blob/master/profanityfilter/data/badwords.txt
Stylesheets - Bootstrap - https://getbootstrap.com/
Paypal - Mock Paypal Checkout - https://developer.paypal.com/home
Dolan Murvihill - Courier Email Form Handling - https://github.com/dmurvihill/courier
