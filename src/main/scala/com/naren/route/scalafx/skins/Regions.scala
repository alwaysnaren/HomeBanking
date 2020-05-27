package com.naren.route.scalafx.skins

import scalafx.scene.effect.Reflection
import scalafx.scene.layout.{Background, Region}

object Regions {

  val header: Region = getRegion(BGcolors.headerBG)

  val summary: Region = getRegion(BGcolors.summaryBG)

  val sideBar: Region = getRegion(BGcolors.sideBarBG)

  val mainFrame: Region = getRegion(BGcolors.mainFrameBG)

  def getRegion(bg: Background): Region =
    new Region {
      effect = new Reflection {
        fraction = 0.45
      }
      background = bg
    }

}
