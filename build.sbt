name := "play-cron"

organization := "net.valraiso"

version := "0.0.4"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.github.buster84" %% "scala-cron" % "1.0.0"
  ,  "joda-time" % "joda-time" % "2.3"
)
