package net.valraiso
import java.util.logging.Logger

import com.github.buster84.cron.Schedule
import org.joda.time._

class CronScheduler(){

  private val log = Logger.getLogger("SchedulerLogger")
  private val tasks = scala.collection.mutable.ArrayBuffer.empty[CronTask]
  log.info("Instantiate CronScheduler")


  def addTask(name: String, job: () => Unit, cronExpression: String, timezone: DateTimeZone = DateTimeZone.forID("Europe/Paris")) = {
    log.info(s"Adding task $name ($cronExpression)")
    val t = new CronTask(name, job, Schedule(cronExpression, timezone))
    tasks+=t
  }

  def addDaily(name: String, job: () => Unit, hour: Int, minutes: Int, timezone: DateTimeZone = DateTimeZone.forID("Europe/Paris")) = {
    addTask(name, job, s"${minutes} ${hour} * * *")
  }

  def addHourly(name: String, job: () => Unit, minutes: Int, timezone: DateTimeZone = DateTimeZone.forID("Europe/Paris")) = {
    addTask(name, job, s"${minutes} * * * *")
  }

  def stop() = {
    tasks.map(_.cancel)
  }

}
