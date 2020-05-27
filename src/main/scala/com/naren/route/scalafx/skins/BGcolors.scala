package com.naren.route.scalafx.skins

import scalafx.geometry.Insets
import scalafx.scene.layout.{Background, BackgroundFill}
import scalafx.scene.paint.Color

object BGcolors {

  val headerBG: Background = getBGcolor(Color.LightCyan)

  val summaryBG: Background = getBGcolor(Color.DarkCyan)

  val sideBarBG: Background = getBGcolor(Color.DarkOliveGreen)

  val mainFrameBG: Background = getBGcolor(Color.LightSeaGreen)

  val insets = Insets(2.0,2.0,2.0,2.0)
  def getBGcolor(color: Color): Background =
    new Background(
      Array(new BackgroundFill(color,null,null))
    )

}
