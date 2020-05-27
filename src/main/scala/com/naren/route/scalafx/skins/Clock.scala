package com.naren.route.scalafx.skins

import java.text.SimpleDateFormat
import java.util.Date

import scalafx.animation.PauseTransition
import scalafx.application.Platform
import scalafx.beans.property.StringProperty
import scalafx.util.Duration

// Object should be constructed lazily on the JFX App Thread. Verify that...
object Clock {
  assert(Platform.isFxApplicationThread)

  private val dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")

  val curTime = new StringProperty(getDate())

  // Update the curTime property every second, using a timer.
  // Note: 1,000 ms = 1 sec
  val timer = new PauseTransition(Duration(1000))
  timer.onFinished = {_ =>
    curTime.value = getDate()
    timer.playFromStart() // Wait another second, or you can opt to finish instead.
  }

  // Start the timer.
  timer.play()

  private def getDate() = dateFormat.format(new Date())
}
