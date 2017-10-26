package net.valraiso

import java.util.logging.Logger
import java.util.{Timer, TimerTask}

import com.github.buster84.cron.Schedule
import org.joda.time._
class CronTask(name: String, job: () => Unit, schedule: Schedule) {
  private val log = Logger.getLogger("SchedulerLogger")
  val timer = new Timer()
  add()

  private def add(): Unit = {
    timer.schedule(new TimerTask {
      override def run(): Unit = {
        log.info(s"Running task $name at ${DateTime.now().toString("dd/MM/yyyy hh:mm:sss")}")
        add()
        job()
      }
    }, schedule.getNextAfter(DateTime.now()).toDate)
  }

  def cancel() = {
    log.info(s"Stopping schedule of task $name at ${DateTime.now().toString("dd/MM/yyyy hh:mm:sss")}")
    timer.cancel()
  }
}
