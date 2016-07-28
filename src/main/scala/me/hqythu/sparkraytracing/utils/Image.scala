/**
  * Created by hqythu on 7/29/16.
  */

package me.hqythu.sparkraytracing.utils

import java.awt.image.BufferedImage

class Image(var width: Int = 0, var height: Int = 0) extends Serializable {

  var r = new Array[Short](width * height)
  var g = new Array[Short](width * height)
  var b = new Array[Short](width * height)

  def setColor(x: Int, y: Int, color: Color): Unit = {
    r(x * height + y) = (color.r * 255).toByte
    g(x * height + y) = (color.g * 255).toByte
    b(x * height + y) = (color.b * 255).toByte
  }

  def getColor(x: Int, y: Int): Color = {
    new Color(r(x * height + y) / 255.0, g(x * height + y) / 255.0, b(x * height + y) / 255.0)
  }

  def toBufferedImage: BufferedImage = {
    val image: BufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

    for (i <- 0 until width) {
      for (j <- 0 until height) {
        val rgb = r(i * height + j) * 256 * 256 + g(i * height + j) * 256 + b(i * height + j)
        image.setRGB(i, height - j - 1, rgb)
      }
    }

    image
  }

  def fromBufferedImage(img: BufferedImage): Unit = {
    width = img.getWidth
    height = img.getHeight
    r = new Array[Short](width * height)
    g = new Array[Short](width * height)
    b = new Array[Short](width * height)
    for (i <- 0 until width) {
      for (j <- 0 until height) {
        val argb = img.getRGB(i, height - j - 1)
        val rgb = argb & (256 * 256 * 256 - 1)
        val r = (rgb / 256 / 256 % 256).toShort
        val g = (rgb / 256 % 256).toShort
        val b = (rgb % 256).toShort
        this.r(i * height + j) = r
        this.g(i * height + j) = g
        this.b(i * height + j) = b
      }
    }
  }

}
