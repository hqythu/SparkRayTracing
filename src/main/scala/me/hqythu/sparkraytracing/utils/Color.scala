/**
  * Created by hqythu on 7/27/16.
  */

package me.hqythu.sparkraytracing.utils

import scala.math.min

class Color(val r: Double = 0, val g: Double = 0, val b: Double = 0) extends Serializable {

//  assert(0 <= r && r <= 1)
//  assert(0 <= g && g <= 1)
//  assert(0 <= b && b <= 1)

  def +(that: Color): Color = new Color(r + that.r, g + that.g, b + that.b)

  def -(that: Color): Color = new Color(r - that.r, g - that.g, b - that.b)

  def *(factor: Double): Color = new Color(r * factor, g * factor, b * factor)

  def *(that: Color): Color = new Color(r * that.r, g * that.g, b * that.b)

  def /(factor: Double): Color = new Color(r / factor, g / factor, b / factor)

  def confine(): Color = new Color(min(r, 1.0), min(g, 1.0), min(b, 1.0))

}


object Color {

  def fromRGB(argb: Int): Color = {
    val rgb = argb & (256 * 256 * 256 - 1)
    val r = (rgb / 256 / 256 % 256) / 255.0
    val g = (rgb / 256 % 256) / 255.0
    val b = (rgb % 256) / 255.0
    new Color(r, g, b)
  }

}
