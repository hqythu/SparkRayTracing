/**
  * Created by hqythu on 7/27/16.
  */

package me.hqythu.sparkraytracing.utils

import scala.math.min

class Color(val r: Double = 0, val g: Double = 0, val b: Double = 0) extends Serializable {

  def +(that: Color): Color = new Color(r + that.r, g + that.g, b + that.b)

  def -(that: Color): Color = new Color(r - that.r, g - that.g, b - that.b)

  def *(factor: Double): Color = new Color(r * factor, g * factor, b * factor)

  def *(that: Color): Color = new Color(r * that.r, g * that.g, b * that.b)

  def /(factor: Double): Color = new Color(r / factor, g / factor, b / factor)

  def confine(): Color = new Color(min(r, 1.0), min(g, 1.0), min(b, 1.0))

}
