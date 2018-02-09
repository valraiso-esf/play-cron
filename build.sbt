name := "play-cron"

organization := "net.valraiso"

version := "0.0.2"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.github.buster84" %% "scala-cron" % "1.0.0"
  ,  "joda-time" % "joda-time" % "2.3"
)

publishTo := Some(
  Resolver.sftp("ESFPlus Repository","releases-esf.valraiso.net","/var/www/releases/").as(
    "valraiso", new File(sys.env("HOME") + "/.ssh/id_rsa")
  )
)
