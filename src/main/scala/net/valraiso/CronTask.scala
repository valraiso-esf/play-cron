package net.valraiso

import java.util.logging.Logger
import java.util.{Timer, TimerTask}

import com.github.buster84.cron.Schedule
import org.joda.time._
class CronTask(name: String, job: () => Any, schedule: Schedule) {
  private val log = Logger.getLogger("SchedulerLogger")
  private def nowString() = DateTime.now().toString("dd/MM/yyyy hh:mm:ss.SSS")
  val timer = new Timer()
  add()

  private def add(): Unit = {
    timer.schedule(new TimerTask {
      override def run(): Unit = {
        log.info(s"Running task $name at ${nowString()}")
        add()
        job()
      }
    }, schedule.getNextAfter(DateTime.now().withSecondOfMinute(0).withMillisOfSecond(0)).toDate)
  }

  def cancel() = {
    log.info(s"Stopping schedule of task $name at ${nowString()}")
    timer.cancel()
  }
}
