import play.sbt.routes.RoutesKeys

name := "QA Cinemas"
 
version := "2.8"
      
lazy val `recipies_new_play_version` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.13.5"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.3",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
  "mysql" % "mysql-connector-java" % "8.0.23"
)
libraryDependencies += "com.github.daddykotex" %% "courier" % "3.0.0-M2a"

libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % "test",
  "org.scalatestplus" %% "mockito-3-4" % "3.2.2.0" % "test"
)

libraryDependencies += "org.jvnet.mock-javamail" % "mock-javamail" % "1.9" % "test"

libraryDependencies += "org.scalatestplus" %% "selenium-3-141" % "3.2.2.0" % "test"

libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.2" % "test"

libraryDependencies += "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.2" % "test"

libraryDependencies ++= Seq(
  "org.reactivemongo" %% "play2-reactivemongo" % "0.20.13-play27"
)


RoutesKeys.routesImport += "play.modules.reactivemongo.PathBindables._"
